package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

public class KnightMoveException extends ChessException {
    private static final String message = "Invalid move. Knight can move only across adjacent row or column";

    public KnightMoveException() {
        super(message);
    }
}
