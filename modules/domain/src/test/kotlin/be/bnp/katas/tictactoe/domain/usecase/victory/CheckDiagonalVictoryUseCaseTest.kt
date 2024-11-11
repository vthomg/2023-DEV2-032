package be.bnp.katas.tictactoe.domain.usecase.victory

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.model.BoardPoints
import be.bnp.katas.tictactoe.domain.repository.BoardRepository
import be.bnp.katas.tictactoe.domain.utils.asBoardPoints
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CheckDiagonalVictoryUseCaseTest {
    companion object {
        fun useCaseForBoardPoints(boardPoints: BoardPoints): CheckDiagonalVictoryUseCase {
            val repositoryMocked = mockk<BoardRepository>()
            every { repositoryMocked.boardPoints } returns boardPoints
            return CheckDiagonalVictoryUseCase(repositoryMocked)
        }
    }

    @Test
    fun `Diagonal top left to bottom right victory is detected`() {
        val givenBoard = """
            x,_,x
            o,x,o
            o,o,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 2, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(useCase(givenPoint))
    }

    @Test
    fun `Diagonal top right to bottom left victory is detected`() {
        val givenBoard = """
            x,_,x
            o,x,o
            _,o,o
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 0, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(useCase(givenPoint))
    }

    @Test
    fun `Diagonal Victory is not detected with all empty`() {
        val givenBoard = """
            _,_,_
            _,_,_
            _,_,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 2, BoardPoint.State.Cross)

        val useCase = CheckColumnVictoryUseCaseTest.useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertFalse(useCase(givenPoint))
    }
}