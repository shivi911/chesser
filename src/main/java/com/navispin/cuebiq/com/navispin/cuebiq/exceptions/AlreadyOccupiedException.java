package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;


public class AlreadyOccupiedException extends ChessException {
    private static final String message = "Destination position already occupied";

    public AlreadyOccupiedException() {
        super(message);
    }
}
