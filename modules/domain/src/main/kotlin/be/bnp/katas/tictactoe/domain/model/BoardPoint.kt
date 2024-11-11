package be.bnp.katas.tictactoe.domain.model

import be.bnp.katas.tictactoe.domain.model.BoardPoint.State

data class BoardPoint(
    val row: Int,
    val column: Int,
    val state: State,
) {
    enum class State {
        CROSS {
            override fun toString(): String = "X"
        },
        NOUGHT {
            override fun toString(): String = "O"
        },
        EMPTY {
            override fun toString(): String = ""
        }
    }

    companion object {
        fun createEmpty(row: Int, column: Int) = BoardPoint(row, column, State.EMPTY)
    }
}

val BoardPoint.isEmpty: Boolean get() = state == State.EMPTY