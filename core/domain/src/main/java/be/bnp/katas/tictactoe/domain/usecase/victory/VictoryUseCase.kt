package be.bnp.katas.tictactoe.domain.usecase.victory

import be.bnp.katas.tictactoe.data.model.BoardPoint

interface VictoryUseCase {
    operator fun invoke(point: BoardPoint): Boolean
}