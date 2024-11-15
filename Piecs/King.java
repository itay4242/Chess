package Piecs;

import Board.Board;
import Board.Square;
import main.MainClass;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private boolean wasMoved = false;

    private boolean isInCheck = false;

    public King(ChessColor color, Square currentSquare, BufferedImage pieceImg) {
        super(color, currentSquare, pieceImg);
    }

    @Override
    public List<Square> getLegalMoves() {
        List<Square> legalMoves = getRawLegalMoves();
        legalMoves.removeIf(square -> {
            if (square.isOccupied()) {
                Piece occupyingPiece = square.getOccupyingPiece();
                if (occupyingPiece.getColor() == this.getColor()) {
                    return true;
                } else {
                    return isProtected(square, Board.getBoard());
                }
            } else {
                return false;
            }
        });
        return legalMoves;
    }

    @Override
    public List<Square> getRawLegalMoves() {
        List<Square> legalMoves = new ArrayList<>();
        Square[][] squaresBoard = Board.getSquaresBoard();
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();


        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                try {
                    Square targetSquare = squaresBoard[y + dy][x + dx];
                    if (!targetSquare.isOccupied() || targetSquare.getOccupyingPiece().getColor() != this.getColor())
                        legalMoves.add(targetSquare);

                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }

        if (!this.wasMoved()) {
            if (canCastle(squaresBoard, x, y, true)) {
                legalMoves.add(squaresBoard[y][x + 2]);
            }
            if (canCastle(squaresBoard, x, y, false)) {
                legalMoves.add(squaresBoard[y][x - 2]);
            }
        }

        return legalMoves;
    }

    private boolean canCastle(Square[][] squaresBoard, int x, int y, boolean kingSide) {
        int rookX = kingSide ? 7 : 0;
        int direction = kingSide ? 1 : -1;

        Piece rook = squaresBoard[y][rookX].getOccupyingPiece();
        if (!(rook instanceof Rook) || ((Rook) rook).wasMoved()) {
            return false;
        }

        for (int i = x + direction; i != rookX; i += direction) {
            if (squaresBoard[y][i].isOccupied()) {
                return false;
            }
        }


        return true;
    }

    private boolean isProtected(Square square, Board board) {
        String color;
        if (this.getColor() == ChessColor.WHITE)
            color = "white";
        else color = "black";

        if (color.equals("white")) {
            for (Piece Bpiece : board.getBPieces()) {
                if (Bpiece.getRawLegalMoves().contains(square)) {
                    if (MainClass.IsDebugMode())
                        System.out.println("there is a black piece that block you and he is:" + Bpiece.getClass().getName() + "the x is:" + Bpiece.getPosition().getXNum() + "the y is:" + Bpiece.getPosition().getYNum());
                    return true;
                }
            }
        } else
            for (Piece Wpiece : board.getWPieces()) {
                if (Wpiece.getRawLegalMoves().contains(square)) {
                    if (MainClass.IsDebugMode())
                        System.out.println("there is a black piece that block you and he is:" + Wpiece.getClass().getName() + " the x is:" + Wpiece.getPosition().getXNum() + "the y is:" + Wpiece.getPosition().getYNum());
                    return true;
                }
            }
        if (MainClass.IsDebugMode())
            System.out.println("there was no one stoping the " + color + " king can move to the x :" + square.getXNum() + " and the y :" + square.getYNum());
        return false;


    }


    public boolean wasMoved() {
        return wasMoved;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
    }
    public boolean isInCheck() {
        return isInCheck;
    }

    public void callCheck(){
        this.isInCheck = true;
        if (main.MainClass.IsDebugMode())
          System.out.println("the " + getColor() + " king is in check");
    }

    public void stopCheck(){
        this.isInCheck = false;
        if (main.MainClass.IsDebugMode())
            System.out.println("the " + getColor() + " king is not check");
    }
}
