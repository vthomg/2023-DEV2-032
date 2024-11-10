package be.bnp.katas.tictactoe.board

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.data.game.TicTacToeGameRulesImpl
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.data.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckRowVictoryUseCase
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class BoardViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        private lateinit var viewModel: BoardViewModel
    }

    @Before
    fun createBoardViewModel() {
        val repository = BoardRepositoryImpl()
        val victoryUseCases = listOf(
            CheckColumnVictoryUseCase(repository),
            CheckRowVictoryUseCase(repository),
            CheckDiagonalVictoryUseCase(repository)
        )
        viewModel = BoardViewModel(
            StandardTestDispatcher(),
            BoardRepositoryImpl(),
            TicTacToeGameRulesImpl(repository, victoryUseCases, CheckDrawUseCase(repository))
        )
    }

    @Test
    fun `Verify turn change is dispatched after placing point`() = runTest(StandardTestDispatcher()) {
            val expectedTurn = "O"

            viewModel.pointClicked(0, 1)
            val game = viewModel.game.value
            assert(game is BoardViewModel.Game.GameIsHappening)
            require(game is BoardViewModel.Game.GameIsHappening)
            assertEquals(expectedTurn, game.turnBy.user)
        }
}