package be.bnp.katas.tictactoe.ui.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

enum class TestTags {
    GameStateText,
    TicTacToeGrid
}

internal fun Modifier.testTag(tag: TestTags) = testTag(tag.toString())