package be.bnp.katas.tictactoe.ui.preview.tooling

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import be.bnp.katas.tictactoe.ui.GameState

class GameStatePreviewParameterProvider : PreviewParameterProvider<GameState> {
    override val values: Sequence<GameState> = sequenceOf(
        GameState.MakeATurn("X"),
        GameState.Victory("O"),
        GameState.Draw
    )
}