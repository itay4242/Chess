package main;

import Board.Board;
import Board.Square;
import Detectores.CheckDetector;
import Detectores.CheckMate;
import Piecs.*;
import inputs.*;
import utilz.LoadSave;

import java.awt.*;
import javax.swing.JPanel;

import static Board.Board.getBoard;
import static Board.Board.getSquaresBoard;

import static main.GameWindow.WINDOW_HEIGHT;
import static main.GameWindow.WINDOW_WIDTH;
import static utilz.LoadSave.getLoadSave;

public class GamePanel extends JPanel {
    private final Board board;
    private final Square[][] SquaresBoard;
    private final LoadSave loadSave;
    public static int BOARD_WIDTH = 700, BOARD_HEIGHT = 700;

    boolean bp = true;
    boolean wp = true;

    private King wk;
    private King bk;

    private boolean isInit;

    Color grey = new Color(160, 160, 164, 73);

    MouseInputs mouseInputs;
    private final CheckDetector cd;


    public GamePanel() {
        loadSave = getLoadSave();
        board = getBoard();
        SquaresBoard = getSquaresBoard();
        mouseInputs = new MouseInputs(board);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        initializePieces();
        cd = new CheckDetector(this);

        CheckMate.run(this, cd);


    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));

        g.drawImage(loadSave.getBOARD_IMG(), WINDOW_WIDTH - 800, WINDOW_HEIGHT - 770, BOARD_WIDTH, BOARD_HEIGHT, null);
        g.fillRect(0, 20, WINDOW_WIDTH - 850, WINDOW_HEIGHT - 40);
        g.drawImage(loadSave.getLogoImg(), 130, 30, 150, 150, null);


        g.setColor(Color.WHITE);
        g.drawString("  -♙♖♘♗♕♔♗♘♖♙---------Chess Nate --------♟♜♞♝♛♚♝♞♜♟-  ", 0, 200);


        g.setColor(grey);
        if (!(mouseInputs.currPiece() == null))
            if (mouseInputs.currPiece().getColor() == ChessColor.WHITE && mouseInputs.isWhiteTurn() ||
                    mouseInputs.currPiece().getColor() == ChessColor.BLACK && !mouseInputs.isWhiteTurn())
                for (Square square : mouseInputs.currPiece().getLegalMoves())

                    if (mouseInputs.EnableDots())
                        if (!square.isOccupied())
                            g.fillOval(square.getX() + 18, square.getY() + 18, 30, 30);
                        else if (square.getOccupyingPiece().getColor() != mouseInputs.currPiece().getColor())
                            g2d.drawOval(square.getX() - 7, square.getY() - 10, 85, 85);


        for (Piece Wpiece : board.getBPieces())
            Wpiece.Draw(g);

        for (Piece Bpiece : board.getWPieces())
            Bpiece.Draw(g);


    }


    private void initializePieces() {

        wk = new King(ChessColor.WHITE, SquaresBoard[0][4], loadSave.getWKingImg());
        bk = new King(ChessColor.BLACK, SquaresBoard[7][4], loadSave.getBKingImg());

        SquaresBoard[7][4].put(bk);
        SquaresBoard[0][4].put(wk);

        for (int x = 0; x < 8; x++) {
            SquaresBoard[1][x].put(new Pawn(ChessColor.WHITE, SquaresBoard[1][x], loadSave.getWPawnImg()));
            SquaresBoard[6][x].put(new Pawn(ChessColor.BLACK, SquaresBoard[6][x], loadSave.getBPawnImg()));
        }

        SquaresBoard[7][3].put(new Queen(ChessColor.BLACK, SquaresBoard[7][3], loadSave.getBQueenImg()));
        SquaresBoard[0][3].put(new Queen(ChessColor.WHITE, SquaresBoard[0][3], loadSave.getWQueenImg()));


        SquaresBoard[0][0].put(new Rook(ChessColor.WHITE, SquaresBoard[0][0], loadSave.getWRookImg()));
        SquaresBoard[0][7].put(new Rook(ChessColor.WHITE, SquaresBoard[0][7], loadSave.getWRookImg()));
        SquaresBoard[7][0].put(new Rook(ChessColor.BLACK, SquaresBoard[7][0], loadSave.getBRookImg()));
        SquaresBoard[7][7].put(new Rook(ChessColor.BLACK, SquaresBoard[7][7], loadSave.getBRookImg()));

        SquaresBoard[0][1].put(new Knight(ChessColor.WHITE, SquaresBoard[0][1], loadSave.getWNightImg()));
        SquaresBoard[0][6].put(new Knight(ChessColor.WHITE, SquaresBoard[0][6], loadSave.getWNightImg()));
        SquaresBoard[7][1].put(new Knight(ChessColor.BLACK, SquaresBoard[7][1], loadSave.getBNightImg()));
        SquaresBoard[7][6].put(new Knight(ChessColor.BLACK, SquaresBoard[7][6], loadSave.getBNightImg()));

        SquaresBoard[0][2].put(new Bishop(ChessColor.WHITE, SquaresBoard[0][2], loadSave.getWBishopImg()));
        SquaresBoard[0][5].put(new Bishop(ChessColor.WHITE, SquaresBoard[0][5], loadSave.getWBishopImg()));
        SquaresBoard[7][2].put(new Bishop(ChessColor.BLACK, SquaresBoard[7][2], loadSave.getBBishopImg()));
        SquaresBoard[7][5].put(new Bishop(ChessColor.BLACK, SquaresBoard[7][5], loadSave.getBBishopImg()));


        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 8; x++) {
                board.getWPieces().add(SquaresBoard[y][x].getOccupyingPiece());
                board.getBPieces().add(SquaresBoard[7 - y][x].getOccupyingPiece());
            }
        }

        for (Piece Wp : board.getWPieces()) {
            if (Wp == null) {
                wp = false;
                break;
            }
        }

        for (Piece Bp : board.getBPieces()) {
            if (Bp == null) {
                bp = false;
                break;
            }
        }

        if (bp && wp) {
            isInit = true;
            System.out.println("very init");
        } else
            System.out.println("not init");
    }

    public void soutForced() {
        if (mouseInputs.currPiece().getColor().equals(ChessColor.WHITE)) {
            var WForcedSq = cd.getWforcedSquares();
            if (WForcedSq == null)  {
                System.out.println("the forced was empty");
                return;
            }
            for (Piece Wpiece : board.getWPieces()) {
                if (WForcedSq.get(Wpiece) == null) {
                    System.out.println("the piece dont have a forced squares");
                    return;
                }
                for (Square square : Wpiece.getRawLegalMoves())
                    if (WForcedSq.get(Wpiece).contains(square))
                        System.out.println("the white " + Wpiece.getClass().getName() + "forced to move to:   x: " + square.getXNum() + " y:  " + square.getYNum());
                    else
                        System.out.println("the white " + Wpiece.getClass().getName() + "cannot  to move to:   x: " + square.getXNum() + " y:  " + square.getYNum());

            }

        } else {
            var BForcedSq = cd.getBforcedSquares();
            if (BForcedSq == null) {
                System.out.println("the forced was empty");
                return;
            }
            for (Piece Bpiece : board.getWPieces()) {
                if (BForcedSq.get(Bpiece) == null){
                    System.out.println("the piece dont have a forced squares");
                    return;
                }

                for (Square square : Bpiece.getRawLegalMoves())
                    if (BForcedSq.get(Bpiece).contains(square))
                        System.out.println("the black " + Bpiece.getClass().getName() + "forced to move to:   x: " + square.getXNum() + " y:  " + square.getYNum());
                    else
                        System.out.println("the black " + Bpiece.getClass().getName() + "cannot  to move to:   x: " + square.getXNum() + " y:  " + square.getYNum());

            }

        }

    }
    // פעולה שמקבלת צבע ועוברת על כל השחקנים לראות אם יש מהלך שהם חייבים לעשות
    // (בשביל כל שחקן שרוצה לעשות מהלך ולו אין מהלך שהוא חייב לעשות.)
    public boolean isForcedSquaresForColor(ChessColor color){
        boolean isForced = false;
        if (color.equals(ChessColor.WHITE)){
            if (!cd.getWforcedSquares().isEmpty())
                isForced = true;
        }else {
                if (!cd.getBforcedSquares().isEmpty())
                    isForced = true;
        }

        return isForced;
    }


    public Board board() {
        return board;
    }


    public LoadSave getloadSave() {
        return loadSave;
    }

    public static int getBOARD_WIDTH() {
        return BOARD_WIDTH;
    }

    public static int getBOARD_HEIGHT() {
        return BOARD_HEIGHT;
    }

    public Color getGrey() {
        return grey;
    }

    public MouseInputs getMouseInputs() {
        return mouseInputs;
    }

    public King getWk() {
        if (isInit)
            return wk;
        System.out.println("is init?:" + isInit);
        return null;
    }

    public King getBk() {
        if (isInit)
            return bk;
        System.out.println("is init?:" + isInit);
        return null;
    }

    public boolean isInit() {
        return isInit;
    }

    public CheckDetector getCd() {
        return cd;
    }
}

