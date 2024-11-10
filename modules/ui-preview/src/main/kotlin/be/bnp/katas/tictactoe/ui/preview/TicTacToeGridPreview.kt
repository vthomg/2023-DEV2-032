package be.bnp.katas.tictactoe.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import be.bnp.katas.tictactoe.ui.TicTacToeGrid
import be.bnp.katas.tictactoe.ui.TicTacToeGridItemData

private val previewBoard = listOf(
    listOf(
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Empty),
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Cross),
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Cross),
    ),
    listOf(
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Nought),
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Nought),
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Empty),
    ),
    listOf(
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Nought),
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Empty),
        TicTacToeGridItemData(TicTacToeGridItemData.PointState.Empty),
    )
)

@Composable
@Preview(showSystemUi = true)
fun TicTacToeGridPreview(gridItems: List<List<TicTacToeGridItemData>> = previewBoard) {
    TicTacToeGrid(gridItems, onClick = { _, _ -> })
}