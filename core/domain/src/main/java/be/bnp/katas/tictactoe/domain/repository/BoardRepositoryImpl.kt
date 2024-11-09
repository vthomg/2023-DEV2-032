package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.Board
import be.bnp.katas.tictactoe.data.BoardPoints
import be.bnp.katas.tictactoe.data.model.isEmpty

class BoardRepositoryImpl(
    boardSize: Int = DEFAULT_BOARD_SIZE,
) : BoardRepository {

    companion object {
        private const val DEFAULT_BOARD_SIZE = 3
    }

    private val board: Board = Board.createEmptyFor(boardSize)

    override val boardPoints: BoardPoints get() = board.points

    override fun updatePoint(point: BoardPoint) {
        if (!isPointValidForBoard(point)) return
        val (row, column) = point
        board.updatePointFor(row, column, point)
    }

    override fun isPointValidForBoard(point: BoardPoint): Boolean {
        return point.row < board.size && point.column < board.size && board.getPointFor(
            point.row,
            point.column
        ).isEmpty
    }

    override fun cleanBoard() {
    }
}