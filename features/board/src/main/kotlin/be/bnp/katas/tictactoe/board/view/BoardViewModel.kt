package be.bnp.katas.tictactoe.board.view

import androidx.lifecycle.ViewModel
import be.bnp.katas.tictactoe.data.game.GameRules
import be.bnp.katas.tictactoe.data.repository.BoardRepository

class BoardViewModel(
    private val gameRules: GameRules,
    private val boardRepository: BoardRepository,
) : ViewModel() {
}