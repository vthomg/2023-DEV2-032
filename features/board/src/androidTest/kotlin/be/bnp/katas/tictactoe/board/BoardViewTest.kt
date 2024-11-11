package be.bnp.katas.tictactoe.board

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import be.bnp.katas.tictactoe.board.utils.onAllNodesWithTag
import be.bnp.katas.tictactoe.board.utils.onNodeWithTag
import be.bnp.katas.tictactoe.board.view.BoardView
import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.domain.model.Player
import be.bnp.katas.tictactoe.domain.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.domain.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckRowVictoryUseCase
import be.bnp.katas.tictactoe.ui.utils.TestTags
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BoardViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        fun createViewModel(): BoardViewModel {
            val repository = BoardRepositoryImpl()
            val victoryUseCases = listOf(
                CheckColumnVictoryUseCase(repository),
                CheckRowVictoryUseCase(repository),
                CheckDiagonalVictoryUseCase(repository)
            )
            return BoardViewModel(
                Dispatchers.Default,
                repository,
                victoryUseCases,
                CheckDrawUseCase(repository),
                MakeAMoveUseCase(repository)
            )
        }
    }

    @Test
    fun empty_board_is_shown() {
        composeTestRule.setContent {
            BoardView(Modifier, createViewModel())
        }

        composeTestRule.onNodeWithTag(TestTags.GameStateText).assertExists()

        composeTestRule.onNodeWithTag(TestTags.GameStateText).assertExists()
    }

    @Test
    fun point_is_changed_after_click() {
        composeTestRule.setContent {
            BoardView(Modifier, createViewModel())
        }

        composeTestRule.onAllNodesWithTag(TestTags.TicTacToeGridItem)
            .onFirst()
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onAllNodesWithTag(TestTags.TicTacToeGridItem)
            .onFirst()
            .assertTextEquals(Player.Cross.toString())
    }

    @Test
    fun turn_is_changed_after_click() {
        composeTestRule.setContent {
            BoardView(Modifier, createViewModel())
        }

        composeTestRule.onNodeWithTag(TestTags.GameStateText)
            .assertTextContains(Player.Cross.toString(), substring = true)

        composeTestRule.onAllNodesWithTag(TestTags.TicTacToeGridItem)
            .onLast()
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(TestTags.GameStateText)
            .assertTextContains(Player.Nought.toString(), substring = true)
    }
}