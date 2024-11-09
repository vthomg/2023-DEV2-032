package be.bnp.katas.tictactoe.domain.usecase

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository

/**
 * Check if the user won for the column
 * If there are 3 in a column, then the user wins
 */
class IsColumnVictoryUseCase(
    boardRepository: BoardRepository,
) {
    private val boardPoints = boardRepository.boardPoints

    operator fun invoke(point: BoardPoint): Boolean {
        val (_, column) = point

        for (rowIndex in boardPoints.indices) {
            val pointOnBoard = boardPoints[rowIndex][column]
            // Quit the loop, user did not won already since there is already different point
            if (pointOnBoard.state != BoardPoint.State.EMPTY && pointOnBoard.state != point.state) break
            // If iteration went till the last element with no breaks, user won
            else if (rowIndex == boardPoints.size - 1) return true
        }
        return false
    }
}