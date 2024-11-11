package be.bnp.katas.tictactoe.ui.preview.tooling

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.ui.preview.utils.asBoardPoints

class TicTacToeGridPreviewParameterProvider : PreviewParameterProvider<List<BoardPoint>> {
    override val values: Sequence<List<BoardPoint>> = sequenceOf(
        """
            o,_,x
            x,x,o
            _,o,_
        """.trimIndent().asBoardPoints.flatten(),
        """
            o,_,o
            _,x,_
            _,x,_
        """.trimIndent().asBoardPoints.flatten(),
        """
            o,_,o
            o,x,x
            x,x,o
        """.trimIndent().asBoardPoints.flatten()
    )
}