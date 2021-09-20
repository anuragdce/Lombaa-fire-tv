plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

applyCommonConfig()

dependencies {
    implementation(Deps.Android.MATERIAL)
    implementation(Deps.AndroidX.APPCOMPAT)
    implementation(Deps.AndroidX.EXIFINTERFACE)
    implementation(Deps.AndroidX.FRAGMENT_KTX)
    implementation(Deps.AndroidX.SWIPE_REFRESH)

    implementation(Deps.AndroidX.Navigation.FRAGMENT_KTX)
    implementation(Deps.AndroidX.Navigation.UI_KTX)

    implementation(Deps.AndroidX.Navigation.FRAGMENT_KTX)
    implementation(Deps.AndroidX.Navigation.UI_KTX)

    implementation(Deps.AndroidX.Room.RUNTIME)
    implementation(Deps.AndroidX.Room.KTX)
    kapt(Deps.AndroidX.Room.COMPILER)

    implementation(Deps.Dialogs.BOTTOM_SHEETS)
    implementation(Deps.Dialogs.CORE)
    implementation(Deps.Dialogs.INPUT)
    implementation(Deps.Dialogs.DATETIME)
    implementation(Deps.Dialogs.LIFECYCLE)

    implementation(platform(Deps.Firebase.BOM))
    implementation(Deps.Firebase.REMOTE_CONFIG)

    implementation(Deps.FondesaPermissions.CORE)
    implementation(Deps.FondesaPermissions.COROUTINES)

    implementation(Deps.Hilt.ANDROID)
    kapt(Deps.Hilt.ANDROID_COMPILER)

    implementation(Deps.JacksonCore.ANNOTATION)
    implementation(Deps.JacksonCore.CORE)
    implementation(Deps.JacksonCore.DATABIND)
    implementation(Deps.Jackson.KOTLIN)

    implementation(Deps.Sdp.ANDROID)
    implementation(Deps.Ssp.ANDROID)

    implementation(Deps.JODA)
    implementation(Deps.LOADER)
}