package be.bnp.katas.tictactoe.domain.usecase.draw

import be.bnp.katas.tictactoe.domain.model.BoardPoint

interface DrawUseCase {
    operator fun invoke(point: BoardPoint): Boolean
}