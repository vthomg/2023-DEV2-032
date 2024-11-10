package be.bnp.katas.tictactoe.board.di

import be.bnp.katas.tictactoe.board.view.BoardViewModel
import be.bnp.katas.tictactoe.data.game.GameRules
import be.bnp.katas.tictactoe.data.game.TicTacToeGameRulesImpl
import be.bnp.katas.tictactoe.data.repository.BoardRepository
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.data.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckRowVictoryUseCase
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val boardModuleDi = module {
    viewModelOf(::BoardViewModel)

    single<BoardRepository> {
        BoardRepositoryImpl()
    }

    single<GameRules> {
        val repository = get<BoardRepository>()

        TicTacToeGameRulesImpl(
            repository,
            listOf(
                CheckColumnVictoryUseCase(repository),
                CheckRowVictoryUseCase(repository),
                CheckDiagonalVictoryUseCase(repository)
            ),
            CheckDrawUseCase(repository)
        )
    }
}