## Installation and Building

Once this repo is cloned, it will create a chesser directory.

### Pre-requisites for Building
* Java 8
* maven

To compile, run the following command:

`mvn clean install`

To ignore unit tests, you can run the command as:

`mvn clean install -DskipTests`

If the compilation is fine, you should find chess-1.0.jar file created under the target directory.

## Running the program

The chess program can be run with the following script:

`playChess`


You will find chess board

<pre>


  | a  | b  | c  | d  | e  | f  | g  | h |
------------------------------------------
8 | bR | bN | bB | bQ | bK | bB | bN | bR |
7 | bp | bp | bp | bp | bp | bp | bp | bp |
6 |    | ## |    | ## |    | ## |    | ## |
5 | ## |    | ## |    | ## |    | ## |    |
4 |    | ## |    | ## |    | ## |    | ## |
3 | ## |    | ## |    | ## |    | ## |    |
2 | wp | wp | wp | wp | wp | wp | wp | wp |
1 | wR | wN | wB | wQ | wK | wB | wN | wR |
------------------------------------------
  | a  | b  | c  | d  | e  | f  | g  | h |


</pre>

The game starts with White and the toggles between Black and White after each successful move. The board
will refresh with the moved pieces. The user is prompted to enter the "fromPosition" and "toPosition" to
move a chess piece from "fromPosition" to "toPosition". e.g. c2 c4

When a move is invalid, the user will be asked to repeat the input.

To exit the program, type "e" instead of specifying  a position.


## Conventions Used

### Piece convention

The convention followed is 'color''pieceType', where the color is w or b (depending on whether it is white or black)

- wp -- White pawn
- wR -- White Rook
- wN -- White Knight
- wB -- White Bishop
- wQ -- White Queen
- wK -- White King
- bp -- Black pawn
- bR -- Black Rook
- bN -- Black Knight
- bB -- Black Bishop
- bQ -- Black Queen
- bK -- Black King

## Moving Pieces on the board

Each piece on the board is located by its position in a x-y coordinate system, where x axis defines 8 columns a...h
and the Y axis defines rows 1...8

The position is defined as <column><row>. So a position "c2" indicates a chess piece in column c and row 2.

To move a chess piece from a position to another, you need to specify the coordinate on the board.

The position's valid range is <a...h><1...8>. So z1 is invalid; and so is a10.

Here are some examples of moving a chess piece on the board shown in display:

`c4 c6` would move a white pawn at coordinate (c,4) to coordinate (c,6)

`b1 c3` would move a white Knight at coordinate (b,1) to coordinate (c,3)

`c7 c5` would move a black pawn at coordinate (c,7) to coordinate (c,5)


## Design Overview

The 2 core classes are `ChessBoard` and an abstract class `ChessPiece`

The `ChessBoard` class only stores the state of the board and its pieces; and also who the current player is
(white or black). The chess pieces in the board are represented by a 2-dimensional array of size 8X8.

The key method in `ChessBoard` is `movePiece(fromPosition, toPosition)` to move a chess piece from a position
to another position. The method returns true is if the move is valid, else throws an exception.

The abstract class `ChessPiece` is the parent class for all the actual chess pieces classes -- `Pawn`, `Bishop`,
`Knight`, `King`, `Queen`, `Rook`. This class keeps a reference to the board the piece is on, stores the position where
the piece is located, and the color.
The specific classes (e.g. Pawn etc.) will need to override the methods toString and isValidMove. The toString
is used to display the piece on the board, while the isValidMove method checks if the move is legal for
the piece.
