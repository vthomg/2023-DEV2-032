package be.bnp.katas.tictactoe.data

import be.bnp.katas.tictactoe.data.model.BoardPoint
import org.junit.Assert.assertEquals
import org.junit.Test

class BoardTest {
    @Test
    fun `getPointFor(row, column) returns the correct point`() {
        val givenPoint = BoardPoint(0, 1, BoardPoint.State.CROSS)

        val givenBoardRepresentation = Board(
            listOf(
                listOf(
                    BoardPoint.createEmpty(0, 0),
                    givenPoint,
                ),
                listOf(
                    BoardPoint.createEmpty(1, 0),
                    BoardPoint.createEmpty(1, 1),
                )
            )
        )

        val (row, column) = givenPoint
        assertEquals(givenBoardRepresentation.getPointFor(row, column), givenPoint)
    }
}
