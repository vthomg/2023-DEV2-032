package be.bnp.katas.tictactoe.ui.preview.tooling

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import be.bnp.katas.tictactoe.domain.model.GameState
import be.bnp.katas.tictactoe.domain.model.Player

class GameStatePreviewParameterProvider : PreviewParameterProvider<GameState> {
    override val values: Sequence<GameState> = sequenceOf(
        GameState.MakeATurn(Player.Cross),
        GameState.Victory(Player.Nought),
        GameState.Draw
    )
}