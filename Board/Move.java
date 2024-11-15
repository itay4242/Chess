package Board;

import Piecs.Piece;
import Piecs.Rook;

public class Move {
    private final Square StartSquare;
    private final Square EndSquare;
    private final Piece pieceMoved;
    private final Rook movedForCastling;
    private final int score;
    private final Move_Classifications classifications;


    public Move(Square startSquare, Square endSquare, Piece pieceMoved, Rook movedForCastling, int score, Move_Classifications classifications) {
        StartSquare = startSquare;
        EndSquare = endSquare;
        this.pieceMoved = pieceMoved;
        this.movedForCastling = movedForCastling;
        this.score = score;
        this.classifications = classifications;
    }

    public Square StartSquare() {
        return StartSquare;
    }

    public Square EndSquare() {
        return EndSquare;
    }

    public Piece pieceMoved() {
        return pieceMoved;
    }

    public Rook movedForCastling() {
        return movedForCastling;
    }

    public int score() {
        return score;
    }

    public Move_Classifications classifications() {
        return classifications;
    }
}
