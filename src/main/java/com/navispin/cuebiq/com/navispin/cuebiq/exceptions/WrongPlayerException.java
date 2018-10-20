package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;

public class WrongPlayerException extends ChessException {
    private static final String message = "Cannot move piece of other player";

    public WrongPlayerException() {
        super(message);
    }
}
