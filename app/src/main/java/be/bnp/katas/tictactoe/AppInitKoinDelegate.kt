package be.bnp.katas.tictactoe

import android.app.Application
import be.bnp.katas.tictactoe.board.di.boardModuleDi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

interface AppInitKoinDelegate {
    fun Application.initKoinDelegated()

    companion object {
        fun create(): AppInitKoinDelegate {
            return AppInitKoinDelegateImpl()
        }
    }
}

private class AppInitKoinDelegateImpl : AppInitKoinDelegate {
    override fun Application.initKoinDelegated() {
        startKoin {
            androidLogger()
            androidContext(this@initKoinDelegated)
            modules(
                boardModuleDi
            )
        }
    }
}