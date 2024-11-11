package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.model.BoardPoints

interface BoardRepository {
    val boardSize: Int

    val boardPoints: BoardPoints

    fun updatePoint(point: BoardPoint)

    /**
     * Normally this method should never return [false] unless there is a fault somewhere in the UI
     */
    fun isPointValidForBoard(point: BoardPoint): Boolean

    fun cleanBoard()
}