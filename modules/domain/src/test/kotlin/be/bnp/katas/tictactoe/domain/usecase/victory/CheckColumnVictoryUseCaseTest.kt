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

class CheckColumnVictoryUseCaseTest {
    companion object {
        fun useCaseForBoardPoints(boardPoints: BoardPoints): CheckColumnVictoryUseCase {
            val repositoryMocked = mockk<BoardRepository>()
            every { repositoryMocked.boardPoints } returns boardPoints
            return CheckColumnVictoryUseCase(repositoryMocked)
        }
    }

    @Test
    fun `Column Victory is detected`() {
        val givenBoard = """
            o,_,x
            o,x,x
            _,o,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 2, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(useCase(givenPoint))
    }

    @Test
    fun `Column Victory is detected in the middle`() {
        val givenBoard = """
            o,_,x
            o,x,_
            _,o,x
        """.trimIndent()
        val givenPoint = BoardPoint(row = 1, column = 2, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(useCase(givenPoint))
    }

    @Test
    fun `Column Victory is detected with mostly empty`() {
        val givenBoard = """
            o,_,o
            _,x,_
            _,x,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 0, column = 1, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(useCase(givenPoint))
    }

    @Test
    fun `Column Victory is not detected with all empty`() {
        val givenBoard = """
            _,_,_
            _,_,_
            _,_,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 2, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertFalse(useCase(givenPoint))
    }

    @Test
    fun `Column Victory is not detected with opponents state`() {
        val givenBoard = """
            o,_,o
            _,o,_
            _,x,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 0, column = 1, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertFalse(useCase(givenPoint))
    }
}