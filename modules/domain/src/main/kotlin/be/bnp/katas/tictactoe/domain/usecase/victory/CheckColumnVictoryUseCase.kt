package be.bnp.katas.tictactoe.domain.usecase.victory

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository

/**
 * Check if the user won for the column
 * If there are 3 same turn points in a column, then we have a winner
 */
class CheckColumnVictoryUseCase(
    boardRepository: BoardRepository,
) : VictoryUseCase {
    private val boardPoints = boardRepository.boardPoints.toMutableList().map { it.toMutableList() }

    override operator fun invoke(point: BoardPoint): Boolean {
        val (row, column) = point
        boardPoints[row][column] = point

        for (rowIndex in boardPoints.indices) {
            val pointOnBoard = boardPoints[rowIndex][column]
            // Quit the loop, user did not won already since there is already different point
            if (pointOnBoard.state != point.state) break
            // If iteration went till the last element with no breaks, user won
            else if (rowIndex == boardPoints.size - 1) return true
        }
        return false
    }
}