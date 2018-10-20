package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.KingMoveException;

/**
 * The King class represents the King chess piece and validates King's moves
 */
public class King extends ChessPiece {

    public King(ChessBoard board, Color clr, int currRow, int currCol){
        super(board, clr, currRow, currCol);
    }

    /**
     * Check if the king's move is valid.
     * @param newRow destination row it should be moved to
     * @param newCol destination column it should be moved to
     * @return true if move is valid, else throws an exception
     * @throws ChessException
     */
    @Override
    public boolean isValidMove(int newRow, int newCol) throws ChessException {

        if(Math.abs(newRow - currentRow) > 1 || Math.abs(newCol - currentColumn) > 1){
            // unless castling, king cannot move more than 1 position
            throw new KingMoveException();
        }

        return true;
    }

    /**
     * used to show the piece on the board
     * @return
     */
    @Override
    public char getNotation() {
        return 'K';
    }
}
