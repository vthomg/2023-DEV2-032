plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("be.bnp.katas.tictactoe.androidlibrary")
}

android {
    namespace = "be.bnp.katas.tictactoe.domain"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}