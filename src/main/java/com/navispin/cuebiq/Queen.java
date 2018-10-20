package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.IllegalMoveException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.RookMoveException;

/**
 * The Queen class represents the Queen chess piece and validates Queen's moves
 */
public class Queen extends ChessPiece {

    public Queen(ChessBoard board, Color clr, int currRow, int currCol){
        super(board, clr, currRow, currCol);
    }

    /**
     * check if the Queen's move is valid
     * @param newRow destination row it should be moved to
     * @param newCol destination column it should be moved to
     * @return true if move is valid, else throws an exception
     * @throws ChessException
     */
    @Override
    public boolean isValidMove(int newRow, int newCol) throws ChessException {
        //A Queen's move is the same as a Rook's or a Bishop's
        // try Rook first
        boolean isValid = true;
        try {
            isValid = new Rook(myBoard, color, currentRow, currentColumn).isValidMove(newRow, newCol);
        }
        catch(RookMoveException e) {
            isValid = true; // ignore rooks moving diagonally exception; it's allow for Queen

        }

        if(isValid) return true;


        // if rook has failed for other errors; try Bishop
        return new Bishop(myBoard, color, currentRow, currentColumn).isValidMove(newRow, newCol);
        // return new Rook(myBoard, color, currentRow, currentColumn).isValidMove(newRow, newCol) || new Bishop(myBoard, color, currentRow, currentColumn).isValidMove(newRow, newCol);
    }

    /**
     * used to show the piece on the board
     * @return
     */
    @Override
    public char getNotation() {
        return 'Q';
    }

}
