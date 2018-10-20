package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.AlreadyOccupiedException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.BishopMoveException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;

/**
 * The Bishop class represents the Bishop chess piece and validates Bishop's moves
 */
public class Bishop extends ChessPiece {

    public Bishop(ChessBoard board, Color clr, int currRow, int currCol){
        super(board, clr, currRow, currCol);
    }

    /**
     * check if the Bishop's move is valid
     * @param newRow destination row it should be moved to
     * @param newCol destination column it should be moved to
     * @return true if move is valid, else throws an exception
     * @throws ChessException if move is illegal
     */
    @Override
    public boolean isValidMove(int newRow, int newCol) throws ChessException {

        if(currentRow == newRow || currentColumn == newCol){
            throw new BishopMoveException(); // bishop can move only diagonally
        }

        if(Math.abs(newRow - currentRow) != Math.abs(newCol - currentColumn)){
            throw new BishopMoveException(); // bishop can move only diagonally
        }

        int rowOffset, colOffset;

        if(currentRow < newRow){
            rowOffset = 1; // upward move
        }else{
            rowOffset = -1; // downward move
        }

        if(currentColumn < newCol){
            colOffset = 1; // moving right
        }else{
            colOffset = -1; // move left
        }

        int y = currentColumn + colOffset;
        // move diagonally for each slot; bail out if occupied
        for(int x = currentRow + rowOffset; x != newRow; x += rowOffset){

            if(myBoard.getPiece(x, y) != null){
                throw new AlreadyOccupiedException(); // already occupied
            }

            y += colOffset;
        }

        return true;

    }


    /**
     * used to show the piece on the board
     * @return
     */
    @Override
    public char getNotation() {
        return 'B';
    }

}
