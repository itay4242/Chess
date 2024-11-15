package Piecs;

import Board.Square;
import main.MainClass;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static Board.Board.getSquaresBoard;
public class Pawn extends Piece{

    private boolean wasMoved;

    public Pawn(ChessColor color, Square currentSquare, BufferedImage pieceImg) {
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
        });        if (super.getForcedSquare().isEmpty()) {
            if (MainClass.IsDebugMode())
             System.out.println("the forced move is empty");
            if (isForcedSquares())
                return new ArrayList<>();
            return LegalMoves; // need to add pin
        }
        else
            LegalMoves.removeIf(square -> !getForcedSquare().contains(square));
        return LegalMoves;

    }

    @Override
    public List<Square> getRawLegalMoves() {
        LinkedList<Square> legalMoves = new LinkedList<>();

        Square[][] board = getSquaresBoard();

        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();
        ChessColor c = this.getColor();

        if (c == ChessColor.WHITE) {
            if (!wasMoved) {
                if (!board[y + 2][x].isOccupied()) {
                    legalMoves.add(board[y + 2][x]);
                }
            }

            if (y + 1 < 8) {
                if (!board[y + 1][x].isOccupied()) {
                    legalMoves.add(board[y + 1][x]);
                }
            }

            if (x + 1 < 8 && y + 1 < 8) {
                if (board[y + 1][x + 1].isOccupied()) {
                    legalMoves.add(board[y + 1][x + 1]);
                }
            }

            if (x - 1 >= 0 && y + 1 < 8) {
                if (board[y + 1][x - 1].isOccupied()) {
                    legalMoves.add(board[y + 1][x - 1]);
                }
            }

//            if (x + 1 < 8 && y + 1 < 8) {
//                if (board[y][x + 1].isOccupied() && board[y][x + 1].getOccupyingPiece() instanceof Pawn pawn && pawn.isUnPassant()) {
//                    legalMoves.add(board[y + 1][x + 1]);
//                }
//            }
//
//            if (x - 1 >= 0 && y + 1 < 8) {
//                if (board[y][x - 1].isOccupied()&& board[y][x - 1].getOccupyingPiece() instanceof Pawn pawn && pawn.isUnPassant()) {
//                    legalMoves.add(board[y + 1][x - 1]);
//                }
//            }



        }

        if (c == ChessColor.BLACK) {
            if (!wasMoved) {
                if (!board[y - 2][x].isOccupied()) {
                    legalMoves.add(board[y - 2][x]);
                }
            }

            if (y - 1 >= 0) {
                if (!board[y - 1][x].isOccupied()) {
                    legalMoves.add(board[y - 1][x]);
                }
            }

            if (x + 1 < 8 && y - 1 >= 0) {
                if (board[y - 1][x + 1].isOccupied()) {
                    legalMoves.add(board[y - 1][x + 1]);
                }
            }

            if (x - 1 >= 0 && y - 1 >= 0) {
                if (board[y - 1][x - 1].isOccupied()) {
                    legalMoves.add(board[y - 1][x - 1]);
                }
            }

//            if (x + 1 < 8 && y - 1 >= 0) {
//                if (board[y][x + 1].isOccupied()&& board[y][x + 1].getOccupyingPiece() instanceof Pawn pawn && pawn.isUnPassant()) {
//                    legalMoves.add(board[y - 1][x + 1]);
//                }
//            }
//
//            if (x - 1 >= 0 && y - 1 >= 0) {
//                if (board[y][x - 1].isOccupied()&& board[y][x + 1].getOccupyingPiece() instanceof Pawn pawn && pawn.isUnPassant()) {
//                    legalMoves.add(board[y - 1][x - 1]);
//                }
//            }

        }

        return legalMoves;
    }


    public Pawn setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
        return this;
    }
}
