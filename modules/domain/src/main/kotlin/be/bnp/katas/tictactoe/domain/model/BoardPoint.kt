package be.bnp.katas.tictactoe.domain.model

import be.bnp.katas.tictactoe.domain.model.BoardPoint.State

typealias BoardPoints = List<List<BoardPoint>>

data class BoardPoint(
    val row: Int,
    val column: Int,
    val state: State,
) {
    enum class State {
        Cross,
        Nought,
        Empty
    }

    companion object {
        fun createEmpty(row: Int, column: Int) = BoardPoint(row, column, State.Empty)
    }
}

val BoardPoint.isEmpty: Boolean get() = state == State.Empty