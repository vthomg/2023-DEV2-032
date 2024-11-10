package be.bnp.katas.tictactoe.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import be.bnp.katas.tictactoe.ui.tooling.PointStatePreviewParameterProvider

@Composable
@Preview(widthDp = 200, heightDp = 200, showSystemUi = true)
fun TextualPointPreview(@PreviewParameter(PointStatePreviewParameterProvider::class) pointText: String) {
    TextualPoint(text = pointText, backgroundColor = Color.Blue, color = Color.White) {

    }
}