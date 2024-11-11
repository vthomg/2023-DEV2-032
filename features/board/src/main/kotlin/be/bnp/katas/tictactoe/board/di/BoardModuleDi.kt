package be.bnp.katas.tictactoe.board.di

import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.data.repository.BoardRepository
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.data.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.data.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.data.usecase.reset.ResetTheBoardUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckRowVictoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val boardModuleDi = module {
    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    viewModel {
        BoardViewModel(
            get<CoroutineDispatcher>(),
            get<BoardRepository>(),
            listOf(
                get<CheckColumnVictoryUseCase>(),
                get<CheckRowVictoryUseCase>(),
                get<CheckDiagonalVictoryUseCase>()
            ),
            get<CheckDrawUseCase>(),
            get<MakeAMoveUseCase>(),
        )
    }

    single<BoardRepository> {
        BoardRepositoryImpl()
    }

    factory {
        CheckColumnVictoryUseCase(get<BoardRepository>())
    }

    factory {
        CheckRowVictoryUseCase(get<BoardRepository>())
    }

    factory {
        CheckDiagonalVictoryUseCase(get<BoardRepository>())
    }

    factory {
        CheckDrawUseCase(get<BoardRepository>())
    }

    factory {
        MakeAMoveUseCase(get<BoardRepository>())
    }

    factory {
        ResetTheBoardUseCase(get<BoardRepository>())
    }
}