package be.bnp.katas.tictactoe.data.usecase.reset

import be.bnp.katas.tictactoe.data.repository.BoardRepository

class ResetTheBoardUseCase(
    private val boardRepository: BoardRepository,
) {
    operator fun invoke() {
    }
}