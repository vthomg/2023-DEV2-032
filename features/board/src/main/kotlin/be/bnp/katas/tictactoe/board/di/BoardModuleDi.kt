package be.bnp.katas.tictactoe.board.di

import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.domain.repository.BoardRepository
import be.bnp.katas.tictactoe.domain.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.domain.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.domain.usecase.reset.ResetTheBoardUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckRowVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.VictoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val boardModuleDi = module {
    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    viewModelOf(::BoardViewModel)

    single<BoardRepository> {
        BoardRepositoryImpl()
    }

    factory<List<VictoryUseCase>> {
        listOf(
            get<CheckColumnVictoryUseCase>(),
            get<CheckRowVictoryUseCase>(),
            get<CheckDiagonalVictoryUseCase>()
        )
    }

    factory<CheckColumnVictoryUseCase> {
        CheckColumnVictoryUseCase(get<BoardRepository>())
    }

    factory<CheckRowVictoryUseCase> {
        CheckRowVictoryUseCase(get<BoardRepository>())
    }

    factory<CheckDiagonalVictoryUseCase> {
        CheckDiagonalVictoryUseCase(get<BoardRepository>())
    }

    factory<CheckDrawUseCase> {
        CheckDrawUseCase(get<BoardRepository>())
    }

    factory<MakeAMoveUseCase> {
        MakeAMoveUseCase(get<BoardRepository>())
    }

    factory<ResetTheBoardUseCase> {
        ResetTheBoardUseCase(get<BoardRepository>())
    }
}