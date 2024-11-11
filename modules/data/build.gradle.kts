plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("be.bnp.katas.tictactoe.androidlibrary")
}

android {
    namespace = "be.bnp.katas.tictactoe.data"
}

dependencies {
    implementation(project(":modules:domain"))
    testImplementation(libs.junit)
    testImplementation(libs.testing.mockito)
    testImplementation(libs.testing.mockk)
    testImplementation(libs.testing.androidx.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}