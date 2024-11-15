package Board;

import Piecs.ChessColor;
import Piecs.King;
import Piecs.Piece;

import java.util.ArrayList;

import static main.GameWindow.*;

public class Board {
    private static final Board board;
    private static final Square[][] SquaresBoard = new Square[8][8];
    private static final int SideOfSquare = 87;
    private final ArrayList<Piece> WPieces = new ArrayList<>();
    private final ArrayList<Piece> BPieces = new ArrayList<>();


    private Board() {
        for (int yNum = 0; yNum < 8; yNum++)
            for (int xNum = 0; xNum < 8; xNum++)
                SquaresBoard[yNum][xNum] = new Square(yNum, xNum,  (WINDOW_WIDTH - 790) + SideOfSquare * xNum, (WINDOW_HEIGHT - 60) - SideOfSquare * (yNum+1));

    }


    public ArrayList<Piece> getWPieces() {
        return WPieces;
    }

    public ArrayList<Piece> getBPieces() {
        return BPieces;
    }

    public static Board getBoard() {
        return board;
    }

    public static Square[][] getSquaresBoard() {
        return SquaresBoard;
    }

    public Square getSquare(int x, int y){
        for (Square[] squares : SquaresBoard)
            for (Square square : squares)
                if (square.isInSquare(x,y))
                    return square;
        System.out.println("you're not clicking in a square");
       return null;
    }

    static {
        board = new Board();
    }





    public static int getSideOfSquare(){
        return SideOfSquare;
    }




}
