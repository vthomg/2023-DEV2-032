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
    api(libs.koin.compose)

    testImplementation(libs.testing.koin)
}