package be.bnp.katas.tictactoe.board.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.model.GameState
import be.bnp.katas.tictactoe.domain.model.Player
import be.bnp.katas.tictactoe.domain.repository.BoardRepository
import be.bnp.katas.tictactoe.domain.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.domain.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.VictoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal fun interface PointClick {
    fun pointClicked(row: Int, column: Int)
}

class BoardViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val boardRepository: BoardRepository,
    private val victoryUseCases: List<VictoryUseCase>,
    private val drawUseCase: CheckDrawUseCase,
    private val makeAMoveUseCase: MakeAMoveUseCase,
) : ViewModel(), PointClick {

    private companion object {
        val firstTurn = Player.Cross
    }

    private val _game = MutableStateFlow<Game>(
        Game.GameIsHappening(
            points = boardRepository.boardPoints.flatten(),
            dimension = boardRepository.boardSize,
            turnBy = GameState.MakeATurn(firstTurn)
        )
    )
    val game: StateFlow<Game> = _game.asStateFlow()

    private var currentUserTurn: Player = firstTurn

    override fun pointClicked(row: Int, column: Int) {
        viewModelScope.launch(dispatcher) {
            val point = BoardPoint(row, column, currentUserTurn.toPointState)
            return@launch when {
                !boardRepository.isPointValidForBoard(point) -> Unit

                victoryUseCases.any { it(point) } -> {
                    _game.value = Game.GameIsOver(GameState.Victory(currentUserTurn))
                }

                drawUseCase(point) -> {
                    _game.value = Game.GameIsOver(GameState.Draw)
                }

                makeAMoveUseCase(point) -> {
                    currentUserTurn = currentUserTurn.nextPlayer
                    _game.value = Game.GameIsHappening(
                        points = boardRepository.boardPoints.flatten(),
                        dimension = boardRepository.boardSize,
                        turnBy = GameState.MakeATurn(currentUserTurn)
                    )
                }

                else -> throw IllegalStateException("The point is valid, but it's not any known state. How did you get here?")
            }
        }
    }

    sealed interface Game {
        data class GameIsHappening(
            val points: List<BoardPoint>,
            val dimension: Int,
            val turnBy: GameState.MakeATurn,
        ) : Game

        data class GameIsOver(val gameState: GameState) : Game
    }
}

private val Player.nextPlayer: Player
    get() = when (this) {
        Player.Cross -> Player.Nought
        Player.Nought -> Player.Cross
    }

private val Player.toPointState: BoardPoint.State
    get() = when (this) {
        Player.Cross -> BoardPoint.State.Cross
        Player.Nought -> BoardPoint.State.Nought
    }