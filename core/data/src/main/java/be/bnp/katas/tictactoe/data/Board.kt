package be.bnp.katas.tictactoe.data

import be.bnp.katas.tictactoe.data.model.BoardPoint

@JvmInline
value class Board(val points: List<List<BoardPoint>>) {

    fun getPointFor(row: Int, column: Int): BoardPoint {
        throw NotImplementedError()
    }

    companion object {
        fun createEmptyFor(size: Int): Board {
            val points = MutableList(size) { row ->
                MutableList(size) { column ->
                    BoardPoint.createEmpty(
                        row = row,
                        column = column
                    )
                }
            }
            return Board(points)
        }
    }
}