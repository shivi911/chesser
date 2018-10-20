package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

public class InvalidPositionException extends ChessException {
    private static final String message = "The position does not exist on the board";

    public InvalidPositionException() {
        super(message);
    }
}
