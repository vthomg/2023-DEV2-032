package be.bnp.katas.tictactoe.domain.model

sealed interface GameState {
    data class MakeATurn(val player: Player) : GameState

    data class Victory(val player: Player) : GameState

    data object Draw : GameState
}