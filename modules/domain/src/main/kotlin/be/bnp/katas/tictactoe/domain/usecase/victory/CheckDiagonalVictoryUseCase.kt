package be.bnp.katas.tictactoe.domain.usecase.victory

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository

/**
 * Check if the user won for the diagonal
 * If there are 3 in a diagonal, then the user wins
 */
class CheckDiagonalVictoryUseCase(
    boardRepository: BoardRepository,
) : VictoryUseCase {
    private val boardPoints = boardRepository.boardPoints.toMutableList().map { it.toMutableList() }

    override operator fun invoke(point: BoardPoint): Boolean {
        val (row, column) = point
        boardPoints[row][column] = point

        return isTopLeftToBottomRightVictory(point) || isTopRightToBottomLeftVictory(point)
    }

    private fun isTopLeftToBottomRightVictory(point: BoardPoint): Boolean {
        for (diagonalIndex in boardPoints.indices) {
            val pointOnBoard = boardPoints[diagonalIndex][diagonalIndex]
            // Quit the loop, user did not won already since there is already different point
            if (pointOnBoard.state != point.state) break
            // If iteration went till the last element with no breaks, user won
            else if (diagonalIndex == boardPoints.size - 1) return true
        }
        return false
    }

    private fun isTopRightToBottomLeftVictory(point: BoardPoint): Boolean {
        for (diagonalIndex in boardPoints.indices.reversed()) {
            val pointOnBoard =
                boardPoints[boardPoints.size - 1 - diagonalIndex][diagonalIndex]
            // Quit the loop, user did not won already since there is already different point
            if (pointOnBoard.state != point.state) break
            // If iteration went till the last element with no breaks, user won
            else if (diagonalIndex == 0) return true
        }
        return false
    }
}