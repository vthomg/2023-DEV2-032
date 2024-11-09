package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.Board

interface BoardRepository {
    val sheet: Board

    fun updatePoint(point: BoardPoint)
    fun isPointValidForBoard(point: BoardPoint): Boolean
    fun cleanBoard()
}