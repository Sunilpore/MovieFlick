plugins {
    //`kotlin-dsl`
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

    namespace = "com.movieflick.data"
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {
    api(project(":domain"))

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    implementation(libs.paging.common.ktx)

    implementation(libs.converter.gson)

}
