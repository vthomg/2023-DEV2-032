package be.bnp.katas.tictactoe.domain.usecase.reset

import be.bnp.katas.tictactoe.domain.repository.BoardRepository

class ResetTheBoardUseCase(
    private val boardRepository: BoardRepository,
) {
    operator fun invoke() {
        boardRepository.cleanBoard()
    }
}