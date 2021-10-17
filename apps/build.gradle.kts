plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("android.extensions")
  kotlin("kapt")
  id("dagger.hilt.android.plugin")
  id("androidx.navigation.safeargs")
}

applyCommonConfig()

android {
  defaultConfig {
    applicationId = "com.lombaa.firetv"
    versionCode = 1
    versionName = "1.0"
  }

  buildTypes {
    getByName("debug") {
      applicationIdSuffix = ""
    }

    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  lintOptions {
    isCheckReleaseBuilds = false
    isAbortOnError = false
  }
}

dependencies {
  implementation(project(":base"))
  implementation(Deps.Android.MATERIAL)

  implementation(Deps.AndroidX.APPCOMPAT)
  implementation(Deps.AndroidX.CONSTRAINTLAYOUT)
  implementation(Deps.AndroidX.CORE_KTX)

  implementation(Deps.AndroidX.Navigation.FRAGMENT_KTX)
  implementation(Deps.AndroidX.Navigation.UI_KTX)

  implementation(Deps.AndroidX.Room.RUNTIME)
  implementation(Deps.AndroidX.Room.KTX)
  kapt(Deps.AndroidX.Room.COMPILER)

  implementation(Deps.ExoPlayer.CORE)
  implementation(Deps.ExoPlayer.LEANBACK)

  implementation(Deps.Firebase.CRASHLYTICS)

  implementation(Deps.FondesaPermissions.CORE)
  implementation(Deps.FondesaPermissions.COROUTINES)

  implementation(Deps.Hilt.ANDROID)
  kapt(Deps.Hilt.ANDROID_COMPILER)
  kapt(Deps.AndroidX.Hilt.COMPILER)


  implementation(Deps.JacksonCore.ANNOTATION)
  implementation(Deps.JacksonCore.CORE)
  implementation(Deps.JacksonCore.DATABIND)
  implementation(Deps.Jackson.KOTLIN)

  implementation(Deps.OkHttp3.OKHTTP)
  implementation(Deps.OkHttp3.OKHTTP_LOGGER)

  implementation(Deps.Retrofit.RETROFIT)
  implementation(Deps.Retrofit.JACKSON)

  implementation(Deps.Kotlin.STDLIB)

  implementation(Deps.JODA)
  implementation(Deps.LEANBACK)

  implementation(Deps.Glide.CORE)
  kapt(Deps.Glide.COMPILER)

  implementation(Deps.Sdp.ANDROID)
  implementation(Deps.Ssp.ANDROID)

  androidTestImplementation(Deps.AndroidX.Test.ESPRESSO_CORE)
}