package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

public class InvalidPositionInputException extends ChessException {
    private static final String message = "Invalid Position. Should be in the range <a..h><1..8>";

    public InvalidPositionInputException() {
        super(message);
    }
}
