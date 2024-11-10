package be.bnp.katas.tictactoe.core

import kotlinx.coroutines.CoroutineDispatcher
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
class CoreModuleDiTest : AutoCloseKoinTest() {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        modules(coreModuleDi)
    }

    @Test
    fun `Verify core module`() {
        coreModuleDi.verify()
    }

    @Test
    fun `Verify CoroutineDispatcher is declared`() {
        assertNotNull(get<CoroutineDispatcher>())
    }
}