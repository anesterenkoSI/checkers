package com.si;

import com.si.components.Board;
import com.si.components.Move;
import com.si.enums.Cell;
import com.si.enums.Player;
import com.si.main.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TestAllMovesForBlack {

    private static Board board;

    @Before
    public void initBoard() {
        Cell[][] cell = new Cell[][]{
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY}

        };
       board = new Board(cell);

    }

    @Test
    public void testMovesForBlack() {

        List<List<Move>> actualListB = Game.expandMoves(board, Player.BLACK);

        List<List<Move>> expectedListB = new ArrayList<>();
        List<Move> expectedMoveForBlack1 = new ArrayList<>();
        List<Move> expectedMoveForBlack2 = new ArrayList<>();
        expectedMoveForBlack1.add(new Move(3, 3, 2, 4));
        expectedMoveForBlack2.add(new Move(3, 3, 2, 2));
        expectedListB.add(expectedMoveForBlack1);
        expectedListB.add(expectedMoveForBlack2);
        //forward left and rigth for black
        assertEquals(expectedListB, actualListB);
    }
}
