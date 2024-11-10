package be.bnp.katas.tictactoe.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import be.bnp.katas.tictactoe.ui.TicTacToeGrid
import be.bnp.katas.tictactoe.ui.TicTacToePointState

private val previewBoard = listOf(
    listOf(
        TicTacToePointState.Empty,
        TicTacToePointState.Cross,
        TicTacToePointState.Cross,
    ),
    listOf(
        TicTacToePointState.Nought,
        TicTacToePointState.Nought,
        TicTacToePointState.Empty,
    ),
    listOf(
        TicTacToePointState.Nought,
        TicTacToePointState.Empty,
        TicTacToePointState.Empty,
    )
)

@Composable
@Preview(showSystemUi = true)
fun TicTacToeGridPreview(gridItems: List<List<TicTacToePointState>> = previewBoard) {
    TicTacToeGrid(gridItems, onClick = { _, _ -> })
}