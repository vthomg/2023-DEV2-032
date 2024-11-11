package be.bnp.katas.tictactoe.domain.usecase.draw

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.model.BoardPoints
import be.bnp.katas.tictactoe.domain.repository.BoardRepository
import be.bnp.katas.tictactoe.domain.utils.asBoardPoints
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CheckDrawUseCaseTest {
    companion object {
        fun useCaseForBoardPoints(boardPoints: BoardPoints): CheckDrawUseCase {
            val repositoryMocked = mockk<BoardRepository>()
            every { repositoryMocked.boardPoints } returns boardPoints
            return CheckDrawUseCase(repositoryMocked)
        }
    }

    @Test
    fun `Draw is detected`() {
        val givenBoard = """
            x,x,o
            o,x,x
            _,o,o
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 0, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(useCase(givenPoint))
    }

    @Test
    fun `Draw is not detected when there is space to play left`() {
        val givenBoard = """
            x,x,o
            o,x,_
            _,o,o
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 0, BoardPoint.State.Cross)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertFalse(useCase(givenPoint))
    }
}