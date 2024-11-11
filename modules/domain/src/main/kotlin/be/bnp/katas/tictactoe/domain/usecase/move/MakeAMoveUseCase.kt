package be.bnp.katas.tictactoe.domain.usecase.move

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository

class MakeAMoveUseCase(
    private val boardRepository: BoardRepository,
) {
    operator fun invoke(point: BoardPoint): Boolean {
        if (!boardRepository.isPointValidForBoard(point)) return false

        boardRepository.updatePoint(point)
        return true
    }
}