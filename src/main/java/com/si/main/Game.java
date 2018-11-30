package com.si.main;

import com.si.components.Black;
import com.si.components.Board;
import com.si.components.Move;
import com.si.components.White;
import com.si.enums.Cell;
import com.si.enums.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static void main(String[] args) {
        allAvailableMoves();
    }

    //all available moves for white and black
    private static void allAvailableMoves() {

        Cell[][] cell = new Cell[][]{
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY}

        };
        Board board = new Board(cell);
        board.display();
        List<List<Move>> outerListW = expandMoves(board, Player.WHITE);
        List<List<Move>> outerListB = expandMoves(board, Player.BLACK);

        System.out.println("All forced moves for black: ");
        for (List<Move> innerListW : outerListW) {
            displayMovesInList(innerListW);
        }
        System.out.println("All forced moves for white: ");
        for (List<Move> innerListB : outerListB) {
            displayMovesInList(innerListB);
        }
    }

    //exit from recursive call on condition when forcedMoves is empty
    private static void expandMoveRecursivelyForWhite(Board board, List<List<Move>> outerList, ArrayList<Move> innerList, int row, int column) {
        List<Move> forcedMoves = White.obtainForcedMovesForWhite(row, column, board);
        if (forcedMoves.isEmpty()) {
            List<Move> innerCopy = (List<Move>) innerList.clone();
            outerList.add(innerCopy);
        } else {
            for (Move m : forcedMoves) {
                Board boardCopy = board.duplicate();
                boardCopy.genericMakeWhiteMove(m);
                innerList.add(m);
                expandMoveRecursivelyForWhite(boardCopy, outerList, innerList, m.getFinalRow(), m.getFinalCol());
                innerList.remove(m);
            }
        }
    }

    //exit from recursive call on condition when forcedMoves is empty
    private static void expandMoveRecursivelyForBlack(Board board, List<List<Move>> outerList, ArrayList<Move> innerList, int row, int column) {
        List<Move> forcedMoves = Black.obtainForcedMovesForBlack(row, column, board);
        if (forcedMoves.isEmpty()) {
            List<Move> innerCopy = (ArrayList<Move>) innerList.clone();
            outerList.add(innerCopy);
        } else {
            for (Move m : forcedMoves) {
                Board boardCopy = board.duplicate();
                boardCopy.genericMakeBlackMove(m);
                innerList.add(m);
                expandMoveRecursivelyForBlack(boardCopy, outerList, innerList, m.getFinalRow(), m.getFinalCol());
                innerList.remove(m);
            }
        }
    }

    //All expand moves for white and black
    public static List<List<Move>> expandMoves(Board board, Player player) {
        List<List<Move>> outerList = new ArrayList<>();
        if (player == Player.BLACK) {
            List<Move> moves;
            moves = Black.calculateAllForcedMovesForBlack(board);
            if (moves.isEmpty()) {
                moves = Black.calculateAllNonForcedMovesForBlack(board);
                for (Move m : moves) {
                    List<Move> innerList = new ArrayList<>();
                    innerList.add(m);
                    outerList.add(innerList);
                }
            } else {
                for (Move m : moves) {
                    int row = m.getFinalRow();
                    int column = m.getFinalCol();
                    ArrayList<Move> innerList = new ArrayList<>();
                    innerList.add(m);
                    Board boardCopy = board.duplicate();
                    boardCopy.genericMakeBlackMove(m);
                    expandMoveRecursivelyForBlack(boardCopy, outerList, innerList, row, column);
                    innerList.remove(m);
                }
                System.out.println("All non forced moves for white: ");
                moves = Black.calculateAllNonForcedMovesForBlack(board);
                displayMovesInList(moves);
            }
        } else if (player == Player.WHITE) {
            List<Move> moves;
            moves = White.calculateAllForcedMovesForWhite(board);
            if (moves.isEmpty()) {
                moves = White.calculateAllNonForcedMovesForWhite(board);
                for (Move m : moves) {
                    List<Move> innerList = new ArrayList<>();
                    innerList.add(m);
                    outerList.add(innerList);
                }
            } else {
                for (Move m : moves) {
                    int row = m.getFinalRow();
                    int column = m.getFinalCol();
                    ArrayList<Move> innerList = new ArrayList<>();
                    innerList.add(m);
                    Board boardCopy = board.duplicate();
                    boardCopy.genericMakeWhiteMove(m);
                    expandMoveRecursivelyForWhite(boardCopy, outerList, innerList, row, column);
                    innerList.remove(m);
                }
                System.out.println("All non forced moves for black: ");
                moves = White.calculateAllNonForcedMovesForWhite(board);
                displayMovesInList(moves);
            }
        }
        return outerList;
    }

    //used to display all moves in list
    private static void displayMovesInList(List<Move> v) {
        for (Move m : v) {
            m.display();
            System.out.print(", ");
        }
        System.out.println();
    }
}
