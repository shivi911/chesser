package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.*;

/**
 * The Pawn class represents the Pawn chess piece and validates Pawn's moves
 */
public class Pawn extends ChessPiece {


    public Pawn(ChessBoard board, Color clr, int currRow, int currCol){
        super(board, clr, currRow, currCol);
        hasMoved = false;
    }

    /**
     * check if the Pawn's move is valid
     * @param newRow destination row it should be moved to
     * @param newCol destination column it should be moved to
     * @return true if move is valid, else throws an exception
     * @throws ChessException
     */
    @Override
    public boolean isValidMove(int newRow, int newCol) throws ChessException {

        if(color == Color.WHITE){
            if(currentRow > newRow){ // cannot go backwards
                throw new PawnBackwardException();
            }
        }else{
            if(newRow > currentRow){ // cannot go backwards
                throw new PawnBackwardException();
            }
        }

        if(currentColumn == newCol){ // moving in same column (ok for pawn)
            if(color == Color.WHITE){

                if(myBoard.getPiece(currentRow + 1,currentColumn) != null){ // already in use by another piece
                    throw new AlreadyOccupiedException();
                }
            }else{
                if(myBoard.getPiece(currentRow - 1,currentColumn) != null){ // already in use by another piece
                    throw new AlreadyOccupiedException();
                }
            }

            if(Math.abs(newRow - currentRow) > 2){ // cannot move more than 2 spaces
                throw new PawnMoveException();
            }else if(Math.abs(newRow - currentRow) == 2){ // can move 2 spaces if not moved earlier
                //Advancing two spaces at beginning
                if(hasMoved){ // has moved earlier; cannot move 2 spaces
                    throw new IllegalMoveException();
                }

                if(color == Color.WHITE){
                    if(myBoard.getPiece(currentRow+2, currentColumn) != null){ // already in use by another piece
                        throw new AlreadyOccupiedException();
                    }
                }else{
                    if(myBoard.getPiece(currentRow-2, currentColumn) != null){ // already in use by another piece
                        throw new AlreadyOccupiedException();
                    }
                }

            }
        }
        else { // moving across columns; don't allow; we are not supporting taking another piece
            throw new PawnAcrossColumnException();
        }

        return true;
    }

    /**
     * used to show the piece on the board
     * @return
     */
    @Override
    public char getNotation() {
        return 'p';
    }

}
