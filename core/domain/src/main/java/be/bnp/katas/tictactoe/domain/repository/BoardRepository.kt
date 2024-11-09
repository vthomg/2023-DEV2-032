package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.BoardPointsRepresentation

interface BoardRepository {
    val sheet: BoardPointsRepresentation

    fun updatePoint(point: BoardPoint)
    fun isPointValidForBoard(point: BoardPoint): Boolean
    fun cleanBoard()
}