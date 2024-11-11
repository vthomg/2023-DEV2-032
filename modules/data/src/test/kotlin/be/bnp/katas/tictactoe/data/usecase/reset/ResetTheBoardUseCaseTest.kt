package be.bnp.katas.tictactoe.data.usecase.reset

import be.bnp.katas.tictactoe.domain.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepository
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.domain.usecase.move.MakeAMoveUseCase
import be.bnp.katas.tictactoe.domain.usecase.reset.ResetTheBoardUseCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class ResetTheBoardUseCaseTest {
    companion object {
        private lateinit var boardRepository: BoardRepository
        private lateinit var makeAMoveUseCase: MakeAMoveUseCase
        private lateinit var resetTheBoardUseCase: ResetTheBoardUseCase

        @BeforeClass
        @JvmStatic
        fun initialize() {
            boardRepository = BoardRepositoryImpl()
            makeAMoveUseCase = MakeAMoveUseCase(boardRepository)
            resetTheBoardUseCase = ResetTheBoardUseCase(boardRepository)
        }
    }

    @Before
    fun beforeEach() {
        boardRepository.cleanBoard()
    }

    @Test
    fun `Reset the board resets the board`() {
        val givenPoint = BoardPoint(row = 1, column = 2, BoardPoint.State.CROSS)
        val expectedPoint = BoardPoint(row = 1, column = 2, BoardPoint.State.EMPTY)

        assertTrue(makeAMoveUseCase(givenPoint))
        resetTheBoardUseCase()

        val (row, column) = givenPoint
        assertEquals(expectedPoint, boardRepository.boardPoints[row][column])
    }
}