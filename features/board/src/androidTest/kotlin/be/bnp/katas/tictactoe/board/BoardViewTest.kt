package be.bnp.katas.tictactoe.board

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import be.bnp.katas.tictactoe.board.utils.onAllNodesWithTag
import be.bnp.katas.tictactoe.board.utils.onNodeWithTag
import be.bnp.katas.tictactoe.board.view.BoardView
import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.domain.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.domain.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckRowVictoryUseCase
import be.bnp.katas.tictactoe.ui.utils.TestTags
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BoardViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private lateinit var viewModel: BoardViewModel
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
            Dispatchers.Default,
            repository,
            victoryUseCases,
            CheckDrawUseCase(repository),
            MakeAMoveUseCase(repository)
        )
    }

    @Test
    fun empty_board_is_shown() {
        // Start the composable under test
        composeTestRule.setContent {
            BoardView(Modifier, viewModel)
        }

        // Verify initial text is displayed
        composeTestRule.onNodeWithTag(TestTags.GameStateText).assertExists()

        // The board is shown
        composeTestRule.onNodeWithTag(TestTags.GameStateText).assertExists()
    }

    @Test
    fun point_is_changed_after_click() {
        // Start the composable under test
        composeTestRule.setContent {
            BoardView(Modifier, viewModel)
        }

        // Verify initial text is displayed
        composeTestRule.onAllNodesWithTag(TestTags.TicTacToeGridItem).onFirst().performClick()

        // The board is shown
        composeTestRule.onAllNodesWithTag(TestTags.TicTacToeGridItem).onFirst().assertTextEquals("X")
    }
}