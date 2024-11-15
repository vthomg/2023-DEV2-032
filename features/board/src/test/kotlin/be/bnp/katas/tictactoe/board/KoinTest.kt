package be.bnp.katas.tictactoe.board

import be.bnp.katas.tictactoe.board.di.boardModuleDi
import be.bnp.katas.tictactoe.board.viewmodel.BoardViewModel
import be.bnp.katas.tictactoe.domain.repository.BoardRepository
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.logger.Level
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class BoardModuleDiTest : AutoCloseKoinTest() {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        modules(boardModuleDi)
    }

    @Test
    fun `Verify board module`() {
        boardModuleDi.verify()
    }

    @Test
    fun `Verify BoardViewModel is declared`() {
        assertNotNull(get<BoardViewModel>())
    }

    @Test
    fun `Verify BoardRepository is declared`() {
        assertNotNull(get<BoardRepository>())
    }
}