package be.bnp.katas.tictactoe.domain.usecase

import be.bnp.katas.tictactoe.data.BoardPoints
import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.domain.utils.asBoardPoints
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class CheckDrawUseCaseTest {
    companion object {
        fun useCaseForBoardPoints(boardPoints: BoardPoints): CheckDrawUseCase {
            val repositoryMocked = mockk<BoardRepositoryImpl>()
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
        val givenPoint = BoardPoint(row = 2, column = 0, BoardPoint.State.CROSS)

        val useCase = useCaseForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(useCase(givenPoint))
    }
}