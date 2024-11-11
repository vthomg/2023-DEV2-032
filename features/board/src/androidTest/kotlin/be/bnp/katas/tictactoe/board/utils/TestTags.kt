package be.bnp.katas.tictactoe.board.utils

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import be.bnp.katas.tictactoe.ui.utils.TestTags

internal fun SemanticsNodeInteractionsProvider.onNodeWithTag(tag: TestTags) =
    onNodeWithTag(tag.toString())

internal fun SemanticsNodeInteractionsProvider.onAllNodesWithTag(tag: TestTags) =
    onAllNodesWithTag(tag.toString())