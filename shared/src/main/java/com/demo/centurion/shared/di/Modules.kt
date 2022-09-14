
package com.demo.centurion.shared.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.demo.centurion.shared.data.network.CatsAPI
import com.demo.centurion.shared.data.network.DogsAPI
import com.demo.centurion.shared.data.repository.*
import com.demo.centurion.shared.presentation.viewmodels.CatsDogViewModel
import com.demo.centurion.shared.utils.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

val okhttpModule: Module = module {
  single {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = when (BuildConfig.BUILD_TYPE) {
      "release" -> HttpLoggingInterceptor.Level.NONE
      else -> HttpLoggingInterceptor.Level.BODY
    }
    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()
  }
}

val dogsRetrofitModule: Module = module {
  single(named("dogs")) {
    Retrofit.Builder()
        .baseUrl(Constants.DOGS_BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(get())
        .build()
  }
}

val catsRetrofitModule: Module = module {
  single(named("cats")) {
    Retrofit.Builder()
        .baseUrl(Constants.CATS_BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(get())
        .build()
  }
}

val repositoryModule: Module = module {
  single<DogsRepository> { DogsRepositoryImpl(get()) }
  single<CatsRepository> { CatsRepositoryImpl(get()) }
}

val catsAPIModule: Module = module {
  single<CatsAPI> { get<Retrofit>(named("cats")).create() }
}

val dogsAPIModule: Module = module {
  single<DogsAPI> { get<Retrofit>(named("dogs")).create() }
}

val viewModelModule: Module = module {
  single { CatsDogViewModel(get(), get()) }
}

val sharedModules: List<Module> = listOf(
    okhttpModule,
    dogsRetrofitModule,
    catsRetrofitModule,
    catsAPIModule,
    dogsAPIModule,
    repositoryModule,
    viewModelModule
)