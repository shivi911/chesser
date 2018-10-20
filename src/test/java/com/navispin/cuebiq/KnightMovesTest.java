package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.AlreadyOccupiedException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.IllegalMoveException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.KnightMoveException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class KnightMovesTest {
    private static boolean setUpIsDone = false;

    private static ChessBoard board;;



    @Before
    public void setupBoard() {
        if(setUpIsDone) return;
        board = new ChessBoard();
        setUpIsDone = true;
    }

    @Test
    public void testKnightMoves() throws Exception {
        // move white pawn wp 1 place
        String fromPos = "c2";
        String toPos = "c3";
        boolean retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move black knight
        fromPos = "g8";
        toPos = "f6";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move white knight to already occupied spot (not allowed)
        fromPos = "b1";
        toPos = "c3";
        try {
            retVal = board.movePiece(fromPos, toPos);
            fail();
        }
        catch(AlreadyOccupiedException ex) {
            assertThat(ex.getMessage(), containsString("Destination position already occupie"));
        }

        // move white knight straight (not allowed)
        fromPos = "b1";
        toPos = "b3";
        try {
            retVal = board.movePiece(fromPos, toPos);
            fail();
        }
        catch(KnightMoveException ex) {
            assertThat(ex.getMessage(), containsString("Knight can move only across adjacent row or column"));
        }

    }
}
