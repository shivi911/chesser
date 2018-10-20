package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

public class BishopMoveException extends ChessException {
    private static final String message = "Bishop can move only diagonally";

    public BishopMoveException() {
        super(message);
    }
}
