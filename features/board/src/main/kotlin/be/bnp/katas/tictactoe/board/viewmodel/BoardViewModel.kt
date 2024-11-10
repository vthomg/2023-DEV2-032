package be.bnp.katas.tictactoe.board.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.bnp.katas.tictactoe.data.game.GameRules
import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.model.BoardPoints
import be.bnp.katas.tictactoe.data.repository.BoardRepository
import be.bnp.katas.tictactoe.ui.GameState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BoardViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val boardRepository: BoardRepository,
    private val gameRules: GameRules,
) : ViewModel() {

    private val _game = MutableStateFlow<Game>(
        Game.GameIsHappening(
            points = boardRepository.boardPoints,
            turnBy = GameState.MakeATurn(gameRules.currentUserTurn.toString())
        )
    )
    val game: StateFlow<Game> = _game.asStateFlow()

    fun pointClicked(row: Int, column: Int) {
        viewModelScope.launch(ioDispatcher) {
            val point = BoardPoint(row, column, gameRules.currentUserTurn)
            return@launch when {
                handleIllegalPoint(point) -> Unit
                handleUserWin(point) -> Unit
                handleDraw(point) -> Unit
                else -> handleNormalTurn(point)
            }
        }
    }

    private fun handleIllegalPoint(point: BoardPoint): Boolean {
        return !gameRules.isAllowedToPlacePoint(point)
    }

    private fun handleUserWin(point: BoardPoint): Boolean {
        if (gameRules.isUserWins(point)) {
            _game.value = Game.GameIsOver(GameState.Victory(gameRules.currentUserTurn.toString()))
            return true
        }
        return false
    }

    private fun handleDraw(point: BoardPoint): Boolean {
        if (gameRules.isDraw(point)) {
            _game.value = Game.GameIsOver(GameState.Draw)
            return true
        }
        return false
    }

    private fun handleNormalTurn(point: BoardPoint) {
        boardRepository.updatePoint(point)
        gameRules.moveToNextTurn(gameRules.currentUserTurn)
        _game.value = Game.GameIsHappening(
            boardRepository.boardPoints,
            GameState.MakeATurn(gameRules.currentUserTurn.toString())
        )
    }

    sealed interface Game {
        data class GameIsHappening(
            val points: BoardPoints,
            val turnBy: GameState.MakeATurn,
        ) : Game

        data class GameIsOver(val gameState: GameState) : Game
    }
}