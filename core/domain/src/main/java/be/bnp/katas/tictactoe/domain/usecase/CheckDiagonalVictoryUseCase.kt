package be.bnp.katas.tictactoe.domain.usecase

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository

/**
 * Check if the user won for the diagonal
 * If there are 3 in a diagonal, then the user wins
 */
class CheckDiagonalVictoryUseCase(
    boardRepository: BoardRepository,
) {
    private val boardPoints = boardRepository.boardPoints

    operator fun invoke(point: BoardPoint): Boolean {
        return isTopLeftToBottomRightVictory(point) || isTopRightToBottomLeftVictory(point)
    }

    private fun isTopLeftToBottomRightVictory(point: BoardPoint): Boolean {
        for (diagonalIndex in boardPoints.indices) {
            val pointOnBoard = boardPoints[diagonalIndex][diagonalIndex]
            // Quit the loop, user did not won already since there is already different point
            if (pointOnBoard.state != BoardPoint.State.EMPTY && pointOnBoard.state != point.state) break
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
            if (pointOnBoard.state != BoardPoint.State.EMPTY && pointOnBoard.state != point.state) break
            // If iteration went till the last element with no breaks, user won
            else if (diagonalIndex == boardPoints.size - 1) return true
        }
        return false
    }
}