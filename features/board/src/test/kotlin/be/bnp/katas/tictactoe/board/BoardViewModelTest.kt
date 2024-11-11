package be.bnp.katas.tictactoe.board

import app.cash.turbine.test
import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.domain.model.Player
import be.bnp.katas.tictactoe.domain.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.domain.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckRowVictoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@OptIn(ExperimentalCoroutinesApi::class)
class BoardViewModelTest {

    companion object {
        private lateinit var viewModel: BoardViewModel

        private val testDispatcher = StandardTestDispatcher()
    }

    @Before
    fun setup() {
        val repository = BoardRepositoryImpl()
        val victoryUseCases = listOf(
            CheckColumnVictoryUseCase(repository),
            CheckRowVictoryUseCase(repository),
            CheckDiagonalVictoryUseCase(repository)
        )
        viewModel = BoardViewModel(
            testDispatcher,
            repository,
            victoryUseCases,
            CheckDrawUseCase(repository),
            MakeAMoveUseCase(repository)
        )
    }

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Verify turn change is dispatched after placing point`() = runTest {
        viewModel.game.drop(1).test {
            val expectedTurn = Player.Nought

            viewModel.pointClicked(0, 1)
            advanceUntilIdle()

            val game = awaitItem()

            assert(game is BoardViewModel.Game.GameIsHappening)
            require(game is BoardViewModel.Game.GameIsHappening)
            assertEquals(expectedTurn, game.turnBy.player)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Verify viewmodel checks if the point can be placed before changing the turn`() = runTest {
        viewModel.game.drop(1).test {
            viewModel.pointClicked(0, 20)
            advanceUntilIdle()

            expectNoEvents()
        }
    }

    @Test
    fun `Verify viewmodel updates the board`() = runTest {
        viewModel.game.test {
            val initialGameSetup = awaitItem()

            require(initialGameSetup is BoardViewModel.Game.GameIsHappening)
            val givenBoard = initialGameSetup.points

            viewModel.pointClicked(0, 1)
            advanceUntilIdle()

            val actualGameAfterClick = awaitItem()

            require(actualGameAfterClick is BoardViewModel.Game.GameIsHappening)
            assertNotEquals(givenBoard, actualGameAfterClick.points)
        }
    }
}