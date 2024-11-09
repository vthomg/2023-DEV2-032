package be.bnp.katas.tictactoe.domain.game

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository
import be.bnp.katas.tictactoe.domain.usecase.draw.DrawUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.VictoryUseCase

class GameRulesImpl(
    private val boardRepository: BoardRepository,
    private val victoryCases: List<VictoryUseCase>,
    private val drawUseCase: DrawUseCase,
    private val firstTurn: BoardPoint.State = BoardPoint.State.CROSS,
) : GameRules {

    private var _currentUserTurn: BoardPoint.State = firstTurn
    override val currentUserTurn: BoardPoint.State
        get() = _currentUserTurn

    override fun isUserWins(point: BoardPoint): Boolean {
        return victoryCases.any { case -> case(point) }
    }

    override fun moveToNextTurn(lastTurn: BoardPoint.State) {
        TODO("Not yet implemented")
    }

    override fun isDraw(point: BoardPoint): Boolean {
        return drawUseCase(point)
    }

    override fun isAllowedToPlacePoint(point: BoardPoint): Boolean {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}