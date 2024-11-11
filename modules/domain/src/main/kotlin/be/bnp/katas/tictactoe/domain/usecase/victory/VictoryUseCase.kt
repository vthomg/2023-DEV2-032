package be.bnp.katas.tictactoe.domain.usecase.victory

import be.bnp.katas.tictactoe.domain.model.BoardPoint

/**
 * One might say the interface is not needed. And I see the point in that statement.
 * But I enjoy the option to abstract victory rules, so we can alter the Tic-Tac-Toe rules
 */
interface VictoryUseCase {
    operator fun invoke(point: BoardPoint): Boolean
}