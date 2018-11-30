package com.si.components;

public class Move {

    private int initialRow;
    private int initialCol;
    private int finalRow;
    private int finalCol;

    public int getInitialRow() {
        return initialRow;
    }

    public void setInitialRow(int initialRow) {
        this.initialRow = initialRow;
    }

    public int getInitialCol() {
        return initialCol;
    }

    public void setInitialCol(int initialCol) {
        this.initialCol = initialCol;
    }

    public int getFinalRow() {
        return finalRow;
    }

    public void setFinalRow(int finalRow) {
        this.finalRow = finalRow;
    }

    public int getFinalCol() {
        return finalCol;
    }

    public void setFinalCol(int finalCol) {
        this.finalCol = finalCol;
    }

    public Move(int r1, int c1, int r2, int c2) {
        this.initialRow = r1;
        this.initialCol = c1;
        this.finalRow = r2;
        this.finalCol = c2;
    }

    public void display() {
        String unicode = "\u27F6";
        Board.getStringFromUnicode(unicode);
        System.out.print("(" + this.initialRow + "," + this.initialCol + ") " + unicode + " (" + this.finalRow + ", " + this.finalCol + ")");
    }

    @Override
    public String toString() {
        return "r1=" + initialRow + ", c1=" + initialCol + ", r2=" + finalRow + ", c2=" + finalCol;
    }

    @Override
    public int hashCode() {
        int result = initialRow;
        int prime = 31;
        result = prime * result + initialCol;
        result = prime * result + finalRow;
        result = prime * result + finalCol;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        return initialRow == move.initialRow &&
                initialCol == move.initialCol &&
                finalRow == move.finalRow &&
                finalCol == move.finalCol;

    }
}
