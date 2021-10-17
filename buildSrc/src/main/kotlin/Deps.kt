object Deps : BaseDep() {

  val JUNIT = "junit:junit" version "4.13.2"

  val JODA = "net.danlew:android.joda" version "2.10.9.1"

  val LOADER = "com.agrawalsuneet.androidlibs:dotsloader" version "1.4"

  val SMART_LOCATION = "io.nlopez.smartlocation:library" version "3.3.3"

  val LEANBACK = "androidx.leanback:leanback" version "1.0.0"

  object Android : BaseDep("com.google.android") {
    val LOCATION = dep("gms:play-services-location", "18.0.0")
    val MAP = dep("gms:play-services-maps", "17.0.1")
    val MATERIAL = dep("material:material", "1.3.0")

    object Tools : BaseDep("com.android.tools") {
      val GRADLE = dep("build:gradle", "4.1.1")
    }
  }

  object AndroidX : BaseDep("androidx") {
    val APPCOMPAT = dep("appcompat:appcompat", "1.3.1")
    val CONSTRAINTLAYOUT = dep("constraintlayout:constraintlayout", "2.1.1")
    val CORE_KTX = dep("core:core-ktx", "1.5.0")
    val EXIFINTERFACE = dep("exifinterface:exifinterface", "1.3.2")
    val FRAGMENT_KTX = dep("fragment:fragment-ktx", "1.4.0-alpha01")
    val STARTUP = dep("startup:startup-runtime", "1.0.0")
    val SWIPE_REFRESH = dep("swiperefreshlayout:swiperefreshlayout", "1.1.0")

    object Hilt : BaseDep("androidx.hilt", "1.0.0") {
      val COMPILER = dep(name = "hilt-compiler")
    }

    object Lifecycle : BaseDep("androidx.lifecycle", "2.3.1") {
      val LIVEDATA_KTX = dep(name = "lifecycle-livedata-ktx")
      val VIEWMODEL_KTX = dep(name = "lifecycle-viewmodel-ktx")
    }

    object Navigation : BaseDep("androidx.navigation", "2.4.0-alpha01") {
      val FRAGMENT_KTX = dep(name = "navigation-fragment-ktx")
      val SAFE_ARGS_GRADLE_PLUGIN = dep(name = "navigation-safe-args-gradle-plugin")
      val UI_KTX = dep(name = "navigation-ui-ktx")
    }

    object Room : BaseDep("androidx.room", "2.3.0") {
      val COMPILER = dep(name = "room-compiler")
      val KTX = dep(name = "room-ktx")
      val RUNTIME = dep(name = "room-runtime")
    }

    object Test : BaseDep("androidx.test") {
      val ESPRESSO_CORE = dep("espresso:espresso-core", "3.3.0")
      val JUNIT = dep("ext:junit", "1.1.2")
      val RUNNER = dep(name = "runner", version = "1.3.0")
    }
  }

  object Dialogs : BaseDep("com.afollestad.material-dialogs", "3.3.0") {
    val BOTTOM_SHEETS = dep(name = "bottomsheets")
    val CORE = dep(name = "core")
    val INPUT = dep(name = "input")
    val DATETIME = dep(name = "datetime")
    val LIFECYCLE = dep(name = "lifecycle")
  }

  object ExoPlayer : BaseDep("com.google.android.exoplayer", "2.15.1") {
    val CORE = dep(name = "exoplayer")
    val LEANBACK = dep(name = "extension-leanback")
  }

  object Firebase : BaseDep("com.google.firebase") {
    val BOM = dep(name = "firebase-bom", version = "26.5.0")
    val CRASHLYTICS = dep(name = "firebase-crashlytics")
    val CRASHLYTICS_GRADLE_PLUGIN = dep(name = "firebase-crashlytics-gradle", version = "2.7.1")
    val DATABASE = dep(name = "firebase-database")
    val REMOTE_CONFIG = dep(name = "firebase-config-ktx")
  }

  object Google : BaseDep("com.google.gms") {
    val SERVICES = dep(name = "google-services", version = "4.3.5")
  }

  object Hilt : BaseDep("com.google.dagger", "2.35") {
    val ANDROID = dep(name = "hilt-android")
    val ANDROID_COMPILER = dep(name = "hilt-android-compiler")
    val ANDROID_GRADLE_PLUGIN = dep(name = "hilt-android-gradle-plugin")
    val ANDROID_TESTING = dep(name = "hilt-android-testing")
  }

  object Kotlin : BaseDep("org.jetbrains.kotlin", "1.4.32") {
    val GRADLE_PLUGIN = dep(name = "kotlin-gradle-plugin")
    val STDLIB = dep(name = "kotlin-stdlib")
  }

  object JacksonCore : BaseDep("com.fasterxml.jackson.core", "2.12.1") {
    val CORE = dep(name = "jackson-core")
    val ANNOTATION = dep(name = "jackson-annotations")
    val DATABIND = dep(name = "jackson-databind")
  }

  object Jackson : BaseDep("com.fasterxml.jackson.module", "2.12.1") {
    val KOTLIN = dep(name = "jackson-module-kotlin")
  }

  object OkHttp3 : BaseDep("com.squareup.okhttp3", "4.9.1") {
    val OKHTTP = dep(name = "okhttp")
    val OKHTTP_LOGGER = dep(name = "logging-interceptor")
  }

  object Retrofit : BaseDep("com.squareup.retrofit2", "2.9.0") {
    val RETROFIT = dep(name = "retrofit")
    val JACKSON = dep(name = "converter-jackson")
  }

  object FondesaPermissions : BaseDep("com.github.fondesa", "3.2.1") {
    val CORE = dep(name = "kpermissions")
    val COROUTINES = dep(name = "kpermissions-coroutines")
  }

  object Glide : BaseDep("com.github.bumptech.glide" ,"4.12.0") {
    val CORE = dep(name = "glide")
    val COMPILER = dep(name = "compiler")
  }

  object Sdp : BaseDep("com.intuit.sdp", "1.0.6") {
    val ANDROID = dep(name = "sdp-android")
  }

  object Ssp : BaseDep("com.intuit.ssp", "1.0.6") {
    val ANDROID = dep(name = "ssp-android")
  }
}

abstract class BaseDep(
  private val groupPrefix: String = "",
  private val defaultVersion: String = ""
) {

  protected fun dep(groupName: String = "", version: String = "", name: String = ""): String {
    val depGroupName =
      if (groupName.isNotEmpty()) "$groupPrefix.$groupName" else "$groupPrefix:$name"
    val depVersion = if (version.isNotEmpty()) version else defaultVersion
    return "$depGroupName:$depVersion"
  }
}

private infix fun String.version(version: String) = "$this:$version"
