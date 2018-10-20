package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.AlreadyOccupiedException;
import org.junit.Test;
import org.junit.Before;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class QueenMovesTest {
    private static boolean setUpIsDone = false;

    private static ChessBoard board;;


    @Before
    public void setupBoard() {
        if(setUpIsDone) return;
        board = new ChessBoard();
        setUpIsDone = true;
    }


    @Test
    public void testQueenMoves() throws Exception{
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

        // move white queen sidewards to already occupied spot (not allowed)
        fromPos = "d1";
        toPos = "e1";
        try {
            retVal = board.movePiece(fromPos, toPos);
            fail();
        }
        catch (AlreadyOccupiedException e) {
            assertThat(e.getMessage(), containsString("Destination position already occupie"));
        }
        // move white queen normally
        fromPos = "d1";
        toPos = "d3";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);
    }
}
