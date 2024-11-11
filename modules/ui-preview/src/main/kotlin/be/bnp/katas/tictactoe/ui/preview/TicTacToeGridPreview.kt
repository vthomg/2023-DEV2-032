package be.bnp.katas.tictactoe.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.ui.TicTacToeGrid
import be.bnp.katas.tictactoe.ui.preview.tooling.TicTacToeGridPreviewParameterProvider

@Composable
@Preview(showSystemUi = true)
fun TicTacToeGridPreview(@PreviewParameter(TicTacToeGridPreviewParameterProvider::class) gridItems: List<BoardPoint>) {
    TicTacToeGrid(gridItems, boardSize = 3, onClick = { _, _ -> })
}