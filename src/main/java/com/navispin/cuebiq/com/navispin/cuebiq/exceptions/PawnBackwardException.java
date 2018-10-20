package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

public class PawnBackwardException extends ChessException {
    private static final String message = "Pawn cannot move backwards";

    public PawnBackwardException() {
        super(message);
    }
}
