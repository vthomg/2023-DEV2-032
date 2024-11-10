package be.bnp.katas.tictactoe.board.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.bnp.katas.tictactoe.data.game.GameRules
import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.model.BoardPoints
import be.bnp.katas.tictactoe.data.repository.BoardRepository
import be.bnp.katas.tictactoe.ui.GameState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            if (!gameRules.isAllowedToPlacePoint(point)) return@launch

            swapUserTurn()
        }
    }

    private suspend fun swapUserTurn() {
        gameRules.moveToNextTurn(gameRules.currentUserTurn)
        withContext(Dispatchers.Default) {
            _game.value = Game.GameIsHappening(
                boardRepository.boardPoints,
                GameState.MakeATurn(gameRules.currentUserTurn.toString())
            )
        }
    }

    sealed interface Game {
        data class GameIsHappening(
            val points: BoardPoints,
            val turnBy: GameState.MakeATurn,
        ) : Game

        data class GameIsOver(val gameState: GameState) : Game
    }
}