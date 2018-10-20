package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.BishopMoveException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.IllegalMoveException;
import org.junit.Test;
import org.junit.Before;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class BishopMovesTest {
    private static boolean setUpIsDone = false;

    private static ChessBoard board;;


    @Before
    public void setupBoard() {
        if(setUpIsDone) return;
        board = new ChessBoard();
        setUpIsDone = true;
    }

    @Test
    public void testBishopMoves() throws Exception {
        // move white pawn wp 2 places
        String fromPos = "d2";
        String toPos = "d4";
        boolean retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move black pawn 2 places
        fromPos = "e7";
        toPos = "e5";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move white bishop straight (not allowed)
        fromPos = "c1";
        toPos = "c3";
        try {
            retVal = board.movePiece(fromPos, toPos);
            assertEquals(true, retVal);
            fail();
        }
        catch(BishopMoveException e) {
            assertThat(e.getMessage(), containsString("Bishop can move only diagonally"));

        }

        // move white bishop normally (diagonal)
        fromPos = "c1";
        toPos = "f4";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

    }
}
