package trash;

import Board.Square;
import Piecs.*;

import java.util.List;

public class blocking {
//in Detector
//    private boolean isBlockingCheck(Piece attaker, Piece defender, Square square) {
//        if (attaker instanceof Rook) {
//            if (attaker.getPosition().getXNum() == WKing.getPosition().getXNum()) {
//                if (square.getXNum() == attaker.getPosition().getXNum()) {
//                    if (square.getYNum() > WKing.getPosition().getYNum() && square.getYNum() < attaker.getPosition().getYNum() ||
//                            square.getYNum() < WKing.getPosition().getYNum() && square.getYNum() > attaker.getPosition().getYNum()) {
//                        defender.getForcedSquare().add(square);
//                        return true;
//                    }
//                }
//            } else if (attaker.getPosition().getYNum() == WKing.getPosition().getYNum()) {
//                if (square.getYNum() == attaker.getPosition().getYNum()) {
//                    if (square.getXNum() > WKing.getPosition().getXNum() && square.getXNum() < attaker.getPosition().getXNum() ||
//                            square.getXNum() < WKing.getPosition().getXNum() && square.getXNum() > attaker.getPosition().getXNum()) {
//                        defender.getForcedSquare().add(square);
//                        return true;
//                    }
//                }
//            }
//        } else if (attaker instanceof Bishop) {
//            int dx = Integer.signum(WKing.getPosition().getXNum() - attaker.getPosition().getXNum());
//            int dy = Integer.signum(WKing.getPosition().getYNum() - attaker.getPosition().getYNum());
//
//
//            for (int i = 1; i < 8; i++) {
//                int x = attaker.getPosition().getXNum() + dx * i;
//                int y = attaker.getPosition().getYNum() + dy * i;
//
//                if (x == WKing.getPosition().getXNum() && y == WKing.getPosition().getYNum()) {
//                    break;
//                }
//
//                Square square1 = SquaresBoard[x][y];
//                if (square1 != null && defender.getLegalMoves().contains(square1)) {
//
//                    if (isBlockingCheck(attaker, defender, square1)) {
//                        defender.getForcedSquare().add(square1);
//                        return true;
//                    }
//                }
//            }
//        } else if (attaker instanceof Queen) {
//            int dx = Integer.signum(WKing.getPosition().getXNum() - attaker.getPosition().getXNum());
//            int dy = Integer.signum(WKing.getPosition().getYNum() - attaker.getPosition().getYNum());
//
//            // Check horizontally and vertically like a rook
//            if (attaker.getPosition().getXNum() == WKing.getPosition().getXNum()) {
//                for (int i = 1; i < 8; i++) {
//                    int y = attaker.getPosition().getYNum() + dy * i;
//                    if (y == WKing.getPosition().getYNum()) {
//                        break;
//                    }
//
//                    Square square1 = SquaresBoard[attaker.getPosition().getXNum()][y];
//                    if (square1 != null && defender.getLegalMoves().contains(square1)) {
//                        if (isBlockingCheck(attaker, defender, square1)) {
//                            defender.getForcedSquare().add(square1);
//                            return true;
//                        }
//                    }
//                }
//            }
//
//            if (attaker.getPosition().getYNum() == WKing.getPosition().getYNum()) {
//                for (int i = 1; i < 8; i++) {
//                    int x = attaker.getPosition().getXNum() + dx * i;
//                    if (x == WKing.getPosition().getXNum()) {
//                        break;
//                    }
//
//                    Square square1 = SquaresBoard[x][attaker.getPosition().getYNum()];
//                    if (square1 != null && defender.getLegalMoves().contains(square1)) {
//                        if (isBlockingCheck(attaker, defender, square1)) {
//                            defender.getForcedSquare().add(square1);
//                            return true;
//                        }
//                    }
//                }
//            }
//
//            // Check diagonally like a bishop
//            int dxx = Integer.signum(WKing.getPosition().getXNum() - attaker.getPosition().getXNum());
//            int dyy = Integer.signum(WKing.getPosition().getYNum() - attaker.getPosition().getYNum());
//
//            for (int i = 1; i < 8; i++) {
//                int x = attaker.getPosition().getXNum() + dxx * i;
//                int y = attaker.getPosition().getYNum() + dyy * i;
//
//                if (x == WKing.getPosition().getXNum() && y == WKing.getPosition().getYNum()) {
//                    break;
//                }
//
//                Square square1 = SquaresBoard[x][y];
//                if (square1 != null && defender.getLegalMoves().contains(square1)) {
//                    if (isBlockingCheck(attaker, defender, square1)) {
//                        defender.getForcedSquare().add(square1);
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    //not compeate!!
//    private boolean CanBlock(ChessColor color) {
//        List<Piece> Attackers = getAttackers(color);
//        if (Attackers.size() != 1)
//            return false;
//        var Attacker = Attackers.get(0);
//        if (color.equals(ChessColor.WHITE)) {
//            for (Piece WPiece : WPieces) {
//
//            }
//        } else {
//        }
//        return false;
//    }
//
//    private boolean pieceCanBlock(Piece Attaker, Piece defender) {
//        boolean isOk = false;
//        for (Square square : defender.getLegalMoves()) {
//            if (Attaker.getRawLegalMoves().contains(square))
//                if (isBlockingCheck(Attaker, defender, square)) {
//                    isOk = true;
//                    defender.getForcedSquare().add(square);
//                }
//        }
//        return isOk;
//    }
}
