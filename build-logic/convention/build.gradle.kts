plugins {
    `kotlin-dsl`
}

group = "be.bnp.katas.ticatactoe.buildlogic"

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "be.bnp.katas.tictactoe.androidapplication"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "be.bnp.katas.tictactoe.androidlibrary"
            implementationClass = "AndroidLibraryPlugin"
        }
    }
}
