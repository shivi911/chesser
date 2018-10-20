package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

public class PawnAcrossColumnException extends ChessException {
    private static final String message = "Moving Pawn across columns is not supported";

    public PawnAcrossColumnException() {
        super(message);
    }
}
