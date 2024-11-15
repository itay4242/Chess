package Detectores;

import Board.Board;
import Piecs.*;
import Board.Square;
import main.GamePanel;
import main.MainClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CheckDetector {
    private King WKing;
    private King BKing;
    private final Board board;
    private final Square[][] SquaresBoard;
    private HashMap<Piece, List<Square>> WforcedSquares;
    private  HashMap<Piece, List<Square>> BforcedSquares;
    ArrayList<Piece> WPieces;
    ArrayList<Piece> BPieces;


    public CheckDetector(GamePanel panel) {
        this.board = Board.getBoard();
        SquaresBoard = Board.getSquaresBoard();
        WforcedSquares = new HashMap<>();
        BforcedSquares = new HashMap<>();
        WPieces = board.getWPieces();
        BPieces = board.getBPieces();

        WKing = panel.getWk();
        BKing = panel.getBk();

    }


    public void checkForMate() {
        if (WKing != null && BKing != null) {
            if (WKing.isInCheck()) {
                if (!cabMove(ChessColor.WHITE))
                    if (!canCapture(ChessColor.WHITE))
                        if (!CanBlock(ChessColor.WHITE))
                            CheckMate.callCheckMate(ChessColor.BLACK);


            } else if (BKing.isInCheck()) {
                if (!cabMove(ChessColor.BLACK))
                    if (!canCapture(ChessColor.BLACK))
                        if (!CanBlock(ChessColor.BLACK))
                            CheckMate.callCheckMate(ChessColor.WHITE);
            }
        } else
            System.out.println("one of the kings is null");
    }

    private boolean CanBlock(ChessColor color) {
        boolean canBlock = false;
        List<Piece> Attackers = getAttackers(color);
        if (Attackers.size() != 1)
            return false;
        var Attacker = Attackers.get(0);
        if (color.equals(ChessColor.WHITE)) {
            for (Piece piece : WPieces) {
                for (Square square : piece.getLegalMoves()) {
                    if (getCheckPath(Attacker).contains(square)) {
                        piece.getForcedSquare().add(square);
                        canBlock = true;
                    }
                }
            }

        } else {
            for (Piece piece : BPieces) {
                for (Square square : piece.getLegalMoves()) {
                    if (getCheckPath(Attacker).contains(square)) {
                        piece.getForcedSquare().add(square);
                        canBlock = true;
                    }
                }
            }
        }


        return canBlock;
    }


    private boolean cabMove(ChessColor color) {
        boolean canMove = !getkingForColor(color).getLegalMoves().isEmpty();
        if (canMove)
            System.out.println("the king can move and escape mate");
        else
            System.out.println("the king cannot move, checking captur and block");
        return canMove;
    }

    public Piece getkingForColor(ChessColor color) {
        if (color.equals(ChessColor.WHITE))
            return WKing;
        else return BKing;
    }


    private boolean canCapture(ChessColor color) {
        List<Piece> checkingPieces = getAttackers(color);
        if (checkingPieces.size() != 1)
            return false;

        var Attker = checkingPieces.get(0);

        if (color == ChessColor.WHITE) {

            for (Piece WPiece : WPieces)
                if (WPiece.getLegalMoves().contains(Attker.getPosition())) {
                    WPiece.getForcedSquare().add(Attker.getPosition());
                    WforcedSquares.put(WPiece, WPiece.getForcedSquare());
                }

            return !WforcedSquares.isEmpty();

        } else {
            for (Piece BPiece : BPieces)
                if (BPiece.getLegalMoves().contains(Attker.getPosition())) {
                    BPiece.getForcedSquare().add(Attker.getPosition());
                    BforcedSquares.put(BPiece, BPiece.getForcedSquare());
                }

            return !BforcedSquares.isEmpty();

        }
    }


    public List<Square> getCheckPath(Piece Attaker) {
        List<Square> checkPath = new ArrayList<>();

        if (Attaker instanceof Queen) {
            checkPath.addAll(getStraightLinePath(Attaker));
            checkPath.addAll(getDiagonalPath(Attaker));

        } else if (Attaker instanceof Rook) {
            checkPath.addAll(getStraightLinePath(Attaker));

        } else if (Attaker instanceof Bishop) {
            checkPath.addAll(getDiagonalPath(Attaker));
        }

        return checkPath;
    }

    private List<Square> getStraightLinePath(Piece Attaker) {
        List<Square> path = new ArrayList<>();
        int x1 = Attaker.getPosition().getXNum();
        int y1 = Attaker.getPosition().getYNum();
        int x2 ;
        int y2;

        if (Attaker.getColor().equals(ChessColor.WHITE)) {
             x2 = BKing.getPosition().getXNum();
             y2 = BKing.getPosition().getYNum();
        }else {
            x2 = WKing.getPosition().getXNum();
            y2 = WKing.getPosition().getYNum();
        }


        if (x1 == x2) {
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            for (int y = minY + 1; y < maxY; y++) {
                path.add(SquaresBoard[y][x1]);
            }
        } else if (y1 == y2) {

            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            for (int x = minX + 1; x < maxX; x++) {
                path.add(SquaresBoard[y2][x]);
            }
        }

        return path;
    }


    private List<Square> getDiagonalPath(Piece Attaker) {
        List<Square> path = new ArrayList<>();
        int x1 = Attaker.getPosition().getXNum();
        int y1 = Attaker.getPosition().getYNum();
        int x2;
        int y2 ;

        if (Attaker.getColor().equals(ChessColor.WHITE)) {
            x2 = BKing.getPosition().getXNum();
            y2 = BKing.getPosition().getYNum();
        }else {
            x2 = WKing.getPosition().getXNum();
            y2 = WKing.getPosition().getYNum();
        }

        int dx = Integer.signum(x2 - x1);
        int dy = Integer.signum(y2 - y1);

        int x = x1 + dx;
        int y = y1 + dy;
        while (x != x2 && y != y2) {
            path.add(SquaresBoard[y][x]);
            x += dx;
            y += dy;
        }

        return path;
    }



    public List<Piece> getAttackers(ChessColor color) {

        List<Piece> checkingPieces = new ArrayList<>();

        if (color.equals(ChessColor.WHITE)) {
            for (Piece BPiece : BPieces) {
                if (BPiece.getLegalMoves().contains(WKing.getPosition()))
                    checkingPieces.add(BPiece);
            }
        } else {
            for (Piece WPiece : WPieces) {
                if (WPiece.getLegalMoves().contains(BKing.getPosition()))
                    checkingPieces.add(WPiece);
            }
        }
        return checkingPieces;

    }

    public King getWKing() {
        return WKing;
    }

    public King getBKing() {
        return BKing;
    }

    public HashMap<Piece, List<Square>> getWforcedSquares() {
        return WforcedSquares;
    }

    public HashMap<Piece, List<Square>> getBforcedSquares() {
        return BforcedSquares;
    }

    public void ResetWforcedSquares() {
        BforcedSquares.clear();
    }
    public void ResetBforcedSquares() {
        BforcedSquares.clear();
    }
}






