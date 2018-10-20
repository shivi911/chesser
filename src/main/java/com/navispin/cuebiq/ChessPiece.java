package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;

enum Color
{
    WHITE, BLACK;
}

/**
 * This is the ChessPiece abstract class that encapsulates chess pieces Rook, Bishop, Knight, King, Queen, Pawn
 */
public abstract class ChessPiece {
    protected boolean hasMoved;
    protected Color color;
    protected int currentRow; // the index of the horizontal rows 0..7
    protected int currentColumn; // the index of the vertical column 0..7
    protected ChessBoard myBoard; // the board the piece belongs to

    public ChessPiece(ChessBoard board, Color clr, int currRow, int currCol) {
        this.myBoard = board;
        this.color = clr;
        this.currentRow = currRow;
        this.currentColumn = currCol;
    }

    public ChessPiece() {

    }

    public void setCurrentRow(int row) {
        this.currentRow = row;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentColumn(int column) {
        this.currentColumn = column;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    /**
     * get the color of the piece
     * @return
     */
    public Color getColor() { return color;}

    /**
     * Check if the move is valid. Should be overridden by each specific chess piece class
     * @param newRow destination row it should be moved to
     * @param newCol destination column it should be moved to
     * @return true if move is valid, else throws an exception
     * @throws ChessException
     */
    public abstract boolean isValidMove(int newRow, int newCol) throws ChessException;

   // public abstract boolean isValidMove(ChessPiece[][] board, int currentRow, int currentCol, int newRow, int newCol);

    /**
     * returns the notation for the underlying piece. e.g. K for King; N for Knight, P for pawn, B for bishop
     * @return
     */
    public abstract char getNotation();

    /**
     * converts Piece to string form as indicated by notation. e.g. wR would be white Rook.
     * @return the layout of a piece on the board
     */
    public String toString() {
        String str;
        if(color==Color.WHITE) str = "w";
        else str = "b";
        return str + getNotation();
    }
}
