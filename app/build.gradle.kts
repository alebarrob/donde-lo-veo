plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "barrera.alejandro.dondeloveo"
    compileSdk = 34

    defaultConfig {
        applicationId = "barrera.alejandro.dondeloveo"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val androidxCoreVersion = "1.12.0"
    val appCompatVersion = "1.6.1"
    val materialVersion = "1.11.0"
    val constraintLayoutVersion = "2.1.4"
    val navigationVersion = "2.7.6"
    val coilVersion = "2.5.0"
    val junitVersion = "4.13.2"
    val espressoCoreVersion = "3.5.1"
    val androidTestExtVersion = "1.1.5"

    // Core dependencies for Android development
    implementation("androidx.core:core-ktx:$androidxCoreVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")

    // Navigation dependencies
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    // Coil dependencies
    implementation("io.coil-kt:coil:$coilVersion")

    // Unit testing dependencies
    testImplementation("junit:junit:$junitVersion")

    // Android Test dependencies
    androidTestImplementation("androidx.test.ext:junit:$androidTestExtVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCoreVersion")
}