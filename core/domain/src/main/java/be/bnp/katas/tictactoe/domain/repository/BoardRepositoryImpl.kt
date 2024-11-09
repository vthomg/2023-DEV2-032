package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.Board

class BoardRepositoryImpl(
    boardSize: Int = DEFAULT_BOARD_SIZE,
) : BoardRepository {

    companion object {
        private const val DEFAULT_BOARD_SIZE = 3
    }

    private val _sheet = Board.createEmptyFor(boardSize)

    override val sheet: Board get() = _sheet

    override fun updatePoint(point: BoardPoint) {}

    override fun isPointValidForBoard(point: BoardPoint): Boolean {
        TODO("Not yet implemented")
    }

    override fun cleanBoard() {
    }
}