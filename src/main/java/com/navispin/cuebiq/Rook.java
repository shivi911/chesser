package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.AlreadyOccupiedException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.RookMoveException;

/**
 * The Rook class represents the Rook chess piece and validates Rook's moves
 */
public class Rook extends ChessPiece {


    public Rook(ChessBoard board, Color clr, int currRow, int currCol){
        super(board, clr, currRow, currCol);
    }


    /**
     * check if the Rook's move is valid
     * @param newRow destination row it should be moved to
     * @param newCol destination column it should be moved to
     * @return true if move is valid, else throws an exception
     * @throws ChessException
     */
    @Override
    public boolean isValidMove(int newRow, int newCol) throws ChessException {

        if(currentRow != newRow && currentColumn != newCol){ // rooks can move only on same row or same column
            throw new RookMoveException();
        }

        int offset;

        // when rook crosses, we need to make sure all cells in its path are empty
        if(currentRow != newRow){ // same column; different rows; moving in same column
            if(currentRow < newRow){ // moving up
                offset = 1;
            }else{ // moving down
                offset = -1;
            }

            for(int x = currentRow + offset; x != newRow; x += offset){
                // check that each spot on is way is empty
                if(myBoard.getPiece(x, currentColumn) != null){ // already occupied
                    throw new AlreadyOccupiedException();
                }
            }
        }

        if(currentColumn != newCol){ // same row; different columns
            if(currentColumn < newCol){
                offset = 1;
            }else{
                offset = -1;
            }

            for(int x = currentColumn + offset; x != newCol; x += offset){
                // check that each spot on is way is empty
                if(myBoard.getPiece(currentRow, x) != null){ // already occupied
                    throw new AlreadyOccupiedException();
                }
            }
        }

        return true;
    }

    /**
     * used to show the piece on the board
     * @return
     */
    @Override
    public char getNotation() {
        return 'R';
    }

}
