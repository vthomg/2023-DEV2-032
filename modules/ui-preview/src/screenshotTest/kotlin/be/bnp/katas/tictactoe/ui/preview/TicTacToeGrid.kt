package be.bnp.katas.tictactoe.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.ui.preview.tooling.TicTacToeGridPreviewParameterProvider

@Composable
@Preview
fun TicTacToeGrid(@PreviewParameter(TicTacToeGridPreviewParameterProvider::class) gridItems: List<BoardPoint>) {
    TicTacToeGridPreview(gridItems)
}