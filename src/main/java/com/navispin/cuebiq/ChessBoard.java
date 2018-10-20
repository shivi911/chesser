package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.*;

import java.io.IOException;

/**
 * The ChessBoard represents the chess pieces on it; and the state of the board once the game has started.
 * The chess pieces in the board are represented by a 2-dimensional array of size 8X8
 */
public class ChessBoard {
    Color currentPlayerColor = Color.WHITE;

    public ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoard() {
        this.initialize();
    }

    private void initialize() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = null;
            }
        }

        new Pawn(this, Color.WHITE, 1, 0);
        // White pawns
        for (int x = 0; x < 8; x++) {
            board[1][x] = new Pawn(this, Color.WHITE, 1, x);
        }

        // Black pawns
        for (int x = 0; x < 8; x++) {
            board[6][x] = new Pawn(this, Color.BLACK, 6, x);
        }

        //Rooks
        board[0][0] = new Rook(this, Color.WHITE, 0, 0);
        board[0][7] = new Rook(this, Color.WHITE, 0, 7);
        board[7][7] = new Rook(this, Color.BLACK, 7, 7);
        board[7][0] = new Rook(this, Color.BLACK, 7, 0);

        //Knights
        board[0][1] = new Knight(this, Color.WHITE, 0, 1);
        board[0][6] = new Knight(this, Color.WHITE, 0, 6);
        board[7][6] = new Knight(this, Color.BLACK, 7, 6);
        board[7][1] = new Knight(this, Color.BLACK, 7, 1);

        //Bishops
        board[0][2] = new Bishop(this, Color.WHITE, 0, 2);
        board[0][5] = new Bishop(this, Color.WHITE, 0, 5);
        board[7][2] = new Bishop(this, Color.BLACK, 7, 2);
        board[7][5] = new Bishop(this, Color.BLACK, 7, 5);

        //Queens
        board[0][3] = new Queen(this, Color.WHITE, 0, 3);
        board[7][3] = new Queen(this, Color.BLACK, 7, 3);

        //Kings
        board[0][4] = new King(this, Color.WHITE, 0, 4);
        board[7][4] = new King(this, Color.BLACK, 7, 4);

    }


    /**
     * move a chess piece from one position to another. Example of position is c1 (pawn in 3 column, row 1)
     * It throws an exception if the move is not possible
     * @param fromPosition
     * @param toPosition
     * @return
     * @throws ChessException
     */
    public boolean movePiece(String fromPosition, String toPosition) throws ChessException {
        boolean retVal = true;
        int[] fromCoords = getCoordinates(fromPosition);
        int[] toCoords = getCoordinates(toPosition);
        int srcRow = fromCoords[0];
        int srcCol = fromCoords[1];
        int destRow = toCoords[0];
        int destCol = toCoords[1];
        // check for valid values. Should not exceed 7
        if(srcRow > 7 || srcCol > 7 || destRow > 7 || destCol > 7) throw new InvalidPositionException();
        retVal = movePiece(srcRow, srcCol, destRow, destCol);
        return retVal;
    }

    private boolean movePiece(int srcRow, int srcCol, int destRow, int destCol) throws ChessException {
        boolean retVal = true;
        ChessPiece srcPiece = getPiece(srcRow, srcCol);
        ChessPiece destPiece = getPiece(destRow, destCol);
        if (srcPiece == null) {
            throw new NoPieceExistsException(); // no piece exists here
        }

        if (srcPiece.getColor() != currentPlayerColor) {
            throw new WrongPlayerException(); // different color; wrong player made the move
        }


        if (destPiece != null) {
            // destination already occupied
            throw new AlreadyOccupiedException();
        }

        if (srcPiece.isValidMove(destRow, destCol)) {
            // valid move
            //Switch the two spots on the board because the move was valid
            board[destRow][destCol] = board[srcRow][srcCol];
            // TODO: check the color to be sure
            board[srcRow][srcCol] = null; // clear the current position after the move

            // switch colors now for current active player
            if(currentPlayerColor == Color.WHITE) {
                currentPlayerColor = Color.BLACK;
            }
            else {
                currentPlayerColor = Color.WHITE;
            }

        } else {
            throw new IllegalMoveException();
        }
        return retVal;
    }


    /**
     * get the chess piece at the specified row and column
     * @param row
     * @param column
     * @return
     */
    public ChessPiece getPiece(int row, int column) throws InvalidPositionException {
        if(row > 7 ||  column > 7 || row < 0 || column < 0) throw new InvalidPositionException();
        ChessPiece piece = board[row][column];
        if(piece != null) {
            piece.setCurrentRow(row);
            piece.setCurrentColumn(column);
        }

        return piece;
    }


    /**
     * return the chess coordinates for a position.
     * @param position e.g. c2
     * @return an array of length 2; the first position for x coordinate (row) and the 2nd one for y coordinate (column)
     */
    private int[] getCoordinates(String position) throws ChessException {
        int[] coords = new int[2];

        coords[1] = charToInt(Character.toLowerCase(position.charAt(0)));
        try {
            coords[0] = Integer.parseInt(position.charAt(1) + "") - 1;
        }
        catch(NumberFormatException e) {
            throw new InvalidPositionException();
        }

        return coords;
    }


    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    /**
     * Returns an integer corresponding to the user input for a column position
     */
    public static int charToInt(char ch) {
        switch (ch) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return 8;
        }
    }


    /**
     * displays the chess board. The black squares are indicated by ##
     * @return the layoout of pieces on the board
     */
    public String toString() {
        String string = "";
        int colCount = 0;
        for (ChessPiece[] pieces : board) {
            int rowCount = 0;
            for (ChessPiece piece : pieces) {
                if (piece == null) {
                    if (colCount % 2 == 0) {
                        if (rowCount % 2 == 0) {
                            string += "##";
                        } else {
                            string += "  ";
                        }
                    } else {
                        if (rowCount % 2 == 0) {
                            string += "  ";
                        } else {
                            string += "##";
                        }
                    }
                } else {
                    string += piece;
                }
                string += " | ";
                rowCount++;
            }
            colCount++;
            string += "\n";
        }

        String reverseString = "";

        reverseString += "  | a  | b  | c  | d  | e  | f  | g  | h |\n";
        reverseString += "------------------------------------------\n";

        String[] stringSplit = string.split("\n");
        for (int x = stringSplit.length - 1; x >= 0; x--) {
            reverseString += x + 1 + " | " + stringSplit[x] + "\n";
        }
        reverseString += "------------------------------------------\n";
        reverseString += "  | a  | b  | c  | d  | e  | f  | g  | h |\n";


        return reverseString;
    }

}
