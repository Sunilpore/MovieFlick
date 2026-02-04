plugins {
    alias (libs.plugins.android.library)
    alias (libs.plugins.kotlin.android)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://movies-mock-server.vercel.app/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    flavorDimensions.add("environment")

    productFlavors {
        create("prod") {
            dimension = "environment"
            isDefault = true
        }

        create("mock"){
            dimension = "environment"
        }
    }

    namespace = "com.movieflick.domain"
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {

}
