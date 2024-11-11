package be.bnp.katas.tictactoe.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import be.bnp.katas.tictactoe.domain.model.GameState
import be.bnp.katas.tictactoe.ui.GameStateText
import be.bnp.katas.tictactoe.ui.preview.tooling.GameStatePreviewParameterProvider

@Composable
@Preview
fun GameStateTextPreview(@PreviewParameter(GameStatePreviewParameterProvider::class) gameState: GameState) {
    GameStateText(gameState)
}