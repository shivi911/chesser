package com.navispin.cuebiq;

import com.navispin.cuebiq.com.navispin.cuebiq.exceptions.ChessException;

import java.io.IOException;
import java.util.Scanner;


/**
 * this is the driver class that runs the Chess program
 */
public class App 
{
    public static void main( String[] args )
    {
        ChessBoard chessBoard = new ChessBoard();
        String color = "White";
        Color clr = Color.WHITE;
        boolean inputError = false;
        String errMessage = "";

        // showHelp(chessBoard);

        while(true){

            if(inputError) {
                // show the chessboard
                System.out.println(chessBoard);
                System.out.println(errMessage);
                System.out.print("Sorry " + color + " please try again: ");

            }
            else {
                // show the chessboard
                System.out.println(chessBoard);
                System.out.print(color + " make a move: ");
            }
            Scanner sc = new Scanner(System.in);
            String move = sc.nextLine();
            if(move.equals("e")) { // special case to exit the app
                System.exit(0);
            }

            try {
                String[] positions = parseInput(move);
                String fromPos = positions[0];
                String toPos = positions[1];
                chessBoard.movePiece(fromPos, toPos);
                // chessBoard.movePiece(move, color);
                inputError = false;
                errMessage = "";
            } catch (ChessException e) {
                // Ask for user input again
                // System.out.println("Invalid input! " + e.getMessage());
                errMessage = "Invalid input! " + e.getMessage();
                inputError = true;
                continue;
            }
            catch (IOException e) {

                // System.out.println("Invalid input! " + e.getMessage());
                errMessage = "Invalid input! " + e.getMessage();
                inputError = true;
                continue;
            }

            // switch to the opponent player for next move
            color = changePlayer(color);
            if(color.equals("black")) clr = Color.BLACK;
            else clr = Color.WHITE;

        }

    }

    public static String changePlayer(String color){
        if(color.equals("White")){
            return "Black";
        }

        return "White";
    }

    /**
     * Split the user input into from position and to position
     * @param move
     * @return an array of size 2; the first element for from position and 2nd one for to position
     * @throws IOException
     */
    public static String[] parseInput(String move) throws IOException {
        if(move != null && move.length() !=5) throw new IOException("Input format not correct. Valid input example: c2 c3");

        String[] split = move.split(" ");

        return split;

    }


    public static void showHelp(ChessBoard chessBoard) {
        System.out.println("From the chess board shown below, each chess square is represented by rows and columns.\n");
        System.out.println("Each move comprises of a source location and destination location separated by a space\n");
        System.out.println("For example, c2 c3 moves a white pawn from column c, row 2 to column c, row3\n");

        System.out.println(chessBoard);
    }
}
