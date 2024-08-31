plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "my.id.jeremia.etrash"
    compileSdk = 34

    defaultConfig {
        applicationId = "my.id.jeremia.etrash"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        debug {
            buildConfigField("String","BASE_URL", "\"https://d7f3-38-9-128-236.ngrok-free.app/\"")
            buildConfigField("String","ADMIN_PHONENUMBER", "\"6282275290173\"")
        }
        release {
            buildConfigField("String","BASE_URL", "\"https://etrashclinic.com\"")
            buildConfigField("String","ADMIN_PHONENUMBER", "\"6282275290173\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
        compose = true
        buildConfig=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Image Picker
    implementation ("com.github.dhaval2404:imagepicker:2.1")

    //Code Scanner
    implementation ("com.github.yuriy-budiyev:code-scanner:2.3.0")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation ("com.google.firebase:firebase-messaging")

    //Extended Icon Material
    implementation ("androidx.compose.material:material-icons-extended:1.5.10")

    //Haze Bottom Bar Blur
    implementation("dev.chrisbanes.haze:haze:0.6.0")

    //ROOM DB
    val room = "2.6.1"
    implementation("androidx.room:room-runtime:$room")
    ksp("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-ktx:$room")

    //Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")

    //CameraX
    val camerax_version = "1.3.2"
    implementation ("androidx.camera:camera-core:${camerax_version}")
    implementation ("androidx.camera:camera-camera2:${camerax_version}")
    implementation ("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation ("androidx.camera:camera-video:${camerax_version}")

    implementation ("androidx.camera:camera-view:${camerax_version}")
    implementation ("androidx.camera:camera-extensions:${camerax_version}")

    // Image Coil
    val coil = "2.6.0"
    implementation("io.coil-kt:coil:$coil")
    implementation("io.coil-kt:coil-compose:$coil")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.1.1")


    // Network
    val retrofit2 = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit2")
    implementation("com.squareup.retrofit2:converter-moshi:${retrofit2}")

    val okhttp3 = "4.12.0"
    implementation(platform("com.squareup.okhttp3:okhttp-bom:$okhttp3"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // JSON
    val moshi = "1.15.0"
    implementation("com.squareup.moshi:moshi:$moshi")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshi")

    val coroutines = "1.8.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    // Work
    val work = "2.9.0"
    implementation("androidx.work:work-runtime-ktx:$work")

    // Dependency Management
    val hilt = "2.50"
    implementation("com.google.dagger:hilt-android:$hilt")
    ksp("com.google.dagger:hilt-android-compiler:$hilt")

    val hiltKtx = "1.1.0"
    implementation("androidx.hilt:hilt-navigation-compose:$hiltKtx")
    implementation("androidx.hilt:hilt-work:$hiltKtx")
    ksp("androidx.hilt:hilt-compiler:$hiltKtx")

    //Lottie Animation
    val lottie = "6.4.0"
    implementation("com.airbnb.android:lottie-compose:$lottie")

    //Splash Screen
    implementation(libs.androidx.core.splashscreen)

    //RXJava
    implementation ("io.reactivex.rxjava3:rxjava:3.0.6")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")


    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}