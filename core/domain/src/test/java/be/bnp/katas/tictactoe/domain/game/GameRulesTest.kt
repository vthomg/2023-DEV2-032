package be.bnp.katas.tictactoe.domain.game

import be.bnp.katas.tictactoe.data.BoardPoints
import be.bnp.katas.tictactoe.data.model.BoardPoint
import be.bnp.katas.tictactoe.domain.repository.BoardRepositoryImpl
import be.bnp.katas.tictactoe.domain.usecase.draw.CheckDrawUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckColumnVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckDiagonalVictoryUseCase
import be.bnp.katas.tictactoe.domain.usecase.victory.CheckRowVictoryUseCase
import be.bnp.katas.tictactoe.domain.utils.asBoardPoints
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
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
}