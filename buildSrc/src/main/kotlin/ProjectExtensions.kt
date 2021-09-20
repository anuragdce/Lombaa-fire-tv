import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.applyCommonConfig() {
    android {
        compileSdkVersion(Config.COMPILE_SDK_VERSION)
        defaultConfig {
            minSdkVersion(Config.MIN_SDK_VERSION)
            targetSdkVersion(Config.TARGET_SDK_VERSION)
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        compileOptions {
            sourceCompatibility = Config.JAVA_VERSION
            targetCompatibility = Config.JAVA_VERSION
        }
        kotlinOptions {
            jvmTarget = Config.JAVA_VERSION.toString()
        }
        sourceSets.all {
            java.srcDir("src/$name/kotlin")
        }
        buildFeatures {
            dataBinding = true
        }
    }
    dependencies {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        testImplementation(Deps.JUNIT)
        androidTestImplementation(Deps.AndroidX.Test.RUNNER)
    }
}