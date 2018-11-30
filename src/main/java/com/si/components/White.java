package com.si.components;

import com.si.enums.Cell;

import java.util.ArrayList;
import java.util.List;

public class White {

    public static List<Move> obtainForcedMovesForWhite(int row, int column, Board board) {
        List<Move> nextCaptures = new ArrayList<>();
        if (board.cell[row][column].equals(Cell.WHITE)) {
            if (forwardLeftCaptureForWhite(row, column, board) != null)
                nextCaptures.add(forwardLeftCaptureForWhite(row, column, board));
            if (forwardRightCaptureForWhite(row, column, board) != null)
                nextCaptures.add(forwardRightCaptureForWhite(row, column, board));
        }
        return nextCaptures;
    }

    public static List<Move> calculateAllForcedMovesForWhite(Board board) {
        List<Move> forcedMovesForWhite = new ArrayList<>();
        // Scan across the board
        for (int row = 0; row < Board.rows; row++) {
            int column = (row % 2 == 0) ? 0 : 1;
            for (; column < Board.cols; column += 2) {
                assert (!board.cell[row][column].equals(Cell.UNAVAILABLE));
                if (board.cell[row][column].equals(Cell.WHITE)) {
                    if (row < Board.rows - 2) {
                        if (forwardLeftCaptureForWhite(row, column, board) != null)
                            forcedMovesForWhite.add(forwardLeftCaptureForWhite(row, column, board));
                        if (forwardRightCaptureForWhite(row, column, board) != null)
                            forcedMovesForWhite.add(forwardRightCaptureForWhite(row, column, board));
                    }
                }
            }
        }
        return forcedMovesForWhite;
    }

    //  Returns a list of all possible moves which White can make at the state of the components given by board.
    public static List<Move> calculateAllNonForcedMovesForWhite(Board board) {
        List<Move> allNonForcedMovesForWhite = new ArrayList<>();
        // Scan across the board
        for (int row = 0; row < Board.rows; row++) {
            int column = (row % 2 == 0) ? 0 : 1;
            for (; column < Board.cols; column += 2) {
                assert (!board.cell[row][column].equals(Cell.UNAVAILABLE));
                if (board.cell[row][column].equals(Cell.WHITE)) {
                    Move move;
                    move = forwardLeftCaptureForWhite(row, column, board);
                    assert (move == null);
                    move = forwardRightCaptureForWhite(row, column, board);
                    assert (move == null);
                    move = forwardLeftForWhite(row, column, board);
                    if (move != null) {
                        allNonForcedMovesForWhite.add(move);
                    }
                    move = forwardRightForWhite(row, column, board);
                    if (move != null) {
                        allNonForcedMovesForWhite.add(move);
                    }
                }
            }
        }
        return allNonForcedMovesForWhite;
    }

    private static Move forwardLeftForWhite(int row, int column, Board board) {
        Move forwardLeft = null;
        if (row < Board.rows - 1 && column >= 1 && board.cell[row + 1][column - 1] == Cell.EMPTY) {
            forwardLeft = new Move(row, column, row + 1, column - 1);
        }
        return forwardLeft;
    }

    private static Move forwardLeftCaptureForWhite(int row, int column, Board board) {
        Move forwardLeftCapture = null;
        if (row < Board.rows - 2 && column >= 2 && board.cell[row + 1][column - 1].equals(Cell.BLACK) && board.cell[row + 2][column - 2].equals(Cell.EMPTY)) {
            forwardLeftCapture = new Move(row, column, row + 2, column - 2);
        }
        return forwardLeftCapture;
    }

    private static Move forwardRightForWhite(int row, int column, Board board) {
        Move forwardRight = null;
        if (row < Board.rows - 1 && column < Board.cols - 1 && board.cell[row + 1][column + 1] == Cell.EMPTY) {
            forwardRight = new Move(row, column, row + 1, column + 1);
        }
        return forwardRight;
    }

    private static Move forwardRightCaptureForWhite(int row, int column, Board board) {
        Move forwardRightCapture = null;
        if (row < Board.rows - 2 && column < Board.cols - 2 && board.cell[row + 1][column + 1].equals(Cell.BLACK) && board.cell[row + 2][column + 2].equals(Cell.EMPTY)) {
            forwardRightCapture = new Move(row, column, row + 2, column + 2);
        }
        return forwardRightCapture;
    }
}