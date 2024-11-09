package be.bnp.katas.tictactoe.data

import be.bnp.katas.tictactoe.data.model.BoardPoint

@JvmInline
value class BoardPointsRepresentation(val points: List<List<BoardPoint>>) {
    companion object {
        fun createEmptyFor(size: Int): BoardPointsRepresentation {
            val points = MutableList(size) { row ->
                MutableList(size) { column ->
                    BoardPoint.createEmpty(
                        x = row,
                        y = column
                    )
                }
            }
            return BoardPointsRepresentation(points)
        }
    }
}