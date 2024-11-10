package be.bnp.katas.tictactoe.data.utils

import org.junit.Assert
import org.junit.Test

class BoardUtilsTest {
    private val boardString = """
        x,_,x
        o,x,o
        o,x,x
        """.trimIndent()

    @Test
    fun `Board conversion vise-versa is correct`() {
        Assert.assertEquals(boardString.asBoardPoints.asString, boardString)
    }
}