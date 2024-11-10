package be.bnp.katas.tictactoe.data.game

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.model.isEmpty
import be.bnp.katas.tictactoe.data.repository.BoardRepository
import be.bnp.katas.tictactoe.data.usecase.draw.DrawUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.VictoryUseCase

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
        _currentUserTurn = determineNextTurn(lastTurn)
    }

    override fun isDraw(point: BoardPoint): Boolean {
        return drawUseCase(point)
    }

    override fun isAllowedToPlacePoint(point: BoardPoint): Boolean {
        val (row, column) = point
        // According to rules it's allowed to place point only if the position is empty
        return boardRepository.boardPoints[row][column].isEmpty
    }

    override fun reset() {
        _currentUserTurn = firstTurn
        boardRepository.cleanBoard()
    }

    private fun determineNextTurn(turn: BoardPoint.State): BoardPoint.State {
        return when (turn) {
            BoardPoint.State.CROSS -> BoardPoint.State.NOUGHT
            BoardPoint.State.NOUGHT -> BoardPoint.State.CROSS
            else -> BoardPoint.State.CROSS
        }
    }
}