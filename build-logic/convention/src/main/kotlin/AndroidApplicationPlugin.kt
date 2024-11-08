import be.bnp.katas.tictactoe.android.configureAndroid
import be.bnp.katas.tictactoe.kotlin.configureKotlin
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                configureAndroid(this)
                configureKotlin(this)
            }
        }
    }
}