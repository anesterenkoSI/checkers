package com.si.components;

import com.si.enums.Cell;

import java.util.ArrayList;
import java.util.List;

public class Black {

    public static List<Move> obtainForcedMovesForBlack(int row, int column, Board board) {
        List<Move> nextCaptures = new ArrayList<>();
        if (board.cell[row][column].equals(Cell.BLACK)) {
            if (forwardLeftCaptureForBlack(row, column, board) != null)
                nextCaptures.add(forwardLeftCaptureForBlack(row, column, board));
            if (forwardRightCaptureForBlack(row, column, board) != null)
                nextCaptures.add(forwardRightCaptureForBlack(row, column, board));
        }
        return nextCaptures;
    }

    public static List<Move> calculateAllForcedMovesForBlack(Board board) {
        List<Move> forcedMovesForBlack = new ArrayList<>();
        // Scan across the board
        for (int row = 0; row < Board.rows; row++) {
            int column = (row % 2 == 0) ? 0 : 1;
            for (; column < Board.cols; column += 2) {
                assert (!board.cell[row][column].equals(Cell.UNAVAILABLE));
                if (board.cell[row][column].equals(Cell.BLACK)) {
                    if (row >= 2) {
                        if (forwardLeftCaptureForBlack(row, column, board) != null)
                            forcedMovesForBlack.add(forwardLeftCaptureForBlack(row, column, board));
                        if (forwardRightCaptureForBlack(row, column, board) != null)
                            forcedMovesForBlack.add(forwardRightCaptureForBlack(row, column, board));
                    }
                }
            }
        }
        return forcedMovesForBlack;
    }

    //  Returns a list of all possible moves which Black can make at the state of the components given by board.
    public static List<Move> calculateAllNonForcedMovesForBlack(Board board) {
        List<Move> allNonForcedMovesForBlack = new ArrayList<>();
        // Scan across the board
        for (int row = 0; row < Board.rows; row++) {
            int column = (row % 2 == 0) ? 0 : 1;
            for (; column < Board.cols; column += 2) {
                assert (!board.cell[row][column].equals(Cell.UNAVAILABLE));
                if (board.cell[row][column].equals(Cell.BLACK)) {
                    Move move;
                    move = forwardLeftCaptureForBlack(row, column, board);
                    assert (move == null);
                    move = forwardRightCaptureForBlack(row, column, board);
                    assert (move == null);
                    move = forwardLeftForBlack(row, column, board);
                    if (move != null) {
                        allNonForcedMovesForBlack.add(move);
                    }
                    move = forwardRightForBlack(row, column, board);
                    if (move != null) {
                        allNonForcedMovesForBlack.add(move);
                    }
                }
            }
        }
        return allNonForcedMovesForBlack;
    }

    private static Move forwardLeftForBlack(int row, int column, Board board) {
        Move forwardLeft = null;
        assert (board.cell[row][column] == Cell.BLACK);
        if (row >= 1 && column < Board.cols - 1 && board.cell[row - 1][column + 1] == Cell.EMPTY) {
            forwardLeft = new Move(row, column, row - 1, column + 1);
        }
        return forwardLeft;
    }

    private static Move forwardLeftCaptureForBlack(int row, int column, Board board) {
        Move forwardLeftCapture = null;
        if (row >= 2 && column < Board.cols - 2 && board.cell[row - 1][column + 1].equals(Cell.WHITE) && board.cell[row - 2][column + 2].equals(Cell.EMPTY)) {
            forwardLeftCapture = new Move(row, column, row - 2, column + 2);
        }
        return forwardLeftCapture;
    }

    private static Move forwardRightForBlack(int row, int column, Board board) {
        Move forwardRight = null;
        if (row >= 1 && column >= 1 && board.cell[row - 1][column - 1] == Cell.EMPTY) {
            forwardRight = new Move(row, column, row - 1, column - 1);
        }
        return forwardRight;
    }

    private static Move forwardRightCaptureForBlack(int row, int column, Board board) {
        Move forwardRightCapture = null;
        if (row >= 2 && column >= 2 && board.cell[row - 1][column - 1].equals(Cell.WHITE) && board.cell[row - 2][column - 2].equals(Cell.EMPTY)) {
            forwardRightCapture = new Move(row, column, row - 2, column - 2);
        }
        return forwardRightCapture;
    }
}