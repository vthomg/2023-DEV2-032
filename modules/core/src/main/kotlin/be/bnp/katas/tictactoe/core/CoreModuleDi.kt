package be.bnp.katas.tictactoe.core

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

internal val coreModuleDi = module {
    single {
        Dispatchers.IO
    }
}