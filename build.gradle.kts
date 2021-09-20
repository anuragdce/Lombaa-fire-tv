buildscript {

    val kotlin_version by extra("1.4.32")
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Deps.Android.Tools.GRADLE)
        classpath(Deps.AndroidX.Navigation.SAFE_ARGS_GRADLE_PLUGIN)
        classpath(Deps.Hilt.ANDROID_GRADLE_PLUGIN)
        classpath(Deps.Kotlin.GRADLE_PLUGIN)
        classpath(Deps.Firebase.CRASHLYTICS_GRADLE_PLUGIN)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}