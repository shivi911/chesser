package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.KnightMoveException;

/**
 * The Knight class represents the Knight chess piece and validates Knight's moves
 */
public class Knight extends ChessPiece {

    public Knight(ChessBoard board, Color clr, int currRow, int currCol){
        super(board, clr, currRow, currCol);
    }

    /**
     * check if the Knight's move is valid
     * @param newRow destination row it should be moved to
     * @param newCol destination column it should be moved to
     * @return true if move is valid, else throws an exception
     * @throws ChessException
     */
    @Override
    public boolean isValidMove(int newRow, int newCol) throws ChessException {

        if(Math.abs(newRow - currentRow) == 2 && Math.abs(newCol - currentColumn) == 1){
            return true; // 2 rows up/down and next column
        }
        else if(Math.abs(newRow - currentRow) == 1 && Math.abs(newCol - currentColumn) == 2){
            return true; // 2 columns left/right and next row
        }
        else {
            throw new KnightMoveException(); // illegal move for knight
        }

    }


    /**
     * used to show the piece on the board
     * @return
     */
    @Override
    public char getNotation() {
        return 'N';
    }

}
