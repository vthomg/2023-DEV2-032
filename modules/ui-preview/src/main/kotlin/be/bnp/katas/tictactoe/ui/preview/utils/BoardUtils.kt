package be.bnp.katas.tictactoe.ui.preview.utils

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.model.BoardPoints

/**
 * It's easier and faster to create test and understand board points position in a more human readable format
 * Convert human readable board representation to android operational representation
 * ,or
 * x,_,_
 * o,_,x
 * o,x,o to [BoardPoints]
 */
val String.asBoardPoints: BoardPoints
    get() {
        // "x,_,_","o,_,x","o,x,o"
        val rows = this.split('\n')
        return List(rows.size) { column ->
            List(rows.size) { row ->
                val pointState = rows[column].split(',')[row].asBoardPointState
                BoardPoint(column, row, pointState)
            }
        }
    }

val String.asBoardPointState: BoardPoint.State
    get() = when (lowercase()) {
        "x" -> BoardPoint.State.Cross
        "o" -> BoardPoint.State.Nought
        else -> BoardPoint.State.Empty
    }