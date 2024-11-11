package be.bnp.katas.tictactoe.domain.usecase.draw

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.model.isEmpty
import be.bnp.katas.tictactoe.domain.repository.BoardRepository

/**
 * If the board is full and there is no winner, then it's a draw
 */
class CheckDrawUseCase(
    boardRepository: BoardRepository,
) {
    private val boardPoints = boardRepository.boardPoints.toMutableList().map { it.toMutableList() }

    operator fun invoke(point: BoardPoint): Boolean {
        val (row, column) = point
        boardPoints[row][column] = point

        return !boardPoints.flatten().any { it.isEmpty }
    }
}