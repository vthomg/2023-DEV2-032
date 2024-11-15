plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("be.bnp.katas.tictactoe.androidapplication")
}

android {
    namespace = "be.bnp.katas.tictactoe"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.modules.domain)
    implementation(projects.modules.data)
    implementation(projects.features.board)

    implementation(libs.androidx.core.ktx)
    implementation(libs.koin.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    testImplementation(libs.junit)
    testImplementation(libs.testing.koin)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}