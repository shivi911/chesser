package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;

public class RookMoveException extends ChessException {
    private static final String message = "Rook cannot move across rows or columns. Only same row/column is allowed";

    public RookMoveException() {
        super(message);
    }
}
