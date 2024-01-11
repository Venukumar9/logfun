plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.motivity.mcfkotlinandroidapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.motivity.mcfkotlinandroidapplication"
        minSdk = 24
        targetSdk = 33
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

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // build.gradle (app module)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Mockito
    testImplementation ("org.mockito:mockito-core:5.7.0")
    androidTestImplementation ("org.mockito:mockito-android:5.7.0")

    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.5.1")

    // JUnit dependencies

    androidTestImplementation ("junit:junit:4.13.2")

    // Espresso dependencies

    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.5.1")

    androidTestImplementation ("androidx.test.espresso:espresso-idling-resource:3.5.1")


    // LiveData testing utilities
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.robolectric:robolectric:4.11.1")
    androidTestImplementation ("androidx.test:core:1.5.0")
    implementation(project(":logfun"))

}