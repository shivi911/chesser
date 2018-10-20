package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.AlreadyOccupiedException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.KingMoveException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class KingMovesTest {
    private static boolean setUpIsDone = false;

    private static ChessBoard board;;



    @Before
    public void setupBoard() {
        if(setUpIsDone) return;
        board = new ChessBoard();
        setUpIsDone = true;
    }

    @Test
    public void testKingMoves() throws Exception {
        // move white pawn wp 2 places
        String fromPos = "e2";
        String toPos = "e4";
        boolean retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move black pawn 2 places
        fromPos = "e7";
        toPos = "e5";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);


        // move white king 1 place
        fromPos = "e1";
        toPos = "e2";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move black king sidewards to an filled position (not allowed)
        fromPos = "e8";
        toPos = "d8";
        try {
            retVal = board.movePiece(fromPos, toPos);
            fail();
        }
        catch(AlreadyOccupiedException e) {
            assertThat(e.getMessage(), containsString("Destination position already occupied"));
        }

        // move black king 2 places forward (not allowed)
        fromPos = "e8";
        toPos = "e6";
        try {
            retVal = board.movePiece(fromPos, toPos);
            fail();
        }
        catch(KingMoveException e) {
            assertThat(e.getMessage(), containsString("King cannot move more than 1 spot"));

        }
    }
}
