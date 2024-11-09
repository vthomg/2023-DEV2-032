package be.bnp.katas.tictactoe.data.model

import be.bnp.katas.tictactoe.data.model.BoardPoint.State

data class BoardPoint(
    val row: Int,
    val column: Int,
    val state: State,
) {
    enum class State {
        CROSS,
        NOUGHT,
        EMPTY,
    }

    fun withChangedState(newState: State): BoardPoint {
        return copy(state = newState)
    }

    companion object {
        fun createEmpty(x: Int, y: Int) = BoardPoint(x, y, State.EMPTY)
    }
}

val BoardPoint.isEmpty: Boolean get() = state == State.EMPTY