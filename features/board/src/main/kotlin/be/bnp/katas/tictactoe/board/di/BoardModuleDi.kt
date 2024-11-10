package be.bnp.katas.tictactoe.board.di

import be.bnp.katas.tictactoe.board.view.BoardViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val boardModuleDi = module {
    viewModelOf(::BoardViewModel)
}