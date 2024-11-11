package be.bnp.katas.tictactoe

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

interface AppInitKoinDelegate {
    fun Application.initKoinDelegated()

    companion object {
        fun create(): AppInitKoinDelegate {
            return AppInitKoinDelegateImpl()
        }
    }
}

private class AppInitKoinDelegateImpl : AppInitKoinDelegate {
    /**
     * If we go for a stable api, then please include here [be.bnp.katas.tictactoe.board.di.boardModuleDi]
     * Please see [be.bnp.katas.tictactoe.board.view.BoardView]
     */
    private val modules = listOf<Module>()

    override fun Application.initKoinDelegated() {
        startKoin {
            androidLogger()
            androidContext(this@initKoinDelegated)
            modules(modules)
        }
    }
}