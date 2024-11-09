package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.BoardPointsRepresentation

class BoardRepositoryImpl(
    boardSize: Int = DEFAULT_BOARD_SIZE,
) : BoardRepository {

    companion object {
        private const val DEFAULT_BOARD_SIZE = 3
    }

    private val _sheet = BoardPointsRepresentation.createEmptyFor(boardSize)

    override val sheet: BoardPointsRepresentation get() = _sheet

    override fun updatePoint(point: BoardPoint) {}

    override fun isPointValidForBoard(point: BoardPoint): Boolean {
        TODO("Not yet implemented")
    }

    override fun cleanBoard() {
    }
}