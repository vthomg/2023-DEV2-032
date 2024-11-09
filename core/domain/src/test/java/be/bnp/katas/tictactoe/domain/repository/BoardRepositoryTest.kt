package be.bnp.katas.tictactoe.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

}