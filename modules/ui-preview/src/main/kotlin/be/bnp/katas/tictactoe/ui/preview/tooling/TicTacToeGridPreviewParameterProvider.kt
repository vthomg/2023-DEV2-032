package be.bnp.katas.tictactoe.ui.preview.tooling

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import be.bnp.katas.tictactoe.ui.TicTacToeGridItemData
import be.bnp.katas.tictactoe.ui.preview.utils.asGridItemData

class TicTacToeGridPreviewParameterProvider : PreviewParameterProvider<List<TicTacToeGridItemData>> {
    override val values: Sequence<List<TicTacToeGridItemData>> = sequenceOf(
        """
            o,_,x
            x,x,o
            _,o,_
        """.trimIndent().asGridItemData,
        """
            o,_,o
            _,x,_
            _,x,_
        """.trimIndent().asGridItemData,
        """
            o,_,o
            o,x,x
            x,x,o
        """.trimIndent().asGridItemData
    )
}