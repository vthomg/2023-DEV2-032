package be.bnp.katas.tictactoe.domain.model

/**
 * 2D representation of the board.
 * The main purpose is to hold the points and serve operations on them
 */
@JvmInline
value class Board(
    private val _points: MutableList<MutableList<BoardPoint>>,
) {
    constructor(size: Int) : this(createEmptyPointsFor(size))

    val points: BoardPoints get() = _points
    val size: Int get() = _points.size

    fun getPointFor(row: Int, column: Int): BoardPoint {
        return _points[row][column]
    }

    fun updatePointFor(row: Int, column: Int, point: BoardPoint) {
        _points[row][column] = point
    }

    fun clear() {
        val size = _points.size
        _points.clear()
        _points.addAll(createEmptyPointsFor(size))
    }

    private companion object {
        fun createEmptyPointsFor(size: Int) = MutableList(size) { row ->
            MutableList(size) { column ->
                BoardPoint.createEmpty(
                    row = row,
                    column = column
                )
            }
        }
    }
}