package be.bnp.katas.tictactoe.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import be.bnp.katas.tictactoe.ui.preview.tooling.PointStatePreviewParameterProvider

@Composable
@Preview(widthDp = 200, heightDp = 200)
fun TextualPoint(@PreviewParameter(PointStatePreviewParameterProvider::class) pointText: String) {
    TextualPointPreview(pointText)
}