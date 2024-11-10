package be.bnp.katas.tictactoe.data.usecase.draw

import be.bnp.katas.tictactoe.data.model.BoardPoint

interface DrawUseCase {
    operator fun invoke(point: BoardPoint): Boolean
}