package inputs;

import Board.Board;
import Board.Square;
import Piecs.ChessColor;
import Piecs.Piece;
import main.MainClass;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static main.Game.gamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private static boolean isWhiteTurn = true;
    private static boolean isFirstClick = true;
    private final Board board;
    private Piece currPiece;
    private Square currSquare;
    private boolean EnableDots = false;

    public MouseInputs(Board board) {
        if (board == null) {
            System.out.println("the board is null");
        }
        this.board = board;
    }


    @Override
    public void mouseMoved(MouseEvent e) {


    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    public void doFirstClick(MouseEvent e) {
        try {
            currSquare = board.getSquare(e.getX(), e.getY());
            if (MainClass.IsDebugMode())
                System.out.println("youre in square, x:" + currSquare.getXNum() + "y: currSquare.getYNum()");

            currPiece = currSquare.getOccupyingPiece();

            if (currPiece == null)
                return;
            if (!isWhiteTurn) {
                if (currPiece.getColor() == ChessColor.WHITE)
                    return;
            } else if (currPiece.getColor() == ChessColor.BLACK)
                return;

            isFirstClick = !isFirstClick;
        } catch (Exception ex) {
            System.out.println("in first click:" + ex);
        }

    }

    public void doSecondClick(MouseEvent e) {
        if (currPiece == null)
            return;
        if (currSquare == null)
            return;

        try {
            currSquare = board.getSquare(e.getX(), e.getY());
            if (currPiece.getLegalMoves().contains(currSquare)) {
                currPiece.move(currSquare, board);
                isFirstClick = !isFirstClick;
                isWhiteTurn = !isWhiteTurn;
            } else if (currSquare.getOccupyingPiece() != null)
                if (currSquare.getOccupyingPiece().getColor() == currPiece.getColor()) {
                    doFirstClick(e);
                    EnableDots = true;
                }
        } catch (Exception ex) {
            System.out.println("in second click:" + ex);
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isFirstClick) {
            doFirstClick(e);
            EnableDots = true;
        } else {
            doSecondClick(e);
            EnableDots = false;
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {


    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public boolean EnableDots() {
        return EnableDots;
    }

    public Piece currPiece() {
        return currPiece;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
}
