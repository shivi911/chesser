package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;


public class NoPieceExistsException extends ChessException {
    private static final String message = "No piece exists at this position";

    public NoPieceExistsException() {
        super(message);
    }
}
