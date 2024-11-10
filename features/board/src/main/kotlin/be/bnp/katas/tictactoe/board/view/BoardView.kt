package be.bnp.katas.tictactoe.board.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.board.viewmodel.PointClick
import be.bnp.katas.tictactoe.ui.GameStateText
import be.bnp.katas.tictactoe.ui.TicTacToeGrid
import org.koin.androidx.compose.koinViewModel

@Composable
fun BoardView(modifier: Modifier, viewModel: BoardViewModel = koinViewModel()) {
    val gameState = viewModel.game.collectAsState()

    when (val game = gameState.value) {
        is BoardViewModel.Game.GameIsHappening -> BoardView(modifier, game, viewModel::pointClicked)
        is BoardViewModel.Game.GameIsOver -> BoardView(modifier, game)
    }
}

@Composable
private fun BoardView(
    modifier: Modifier,
    game: BoardViewModel.Game.GameIsHappening,
    clickHandler: PointClick,
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TicTacToeGrid(game.points, boardSize = game.dimension, onClick = clickHandler::pointClicked)
        Spacer(Modifier.weight(1f))
        GameStateText(game.turnBy, Modifier.fillMaxWidth())
    }
}

@Composable
private fun BoardView(modifier: Modifier, gameIsOver: BoardViewModel.Game.GameIsOver) {
    Box(modifier.fillMaxSize()) {
        GameStateText(gameIsOver.gameState, Modifier.align(Alignment.Center))
    }
}