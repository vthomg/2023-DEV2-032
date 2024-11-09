package be.bnp.katas.tictactoe.data

import be.bnp.katas.tictactoe.data.model.BoardPoint

@JvmInline
value class BoardPointsRepresentation(val points: List<List<BoardPoint>>)