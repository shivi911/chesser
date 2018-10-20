package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

public class KingMoveException extends ChessException {
    private static final String message = "King cannot move more than 1 spot";

    public KingMoveException() {
        super(message);
    }
}
