package be.bnp.katas.tictactoe.data.game

import be.bnp.katas.tictactoe.data.model.BoardPoint

interface GameRules {
    val currentUserTurn: BoardPoint.State
    fun isUserWins(point: BoardPoint): Boolean
    fun moveToNextTurn(lastTurn: BoardPoint.State)
    fun isDraw(point: BoardPoint): Boolean
    fun isAllowedToPlacePoint(point: BoardPoint): Boolean

    // Reset game rules to defaults
    fun reset()
}