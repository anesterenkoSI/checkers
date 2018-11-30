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

public class TestAllMovesForWhite {
    private static Board board;

    @Before
    public void initBoard() {
        Cell[][] cell = new Cell[][]{
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK}

        };
        board = new Board(cell);

    }

    @Test
    public void testMovesForWhite() {

        List<List<Move>> actualListW = Game.expandMoves(board, Player.WHITE);
        List<List<Move>> expectedListW = new ArrayList<>();
        //no available moves for white
        assertEquals(expectedListW, actualListW);
    }
}
