package com.si.components;

import com.si.enums.Cell;
import com.si.enums.Action;

public class Board {

    public int blackPieces;
    public int whitePieces;

    public static final int rows = 8;
    public static final int cols = 8;

    public Cell[][] cell;

    //initial positions for white and black
    public Board() {
        this.blackPieces = this.whitePieces = 12;

        this.cell = new Cell[][]{
                {Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE},
                {Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE, Cell.WHITE, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY},
                {Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE, Cell.EMPTY, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK},
                {Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE},
                {Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK, Cell.UNAVAILABLE, Cell.BLACK}
        };
    }

    //use to init board with specific positions white and black
    public Board(Cell[][] board) {
        this.blackPieces = this.whitePieces = 12;
        this.cell = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(board[i], 0, this.cell[i], 0, cols);
        }
    }

    // making move
    private void makeMove(int row1, int column1, int row2, int column2) {
        this.cell[row2][column2] = this.cell[row1][column1];
        this.cell[row1][column1] = Cell.EMPTY;
    }

    // Capture Black Piece and Move
    private void captureBlackPiece(int row1, int column1, int row2, int column2) {
        // Check Valid Capture
        assert (Math.abs(row2 - row1) == 2 && Math.abs(column2 - column1) == 2);
        // Obtain the capture direction
        Action dir;
        if (row2 > row1) {
            if (column2 > column1) {
                dir = Action.FORWARD_RIGHT;
            } else {
                dir = Action.FORWARD_LEFT;
            }
        } else {
            if (column2 > column1) {
                dir = Action.BACKWARD_RIGHT;
            } else {
                dir = Action.BACKWARD_LEFT;
            }
        }
        // Removing Black Piece from the board
        switch (dir) {
            case FORWARD_LEFT:
                this.cell[row1 + 1][column1 - 1] = Cell.EMPTY;
                break;
            case FORWARD_RIGHT:
                this.cell[row1 + 1][column1 + 1] = Cell.EMPTY;
                break;
            case BACKWARD_LEFT:
                this.cell[row1 - 1][column1 - 1] = Cell.EMPTY;
                break;
            case BACKWARD_RIGHT:
                this.cell[row1 - 1][column1 + 1] = Cell.EMPTY;
                break;
        }
        // Decreasing the count of BLACK pieces
        this.blackPieces--;
        // Making move
        this.makeMove(row1, column1, row2, column2);
    }
    // Capture White Piece and Move
    private void captureWhitePiece(int row1, int column1, int row2, int column2) {
        assert (Math.abs(row2 - row1) == 2 && Math.abs(column2 - column1) == 2);
        Action dir;

        if (row2 < row1) {
            if (column2 < column1) {
                dir = Action.FORWARD_RIGHT;
            } else {
                dir = Action.FORWARD_LEFT;
            }
        } else {
            if (column2 < column1) {
                dir = Action.BACKWARD_RIGHT;
            } else {
                dir = Action.BACKWARD_LEFT;
            }
        }
        switch (dir) {
            case FORWARD_LEFT:
                this.cell[row1 - 1][column1 + 1] = Cell.EMPTY;
                break;
            case FORWARD_RIGHT:
                this.cell[row1 - 1][column1 - 1] = Cell.EMPTY;
                break;
            case BACKWARD_LEFT:
                this.cell[row1 + 1][column1 + 1] = Cell.EMPTY;
                break;
            case BACKWARD_RIGHT:
                this.cell[row1 + 1][column1 - 1] = Cell.EMPTY;
                break;
        }
        this.whitePieces--;
        this.makeMove(row1, column1, row2, column2);
    }

    //Makes all kinds of valid moves of a WHITE player.
    public void genericMakeWhiteMove(Move move) {
        int r1 = move.getInitialRow();
        int c1 = move.getInitialCol();
        int r2 = move.getFinalRow();
        int c2 = move.getFinalCol();
        if ((Math.abs(r2 - r1) == 2 && Math.abs(c2 - c1) == 2)) {
            captureBlackPiece(r1, c1, r2, c2);
        } else {
            makeMove(r1, c1, r2, c2);
        }
    }
    //Makes all kinds of valid moves of a BLACK player.
    public void genericMakeBlackMove(Move move) {
        int r1 = move.getInitialRow();
        int c1 = move.getInitialCol();
        int r2 = move.getFinalRow();
        int c2 = move.getFinalCol();
        if (Math.abs(r2 - r1) == 2 && Math.abs(c2 - c1) == 2) {
            captureWhitePiece(r1, c1, r2, c2);
        } else {
            makeMove(r1, c1, r2, c2);
        }
    }

    // used to display board
    public void display() {
        this.displayColIndex();
        this.drawHorizontalLine();

        for (int i = rows - 1; i >= 0; i--) {
            this.displayRowIndex(i);
            this.drawVerticalLine();
            for (int j = 0; j < cols; j++) {
                System.out.print(this.boardPiece(i, j));
                this.drawVerticalLine();
            }
            this.displayRowIndex(i);
            System.out.println();
            this.drawHorizontalLine();
        }
        this.displayColIndex();
        System.out.println();
    }

    // used to display unavailable/empty/white/black on board
    private String boardPiece(int i, int j) {
        assert (i > 0 && i < rows && j > 0 && j < cols);
        String str = "";
        if (this.cell[i][j] == Cell.UNAVAILABLE) {
            str = "     ";
        } else if (this.cell[i][j] == Cell.EMPTY) {
            str = "  _  ";
        } else if (this.cell[i][j] == Cell.WHITE) {
            String unicode = "\u26C0";
            getStringFromUnicode(unicode);
            str = "  " + unicode + "  ";
        } else if (this.cell[i][j] == Cell.BLACK) {
            String unicode = "\u26C2";
            getStringFromUnicode(unicode);
            str = "  " + unicode + "  ";
        }
        return str;
    }

    //return string from unicode
    public static String getStringFromUnicode(String unicode) {
        String str = unicode.split(" ")[0];
        str = str.replace("\\", "");
        String[] arr = str.split("u");
        String text = "";
        for (int i = 1; i < arr.length; i++) {
            int hexVal = Integer.parseInt(arr[i], 16);
            text += (char) hexVal;
        }
        return text;
    }

    //used to display board
    private void drawHorizontalLine() {
        System.out.println("    _______________________________________________");
    }

    //used to display board
    private void drawVerticalLine() {
        System.out.print("|");
    }

    //used to display collumn index on board
    private void displayColIndex() {
        System.out.print("   ");
        for (int colIndex = 0; colIndex < cols; colIndex++) {
            System.out.print("   " + colIndex + "  ");
        }
        System.out.println();
    }
    //used to display row index on board
    private void displayRowIndex(int i) {
        System.out.print(" " + i + " ");
    }

    public Board duplicate() {
        Board newBoard = new Board(this.cell);
        newBoard.blackPieces = this.blackPieces;
        newBoard.whitePieces = this.whitePieces;
        return newBoard;
    }
}