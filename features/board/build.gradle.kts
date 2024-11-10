plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("be.bnp.katas.tictactoe.androidlibrary")
}

android {
    namespace = "be.bnp.katas.tictactoe.board"
}

dependencies {
    implementation(project(":modules:core"))
    implementation(project(":modules:data"))
    implementation(project(":modules:ui"))
    api(libs.koin.compose)

    testImplementation(libs.testing.koin)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.testing.coroutines)
    testImplementation(libs.testing.androidx.core)
}