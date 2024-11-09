package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.BoardPointsRepresentation

class BoardRepositoryImpl(
    private val boardSize: Int = DEFAULT_BOARD_SIZE,
) : BoardRepository {

    companion object {
        private const val DEFAULT_BOARD_SIZE = 3
    }

    override val sheet: BoardPointsRepresentation
        get() = TODO("Not yet implemented")

    override fun updatePoint(point: BoardPoint) {}

    override fun isPointValidForBoard(point: BoardPoint): Boolean {
        TODO("Not yet implemented")
    }

    override fun cleanBoard() {
    }
}