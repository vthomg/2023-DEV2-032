package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.Board
import be.bnp.katas.tictactoe.data.model.isEmpty

class BoardRepositoryImpl(
    boardSize: Int = DEFAULT_BOARD_SIZE,
) : BoardRepository {

    companion object {
        private const val DEFAULT_BOARD_SIZE = 3
    }

    private val _board = Board.createEmptyFor(boardSize)

    override val board: Board get() = _board

    override fun updatePoint(point: BoardPoint) {
        val (row, column) = point
        _board.updatePointFor(row, column, point)
    }

    override fun isPointValidForBoard(point: BoardPoint): Boolean {
        return point.row < _board.size && point.column < _board.size && _board.getPointFor(
            point.row,
            point.column
        ).isEmpty
    }

    override fun cleanBoard() {
    }
}