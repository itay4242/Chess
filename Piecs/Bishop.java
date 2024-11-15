package Piecs;

import Board.Square;
import main.MainClass;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static Board.Board.getSquaresBoard;

public class Bishop extends Piece {
    public Bishop(ChessColor color, Square currentSquare, BufferedImage pieceImg) {
        super(color, currentSquare, pieceImg);
    }

    @Override
    public List<Square> getLegalMoves() {
        List<Square> LegalMoves = getRawLegalMoves();
        LegalMoves.removeIf(square ->
        {
             if (square.isOccupied())
            return square.getOccupyingPiece().getColor().equals(this.getColor());
             else return false;
        });
        if (super.getForcedSquare().isEmpty()) {
            if (MainClass.IsDebugMode())
                System.out.println("the forced moves for " + this + " is empty");
            if (isForcedSquares())
                return new ArrayList<>();
            return LegalMoves; // need to add pin
        } else
            LegalMoves.removeIf(square -> !getForcedSquare().contains(square));
        return LegalMoves;
    }

    @Override
    public List<Square> getRawLegalMoves() {
        Square[][] board = getSquaresBoard();
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();

        return getDiagonalOccupations(board, x, y);
    }

    @Override
    public String toString() {
        return "Bishop{color:" + this.getColor() + "}";
    }
}
