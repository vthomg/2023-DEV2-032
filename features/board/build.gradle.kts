plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("be.bnp.katas.tictactoe.androidlibrary")
}

android {
    namespace = "be.bnp.katas.tictactoe.board"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.modules.domain)
    implementation(projects.modules.data)
    implementation(projects.modules.ui)

    implementation(libs.koin.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.testing.koin)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.testing.coroutines)
    testImplementation(libs.testing.androidx.core)
    testImplementation(libs.testing.turbine)
}