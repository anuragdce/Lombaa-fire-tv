package com.freemovies.firetv.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LombaaApplication : Application() {
  override fun onCreate() {
    super.onCreate()
  }
}