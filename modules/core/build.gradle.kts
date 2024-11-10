plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("be.bnp.katas.tictactoe.androidlibrary")
}

android {
    namespace = "be.bnp.katas.tictactoe.core"
}

dependencies {
    implementation(libs.koin.compose)
    testImplementation(libs.testing.koin)
}