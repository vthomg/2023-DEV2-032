package be.bnp.katas.tictactoe.board.mapper

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.model.BoardPoints
import be.bnp.katas.tictactoe.ui.TicTacToeGridItemData
import be.bnp.katas.tictactoe.ui.TicTacToePointState

internal val BoardPoint.State.asTicTacToePointState: TicTacToePointState
    get() = when (this) {
        BoardPoint.State.CROSS -> TicTacToePointState.Cross
        BoardPoint.State.NOUGHT -> TicTacToePointState.Nought
        BoardPoint.State.EMPTY -> TicTacToePointState.Empty
    }

internal val BoardPoint.asTicTacToeGridItemData: TicTacToeGridItemData
    get() = TicTacToeGridItemData(
        row = row,
        column = column,
        state = state.asTicTacToePointState
    )

internal val BoardPoints.asTicTacToeGridItemData: List<TicTacToeGridItemData>
    get() = flatten().map { it.asTicTacToeGridItemData }