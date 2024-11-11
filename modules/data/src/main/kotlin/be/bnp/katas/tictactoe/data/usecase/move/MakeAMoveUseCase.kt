package be.bnp.katas.tictactoe.data.usecase.move

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.repository.BoardRepository

class MakeAMoveUseCase(
    private val boardRepository: BoardRepository,
) {
    operator fun invoke(point: BoardPoint) {
        boardRepository.updatePoint(point)
    }
}