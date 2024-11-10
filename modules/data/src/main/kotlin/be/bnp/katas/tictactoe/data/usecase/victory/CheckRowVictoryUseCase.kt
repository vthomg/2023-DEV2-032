package be.bnp.katas.tictactoe.data.usecase.victory

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.repository.BoardRepository

/**
 * Check if the user won for the row
 * If there are 3 in a row, then the user wins
 */
class CheckRowVictoryUseCase(
    boardRepository: BoardRepository,
) : VictoryUseCase {
    private val boardPoints = boardRepository.boardPoints.toMutableList().map { it.toMutableList() }

    override operator fun invoke(point: BoardPoint): Boolean {
        val (row, column) = point
        boardPoints[row][column] = point

        for (columnIndex in boardPoints.indices) {
            val pointOnBoard = boardPoints[row][columnIndex]
            // Quit the loop, user did not won already since there is already different point
            if (pointOnBoard.state != point.state) break
            // If iteration went till the last element with no breaks, user won
            else if (columnIndex == boardPoints.size - 1) return true
        }
        return false
    }
}