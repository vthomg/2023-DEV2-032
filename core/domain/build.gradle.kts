plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("be.bnp.katas.tictactoe.androidlibrary")
}

android {
    namespace = "be.bnp.katas.tictactoe.domain"
}

dependencies {
    implementation(project(":core:data"))

    testImplementation(libs.junit)
    testImplementation(libs.testing.mockito)
    testImplementation(libs.testing.androidx.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}