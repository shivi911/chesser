package com.navispin.cuebiq.com.navispin.cuebiq.exceptions;

public class PawnMoveException extends ChessException {
    private static final String message = "Pawn cannot move than 2 spaces";

    public PawnMoveException() {
        super(message);
    }
}
