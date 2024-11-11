package be.bnp.katas.tictactoe.domain.repository

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.model.BoardPoints

interface BoardRepository {
    val boardSize: Int

    val boardPoints: BoardPoints

    fun updatePoint(point: BoardPoint)

    /**
     * Normally this method should never return [false] unless there is a fault somewhere in the UI
     * Because that means that you let the user click on the point which is not valid for the board
     */
    fun isPointValidForBoard(point: BoardPoint): Boolean

    fun cleanBoard()
}