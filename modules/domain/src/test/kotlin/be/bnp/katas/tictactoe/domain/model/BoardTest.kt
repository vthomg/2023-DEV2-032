package be.bnp.katas.tictactoe.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class BoardTest {
    @Test
    fun `getPointFor(row, column) returns the correct point`() {
        val givenPoint = BoardPoint(0, 1, BoardPoint.State.Cross)
        val givenBoard = Board(
            mutableListOf(
                mutableListOf(
                    BoardPoint.createEmpty(0, 0),
                    givenPoint,
                ),
                mutableListOf(
                    BoardPoint.createEmpty(1, 0),
                    BoardPoint.createEmpty(1, 1),
                )
            )
        )

        val (row, column) = givenPoint
        assertEquals(givenBoard.getPointFor(row, column), givenPoint)
    }


    @Test
    fun `updatePointFor(row, column) updates the point on the board`() {
        val givenPoint = BoardPoint(0, 0, BoardPoint.State.Cross)
        val givenBoard = Board(
            mutableListOf(
                mutableListOf(
                    BoardPoint.createEmpty(0, 0),
                    givenPoint,
                ),
                mutableListOf(
                    BoardPoint.createEmpty(1, 0),
                    BoardPoint.createEmpty(1, 1),
                )
            )
        )

        val (row, column) = givenPoint
        givenBoard.updatePointFor(row, column, givenPoint)

        assertEquals(givenPoint, givenBoard.getPointFor(row, column))
    }
}
