package be.bnp.katas.tictactoe.data

import be.bnp.katas.tictactoe.data.model.BoardPoint

/**
 * 2D representation of the board.
 * The main purpose is to hold the points and serve operations on them
 */
@JvmInline
value class Board(private val _points: MutableList<MutableList<BoardPoint>>) {
    val points: List<List<BoardPoint>> get() = _points
    val size: Int get() = _points.size

    fun getPointFor(row: Int, column: Int): BoardPoint {
        return _points[row][column]
    }

    fun updatePointFor(row: Int, column: Int, point: BoardPoint) {
        _points[row][column] = point
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