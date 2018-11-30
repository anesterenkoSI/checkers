package com.si;

import com.si.components.Board;
import com.si.components.Move;
import com.si.enums.Player;
import com.si.main.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TestAllMovesForBlackAndWhite {

    private static Board board;

    @Before
    public void initBoard() {
        board = new Board();
    }

    @Test
    public void allAvailableMoves() {

        List<List<Move>> actualListB = Game.expandMoves(board, Player.BLACK);
        List<List<Move>> actualListW = Game.expandMoves(board, Player.WHITE);

        List<List<Move>> expectedListB = new ArrayList<>();
        List<List<Move>> expectedListW = new ArrayList<>();

        List<Move> expectedMoveB1 = new ArrayList<>();
        expectedMoveB1.add(new Move(5, 1, 4, 2));
        List<Move> expectedMoveB2 = new ArrayList<>();
        expectedMoveB2.add(new Move(5, 1, 4, 0));
        List<Move> expectedMoveB3 = new ArrayList<>();
        expectedMoveB3.add(new Move(5, 3, 4, 4));
        List<Move> expectedMoveB4 = new ArrayList<>();
        expectedMoveB4.add(new Move(5, 3, 4, 2));
        List<Move> expectedMoveB5 = new ArrayList<>();
        expectedMoveB5.add(new Move(5, 5, 4, 6));
        List<Move> expectedMoveB6 = new ArrayList<>();
        expectedMoveB6.add(new Move(5, 5, 4, 4));
        List<Move> expectedMoveB7 = new ArrayList<>();
        expectedMoveB7.add(new Move(5, 7, 4, 6));

        List<Move> expectedMoveW1 = new ArrayList<>();
        expectedMoveW1.add(new Move(2, 0, 3, 1));
        List<Move> expectedMoveW2 = new ArrayList<>();
        expectedMoveW2.add(new Move(2, 2, 3, 1));
        List<Move> expectedMoveW3 = new ArrayList<>();
        expectedMoveW3.add(new Move(2, 2, 3, 3));
        List<Move> expectedMoveW4 = new ArrayList<>();
        expectedMoveW4.add(new Move(2, 4, 3, 3));
        List<Move> expectedMoveW5 = new ArrayList<>();
        expectedMoveW5.add(new Move(2, 4, 3, 5));
        List<Move> expectedMoveW6 = new ArrayList<>();
        expectedMoveW6.add(new Move(2, 6, 3, 5));
        List<Move> expectedMoveW7 = new ArrayList<>();
        expectedMoveW7.add(new Move(2, 6, 3, 7));

        expectedListB.add(expectedMoveB1);
        expectedListB.add(expectedMoveB2);
        expectedListB.add(expectedMoveB3);
        expectedListB.add(expectedMoveB4);
        expectedListB.add(expectedMoveB5);
        expectedListB.add(expectedMoveB6);
        expectedListB.add(expectedMoveB7);

        expectedListW.add(expectedMoveW1);
        expectedListW.add(expectedMoveW2);
        expectedListW.add(expectedMoveW3);
        expectedListW.add(expectedMoveW4);
        expectedListW.add(expectedMoveW5);
        expectedListW.add(expectedMoveW6);
        expectedListW.add(expectedMoveW7);
//        System.out.println("expected list black : "+expectedListB + " actual list black : "+actualListB);
//        System.out.println("expected list white : "+expectedListW + " actual list white : "+actualListW);
        assertEquals(expectedListB, actualListB);
        assertEquals(expectedListW, actualListW);

    }

}
