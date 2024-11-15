package be.bnp.katas.tictactoe.ui.preview.tooling

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PointStatePreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf("X", "O", "")
}