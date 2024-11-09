package be.bnp.katas.tictactoe.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.model.isEmpty
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

class BoardRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        private lateinit var boardRepository: BoardRepository

        @BeforeClass
        @JvmStatic
        fun initialize() {
            boardRepository = BoardRepositoryImpl()
        }
    }

    @Before
    fun beforeEach() {
        boardRepository.cleanBoard()
    }

    @Test
    fun `BoardRepository creates the sheet`() {
        assertTrue(boardRepository.sheet.points.isNotEmpty())
    }

    @Test
    fun `BoardRepository creates the sheet with provided size`() {
        val givenBoardSize = 6
        val expectedSheetSize = givenBoardSize * givenBoardSize

        val board = BoardRepositoryImpl(givenBoardSize)

        assertEquals(expectedSheetSize, board.sheet.points.flatten().size)
    }

    @Test
    fun `BoardRepository creates the sheet with only empty points`() {
        assertFalse(boardRepository.sheet.points.flatten().any { !it.isEmpty })
    }

    @Test
    fun `BoardRepository updates the point on the board`() {
        val givenPoint = BoardPoint(0, 0, BoardPoint.State.CROSS)

        boardRepository.updatePoint(givenPoint)

        val (row, column) = givenPoint
        assertEquals(givenPoint, boardRepository.sheet.getPointFor(row, column))
    }

    @Test
    fun `BoardRepository correctly checks for the point validity for the board`() {
        val givenPoint = BoardPoint(5, 0, BoardPoint.State.CROSS)

        assertFalse(boardRepository.isPointValidForBoard(givenPoint))
    }

}