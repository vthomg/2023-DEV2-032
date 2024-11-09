package be.bnp.katas.tictactoe.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

}