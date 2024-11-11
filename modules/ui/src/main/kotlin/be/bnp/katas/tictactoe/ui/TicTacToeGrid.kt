package be.bnp.katas.tictactoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.ui.utils.TestTags
import be.bnp.katas.tictactoe.ui.utils.testTag

object TicTacToeGridDefaults {
    val verticalSpacing: Dp = 8.dp
    val horizontalSpacing: Dp = 8.dp
}

@Composable
fun TicTacToeGrid(
    gridItems: List<BoardPoint>,
    boardSize: Int,
    onClick: (row: Int, column: Int) -> Unit,
    modifier: Modifier = Modifier,
    verticalSpacing: Dp = TicTacToeGridDefaults.verticalSpacing,
    horizontalSpacing: Dp = TicTacToeGridDefaults.horizontalSpacing,
) {
    LazyVerticalGrid(
        modifier = modifier.testTag(TestTags.TicTacToeGrid),
        columns = GridCells.Fixed(boardSize),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
    ) {
        items(gridItems) { point ->
            TicTacToeGridItem(Modifier.testTag(TestTags.TicTacToeGridItem), point.state) {
                onClick(point.row, point.column)
            }
        }
    }
}

@Composable
private fun TicTacToeGridItem(
    modifier: Modifier,
    pointState: BoardPoint.State,
    onClick: () -> Unit,
) {
    when (pointState) {
        BoardPoint.State.Cross -> TextualPoint(
            "X",
            modifier = modifier,
            backgroundColor = Color.Magenta,
            color = Color.White,
            onClick = onClick
        )

        BoardPoint.State.Nought -> TextualPoint(
            "O",
            modifier = modifier,
            backgroundColor = Color.Cyan,
            color = Color.White,
            onClick = onClick
        )

        BoardPoint.State.Empty -> TextualPoint(
            "",
            modifier = modifier,
            backgroundColor = Color.White,
            onClick = onClick
        )
    }
}