package com.demo.centurion.playfeaturedelivery

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.demo.centurion.shared.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      printLogger()
      androidContext(this@App)
      modules(sharedModules)
    }
  }

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    SplitCompat.install(this)
  }
}