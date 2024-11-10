package be.bnp.katas.tictactoe.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

sealed interface GameState {
    data class MakeATurn(val user: String) : GameState

    data class Victory(val user: String) : GameState

    data object Draw : GameState
}

@Composable
fun GameStateText(gameState: GameState, modifier: Modifier = Modifier) {
    Text(
        gameState.text,
        modifier = modifier,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}

private val GameState.text: String
    @Composable get() = when (this) {
        is GameState.MakeATurn -> LocalContext.current.getString(R.string.make_a_turn, user)
        is GameState.Victory -> LocalContext.current.getString(R.string.victory, user)
        is GameState.Draw -> LocalContext.current.getString(R.string.draw)
    }