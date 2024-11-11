plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("be.bnp.katas.tictactoe.androidlibrary")
}

android {
    namespace = "be.bnp.katas.tictactoe.domain"
}

dependencies {
    testImplementation(libs.kotlin.test)
    testImplementation(libs.testing.coroutines)
    testImplementation(libs.testing.mockito)
    testImplementation(libs.testing.mockk)
}