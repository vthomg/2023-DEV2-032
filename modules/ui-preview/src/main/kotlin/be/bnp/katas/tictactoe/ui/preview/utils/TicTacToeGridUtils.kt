package be.bnp.katas.tictactoe.ui.preview.utils

import be.bnp.katas.tictactoe.ui.TicTacToeGridItemData
import be.bnp.katas.tictactoe.ui.TicTacToePointState

/**
 * It's easier and faster to create test and understand board points position in a more human readable format
 * Convert human readable board representation to android operational representation
 * ,or
 * x,_,_
 * o,_,x
 * o,x,o to [TicTacToeGridItemData]
 */
val String.asGridItemData: List<TicTacToeGridItemData>
    get() {
        // "x,_,_","o,_,x","o,x,o"
        val rows = this.split('\n')
        return List(rows.size) { column ->
            List(rows.size) { row ->
                val pointState = rows[column].split(',')[row].asBoardPointState
                TicTacToeGridItemData(column, row, pointState)
            }
        }.flatten()
    }

private val String.asBoardPointState: TicTacToePointState
    get() = when (lowercase()) {
        "x" -> TicTacToePointState.Cross
        "o" -> TicTacToePointState.Nought
        else -> TicTacToePointState.Empty
    }