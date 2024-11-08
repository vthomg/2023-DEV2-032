import be.bnp.katas.tictactoe.android.configureAndroid
import be.bnp.katas.tictactoe.kotlin.configureKotlin
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            extensions.configure<LibraryExtension> {
                configureAndroid(this)
                configureKotlin(this)
            }
        }
    }
}