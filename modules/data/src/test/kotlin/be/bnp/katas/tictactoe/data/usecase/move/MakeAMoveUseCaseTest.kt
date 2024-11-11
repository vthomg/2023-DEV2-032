package be.bnp.katas.tictactoe.data.usecase.move

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.repository.BoardRepository
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class MakeAMoveUseCaseTest {
    companion object {
        private lateinit var boardRepository: BoardRepository
        private lateinit var makeAMoveUseCase: MakeAMoveUseCase

        @BeforeClass
        @JvmStatic
        fun initialize() {
            boardRepository = BoardRepositoryImpl()
            makeAMoveUseCase = MakeAMoveUseCase(boardRepository)
        }
    }

    @Before
    fun beforeEach() {
        boardRepository.cleanBoard()
    }

    @Test
    fun `Make a move updates the point on the board`() {
        val givenPoint = BoardPoint(row = 1, column = 2, BoardPoint.State.CROSS)

        assertTrue(makeAMoveUseCase(givenPoint))

        val (row, column) = givenPoint
        assertEquals(givenPoint, boardRepository.boardPoints[row][column])
    }

    @Test
    fun `Make a move does not updates the invalid point on the board`() {
        val givenPoint = BoardPoint(row = 1, column = 10, BoardPoint.State.CROSS)

        assertFalse(makeAMoveUseCase(givenPoint))
    }
}