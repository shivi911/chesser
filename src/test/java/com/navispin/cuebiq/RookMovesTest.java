package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.AlreadyOccupiedException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.RookMoveException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RookMovesTest {
    private static boolean setUpIsDone = false;

    private static ChessBoard board;;



    @Before
    public void setupBoard() {
        if(setUpIsDone) return;
        board = new ChessBoard();
        setUpIsDone = true;
    }


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testRookMoves() throws Exception {

        // move white pawn wp 2 places
        String fromPos = "h2";
        String toPos = "h4";
        boolean retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move black pawn 2 places
        fromPos = "a7";
        toPos = "a5";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move white rook pawn 2 places
        fromPos = "h1";
        toPos = "h3";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // try illegal black rook move
        fromPos = "a8";
        toPos = "b8";
        try {
            retVal = board.movePiece(fromPos, toPos);
            fail();
        }
        catch(AlreadyOccupiedException ex) {
            assertThat(ex.getMessage(), containsString("Destination position already occupie"));
        }

        // move black rook pawn 1 place
        fromPos = "a8";
        toPos = "a7";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // move white rook diagonally (not allowed)
        fromPos = "h3";
        toPos = "g4";
        try {
            retVal = board.movePiece(fromPos, toPos);
            assertEquals(true, retVal);
            fail();
        }
        catch(RookMoveException ex) {
            assertThat(ex.getMessage(), containsString("Rook cannot move across rows or columns"));
        }


    }

}
