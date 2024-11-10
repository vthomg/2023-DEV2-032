package be.bnp.katas.tictactoe.data.repository

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.model.BoardPoints

interface BoardRepository {
    val boardPoints: BoardPoints

    fun updatePoint(point: BoardPoint)

    /**
     * Normally this method should never return [false] unless there is a fault somewhere in the UI
     */
    fun isPointValidForBoard(point: BoardPoint): Boolean

    fun cleanBoard()
}