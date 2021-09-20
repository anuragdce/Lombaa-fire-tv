import org.gradle.api.JavaVersion

object Config {
    const val COMPILE_SDK_VERSION = 30

    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 30

    val JAVA_VERSION = JavaVersion.VERSION_1_8
}