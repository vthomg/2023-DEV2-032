package be.bnp.katas.tictactoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class TicTacToeGridItemData(
    val pointState: PointState,
) {
    enum class PointState {
        Cross,
        Nought,
        Empty,
    }
}

object TicTacToeGridDefaults {
    val verticalSpacing: Dp = 8.dp
    val horizontalSpacing: Dp = 8.dp
}

@Composable
fun TicTacToeGrid(
    gridItems: List<List<TicTacToeGridItemData>>,
    onClick: (row: Int, column: Int) -> Unit,
    modifier: Modifier = Modifier,
    verticalSpacing: Dp = TicTacToeGridDefaults.verticalSpacing,
    horizontalSpacing: Dp = TicTacToeGridDefaults.horizontalSpacing,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(gridItems.size),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
    ) {
        itemsIndexed(gridItems.flatten()) { index, item ->
            TicTacToeGridItem(item.pointState) {
                val row = (index % gridItems.size - 1).takeIf { it >= 0 } ?: (gridItems.size - 1)
                val column = (index / gridItems.size)
                onClick(row, column)
            }
        }
    }
}

@Composable
private fun TicTacToeGridItem(pointState: TicTacToeGridItemData.PointState, onClick: () -> Unit) {
    when (pointState) {
        TicTacToeGridItemData.PointState.Cross -> TextualPoint(
            "X",
            backgroundColor = Color.Magenta,
            color = Color.White,
            onClick = onClick
        )

        TicTacToeGridItemData.PointState.Nought -> TextualPoint(
            "O",
            backgroundColor = Color.Cyan,
            color = Color.White,
            onClick = onClick
        )

        TicTacToeGridItemData.PointState.Empty -> TextualPoint(
            "",
            backgroundColor = Color.White,
            onClick = onClick
        )
    }
}