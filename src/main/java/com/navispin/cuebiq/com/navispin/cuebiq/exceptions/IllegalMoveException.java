package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;

public class IllegalMoveException extends ChessException {
    private static final String message = "Illegal Move";

    public IllegalMoveException() {
        super(message);
    }
}

