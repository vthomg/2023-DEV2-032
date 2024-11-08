package be.bnp.katas.tictactoe.android

import be.bnp.katas.tictactoe.versionCatalog
import be.bnp.katas.tictactoe.versionString
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

internal fun Project.configureAndroid(
    extension: CommonExtension<*, *, *, *, *, *>,
) {
    extension.apply {
        compileSdk = versionCatalog.versionString("android-compileSdk").toInt()

        with(defaultConfig) {
            compileSdk = versionCatalog.versionString("android-compileSdk").toInt()
            minSdk = versionCatalog.versionString("android-minSdk").toInt()
        }
        if (extension is ApplicationExtension) {
            extension.configure(versionCatalog)
        } else if (extension is LibraryExtension) {
            extension.configure(versionCatalog)
        }
        kotlinExtension.jvmToolchain(17)
    }
}

private fun ApplicationExtension.configure(versionCatalog: VersionCatalog) {
    defaultConfig {
        targetSdk = versionCatalog.versionString("android-targetSdk").toInt()
        multiDexEnabled = true
    }
}

private fun LibraryExtension.configure(versionCatalog: VersionCatalog) {
    testOptions.unitTests.isIncludeAndroidResources = true
    defaultConfig {
        lint {
            targetSdk = versionCatalog.versionString("android-targetSdk").toInt()
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "./proguard-rules.pro"
            )
            consumerProguardFiles("./proguard-rules.pro")
        }
    }
}