package com.demo.centurion.shared.di

import com.demo.centurion.shared.data.network.InspectionsAPI
import com.demo.centurion.shared.data.network.IssuesAPI
import com.demo.centurion.shared.data.repository.InspectionsRepository
import com.demo.centurion.shared.data.repository.InspectionsRepositoryImpl
import com.demo.centurion.shared.data.repository.IssuesRepository
import com.demo.centurion.shared.data.repository.IssuesRepositoryImpl
import com.demo.centurion.shared.presentation.viewmodels.CatsDogViewModel
import com.demo.centurion.shared.utils.Constants
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(get())
            .build()
    }
}

val catsRetrofitModule: Module = module {

    single(named("cats")) {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(get())
            .build()
    }
}

val repositoryModule: Module = module {
    single<IssuesRepository> { IssuesRepositoryImpl(get()) }
    single<InspectionsRepository> { InspectionsRepositoryImpl(get()) }
}

val inpsectionsAPIModule: Module = module {
    single<InspectionsAPI> { get<Retrofit>(named("cats")).create() }
}

val issuesAPIModule: Module = module {
    single<IssuesAPI> { get<Retrofit>(named("dogs")).create() }
}

val viewModelModule: Module = module {
    single { CatsDogViewModel(get(), get()) }
}

val sharedModules: List<Module> = listOf(
    okhttpModule,
    dogsRetrofitModule,
    catsRetrofitModule,
    inpsectionsAPIModule,
    issuesAPIModule,
    repositoryModule,
    viewModelModule
)