package be.bnp.katas.tictactoe.domain.utils

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

val BoardPoints.asString: String
    get() = buildString {
        this@asString.forEach { column ->
            append(column.map { it.state.asHumanReadable }.reduce { acc, s -> "$acc,$s" })
            append("\n")
        }
    }.trimIndent()

val String.asBoardPointState: BoardPoint.State
    get() = when (lowercase()) {
        "x" -> BoardPoint.State.Cross
        "o" -> BoardPoint.State.Nought
        else -> BoardPoint.State.Empty
    }

val BoardPoint.State.asHumanReadable: String
    get() = when (this) {
        BoardPoint.State.Empty -> "_"
        BoardPoint.State.Nought -> "o"
        BoardPoint.State.Cross -> "x"
    }