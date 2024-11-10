package be.bnp.katas.tictactoe.data.usecase.victory

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.model.BoardPoints
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.data.usecase.victory.CheckColumnVictoryUseCaseTest.Companion
import be.bnp.katas.tictactoe.data.utils.asBoardPoints
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CheckRowVictoryUseCaseTest {
    companion object {
        fun useCaseForBoardPoints(boardPoints: BoardPoints): CheckRowVictoryUseCase {
            val repositoryMocked = mockk<BoardRepositoryImpl>()
            every { repositoryMocked.boardPoints } returns boardPoints
            return CheckRowVictoryUseCase(repositoryMocked)
        }
    }

    @Test
    fun `Row Victory is detected`() {
        val givenBoard = """
            x,_,x
            o,x,x
            _,o,o
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 0, BoardPoint.State.NOUGHT)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(useCase(givenPoint))
    }

    @Test
    fun `Row Victory is not detected with all empty`() {
        val givenBoard = """
            _,_,_
            _,_,_
            _,_,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 2, column = 2, BoardPoint.State.CROSS)

        val useCase = CheckColumnVictoryUseCaseTest.useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertFalse(useCase(givenPoint))
    }
}