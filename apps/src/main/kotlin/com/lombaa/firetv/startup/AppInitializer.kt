package com.lombaa.firetv.startup

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import net.danlew.android.joda.JodaTimeInitializer

class AppInitializer : Initializer<Any> {
  override fun create(context: Context): Any {
    AppInitializer.getInstance(context).initializeComponent(JodaTimeInitializer::class.java)
    return Any()
  }

  override fun dependencies(): List<Class<out Initializer<*>>> {
    return emptyList()
  }
}