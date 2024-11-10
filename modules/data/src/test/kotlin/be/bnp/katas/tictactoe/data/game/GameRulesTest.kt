package be.bnp.katas.tictactoe.data.game

import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.data.model.BoardPoints
import be.bnp.katas.tictactoe.data.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.data.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.data.usecase.victory.CheckRowVictoryUseCase
import be.bnp.katas.tictactoe.data.utils.asBoardPoints
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameRulesTest {
    companion object {
        fun gameRulesForBoardPoints(boardPoints: BoardPoints): GameRules {
            val repositoryMocked = mockk<BoardRepositoryImpl>()
            every { repositoryMocked.boardPoints } returns boardPoints
            val victoryUseCases = listOf(
                CheckColumnVictoryUseCase(repositoryMocked),
                CheckRowVictoryUseCase(repositoryMocked),
                CheckDiagonalVictoryUseCase(repositoryMocked)
            )
            val drawUseCase = CheckDrawUseCase(repositoryMocked)
            return GameRulesImpl(
                repositoryMocked,
                victoryUseCases,
                drawUseCase
            )
        }
    }

    @Test
    fun `isUserWins detects user win`() {
        val givenBoard = """
            o,_,o
            _,x,_
            _,x,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 0, column = 1, BoardPoint.State.CROSS)

        val rules = gameRulesForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(rules.isUserWins(givenPoint))
    }

    @Test
    fun `isDraw detects draw`() {
        val givenBoard = """
            o,_,o
            x,o,o
            o,x,x
        """.trimIndent()
        val givenPoint = BoardPoint(row = 0, column = 1, BoardPoint.State.CROSS)

        val rules = gameRulesForBoardPoints(givenBoard.asBoardPoints)

        assertTrue(rules.isDraw(givenPoint))
    }

    @Test
    fun `moveToNextTurn moves the turn to next user`() {
        val givenBoard = """
            o,_,o
            _,x,_
            _,x,_
        """.trimIndent()
        val givenTurn = BoardPoint.State.CROSS
        val expectedNextTurn = BoardPoint.State.NOUGHT

        val gameRules = gameRulesForBoardPoints(givenBoard.asBoardPoints)

        gameRules.moveToNextTurn(givenTurn)

        assertEquals(expectedNextTurn, gameRules.currentUserTurn)
    }

    @Test
    fun `isAllowedToPlacePoint returns false if the position is already occupied`() {
        val givenBoard = """
            o,_,x
            x,x,o
            _,o,_
        """.trimIndent()
        val givenPoint = BoardPoint(row = 0, column = 0, BoardPoint.State.CROSS)

        val gameRules = gameRulesForBoardPoints(givenBoard.asBoardPoints)

        assertFalse(gameRules.isAllowedToPlacePoint(givenPoint))
    }

    @Test
    fun `reset() resets the turn and board`() {
        val givenBoard = """
            o,_,x
            x,x,o
            _,o,_
        """.trimIndent()

        val repositoryMocked = mockk<BoardRepositoryImpl>(relaxed = true)
        every { repositoryMocked.boardPoints } returns givenBoard.asBoardPoints

        val gameRules = GameRulesImpl(repositoryMocked, emptyList(), mockk(relaxed = true))
        gameRules.moveToNextTurn(lastTurn = BoardPoint.State.CROSS)
        gameRules.reset()

        assertFalse(gameRules.currentUserTurn == BoardPoint.State.NOUGHT)
        verify(exactly = 1) { repositoryMocked.cleanBoard() }
    }
}