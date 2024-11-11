package be.bnp.katas.tictactoe.board.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.bnp.katas.tictactoe.board.mapper.asTicTacToeGridItemData
import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.repository.BoardRepository
import be.bnp.katas.tictactoe.data.usecase.draw.DrawUseCase
import be.bnp.katas.tictactoe.data.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.VictoryUseCase
import be.bnp.katas.tictactoe.ui.GameState
import be.bnp.katas.tictactoe.ui.TicTacToeGridItemData
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
    private val drawUseCase: DrawUseCase,
    private val makeAMoveUseCase: MakeAMoveUseCase,
    firstTurn: BoardPoint.State = BoardPoint.State.CROSS,
) : ViewModel(), PointClick {

    private val _game = MutableStateFlow<Game>(
        Game.GameIsHappening(
            points = boardRepository.boardPoints.asTicTacToeGridItemData,
            dimension = boardRepository.boardSize,
            turnBy = GameState.MakeATurn(firstTurn.toString())
        )
    )
    val game: StateFlow<Game> = _game.asStateFlow()

    private var currentUserTurn: BoardPoint.State = firstTurn

    override fun pointClicked(row: Int, column: Int) {
        viewModelScope.launch(dispatcher) {
            val point = BoardPoint(row, column, currentUserTurn)
            return@launch when {
                !boardRepository.isPointValidForBoard(point) -> Unit

                victoryUseCases.any { it(point) } -> {
                    _game.value = Game.GameIsOver(GameState.Victory(currentUserTurn.toString()))
                }

                drawUseCase(point) -> {
                    _game.value = Game.GameIsOver(GameState.Draw)
                }

                makeAMoveUseCase(point) -> {
                    moveToNextTurn()
                    _game.value = Game.GameIsHappening(
                        points = boardRepository.boardPoints.asTicTacToeGridItemData,
                        dimension = boardRepository.boardSize,
                        turnBy = GameState.MakeATurn(currentUserTurn.toString())
                    )
                }

                else -> throw IllegalStateException("The point is valid, but it's not any known state. How did you get here?")
            }
        }
    }

    private fun moveToNextTurn() {
        currentUserTurn = when (currentUserTurn) {
            BoardPoint.State.CROSS -> BoardPoint.State.NOUGHT
            BoardPoint.State.NOUGHT -> BoardPoint.State.CROSS
            else -> BoardPoint.State.CROSS
        }
    }

    sealed interface Game {
        data class GameIsHappening(
            val points: List<TicTacToeGridItemData>,
            val dimension: Int,
            val turnBy: GameState.MakeATurn,
        ) : Game

        data class GameIsOver(val gameState: GameState) : Game
    }
}