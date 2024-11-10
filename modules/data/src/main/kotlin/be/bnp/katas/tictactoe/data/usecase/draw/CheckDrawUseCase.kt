package be.bnp.katas.tictactoe.data.usecase.draw

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.model.isEmpty
import be.bnp.katas.tictactoe.data.repository.BoardRepository

/**
 * If the board is full and there is no winner, then it's a draw
 */
class CheckDrawUseCase(
    boardRepository: BoardRepository,
) : DrawUseCase {
    private val boardPoints = boardRepository.boardPoints.toMutableList().map { it.toMutableList() }

    /**
     * Should be called only after checking for victory
     */
    override operator fun invoke(point: BoardPoint): Boolean {
        val (row, column) = point
        boardPoints[row][column] = point

        return !boardPoints.flatten().any { it.isEmpty }
    }
}