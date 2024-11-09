package be.bnp.katas.tictactoe.domain.usecase

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository

/**
 * If the board is full and there is no winner, then it's a draw
 */
class CheckDrawUseCase(
    boardRepository: BoardRepository,
) {
    private val boardPoints = boardRepository.boardPoints.toMutableList().map { it.toMutableList() }

    /**
     * Should be called only after checking for victory
     */
    operator fun invoke(point: BoardPoint): Boolean {
        return false
    }
}