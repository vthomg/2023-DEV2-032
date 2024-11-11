package be.bnp.katas.tictactoe.board

import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.data.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.data.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckRowVictoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
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
    fun createBoardViewModel() {
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
        val expectedTurn = "O"

        viewModel.pointClicked(0, 1)
        val game = viewModel.game.value

        assert(game is BoardViewModel.Game.GameIsHappening)
        require(game is BoardViewModel.Game.GameIsHappening)
        assertEquals(expectedTurn, game.turnBy.user)
    }

    @Test
    fun `Verify viewmodel checks if the point can be placed before changing the turn`() = runTest {
        val expectedTurn = "X"

        viewModel.pointClicked(0, 1)
        val game = viewModel.game.value

        assert(game is BoardViewModel.Game.GameIsHappening)
        require(game is BoardViewModel.Game.GameIsHappening)
        assertEquals(expectedTurn, game.turnBy.user)
    }

    @Test
    fun `Verify viewmodel updates the board`() = runTest {
        val initialGameSetup = viewModel.game.value
        require(initialGameSetup is BoardViewModel.Game.GameIsHappening)
        val givenBoard = initialGameSetup.points

        viewModel.pointClicked(0, 1)
        val actualGameAfterClick = viewModel.game.value
        require(actualGameAfterClick is BoardViewModel.Game.GameIsHappening)

        assertNotEquals(givenBoard, actualGameAfterClick.points)
    }
}