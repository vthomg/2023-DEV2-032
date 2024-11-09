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
        return false
    }
}