package be.bnp.katas.tictactoe.domain.game

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository
import be.bnp.katas.tictactoe.domain.usecase.draw.DrawUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.VictoryUseCase

class GameRulesImpl(
    private val boardRepository: BoardRepository,
    private val victoryCases: List<VictoryUseCase>,
    private val drawUseCase: DrawUseCase,
    private val initialTurn: BoardPoint.State = BoardPoint.State.CROSS,
) : GameRules {
    override val currentUserTurn: BoardPoint.State
        get() = initialTurn

    override fun isUserWins(point: BoardPoint): Boolean {
        TODO("Not yet implemented")
    }

    override fun moveToNextTurn(lastTurn: BoardPoint.State) {
        TODO("Not yet implemented")
    }

    override fun isDraw(point: BoardPoint): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAllowedToPlacePoint(point: BoardPoint): Boolean {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}