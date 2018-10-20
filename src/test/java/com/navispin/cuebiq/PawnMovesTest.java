package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.NoPieceExistsException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.PawnBackwardException;
import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.WrongPlayerException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class PawnMovesTest {
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
    public void testPawnMove() throws Exception {
        String fromPos = "c2";
        String toPos = "c3";
        boolean retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);

        // white playing instead of black

        fromPos = "d2";
        toPos = "d1";
        // thrown.expect(WrongPlayerException.class);
        try {
            board.movePiece(fromPos, toPos);
            fail();
        }
        catch (WrongPlayerException e) {
            assertThat(e.getMessage(), containsString("Cannot move piece of other player"));
        }

        fromPos = "e7";
        toPos = "e5";
        retVal = board.movePiece(fromPos, toPos);
        assertEquals(true, retVal);


        fromPos = "c3";
        toPos = "c2";
        // thrown.expect(NoPieceExistsException.class);
        try {
            retVal = board.movePiece(fromPos, toPos);
            fail();
        }
        catch(PawnBackwardException e) {
            assertThat(e.getMessage(), containsString("Pawn cannot move backwards"));
        }


    }


}
