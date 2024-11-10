package be.bnp.katas.tictactoe.data.usecase.victory

import be.bnp.katas.tictactoe.data.model.BoardPoint

interface VictoryUseCase {
    operator fun invoke(point: BoardPoint): Boolean
}