package be.bnp.katas.tictactoe.domain.model

enum class Player {
    Cross {
        override fun toString(): String = "X"
    },
    Nought {
        override fun toString(): String = "O"
    }
}