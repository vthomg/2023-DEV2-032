package be.bnp.katas.tictactoe.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coreModuleDi = module {
    single<CoroutineDispatcher> {
        Dispatchers.IO
    }
}