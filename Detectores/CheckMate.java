package Detectores;

import Piecs.ChessColor;
import main.GamePanel;
import main.MainClass;

public class CheckMate {

    //call the checkmate window and ask if start a new game or reviw the game
    public static void callCheckMate(ChessColor color){
        System.out.println("the " + color.name() + " won the game by checkmate");

    }



    public static void run(GamePanel gamePanel,CheckDetector cd) {
         final int TPS = 1;
         final boolean SHOW_TPS = MainClass.IsDebugMode();
        new Thread(() -> {
            double timePerFrame = 1000000000.0 / TPS;
            long previousTime = System.nanoTime();
            int frames = 0;
            long lastCheck = System.currentTimeMillis();
            double deltaFrames = 0;

            while (true) {
                long currentTime = System.nanoTime();
                deltaFrames += (currentTime - previousTime) / timePerFrame;
                previousTime = currentTime;

                if (deltaFrames >= 1) {
                    if (gamePanel.isInit()){
                        cd.checkForMate();
                        if (MainClass.IsDebugMode())
                         System.out.println("Checking for mate");
                    }
                    frames++;
                    deltaFrames--;
                }

                if (SHOW_TPS) {
                    if (System.currentTimeMillis() - lastCheck >= 1000) {
                        lastCheck = System.currentTimeMillis();
                        System.out.println("TPS:" + frames);
                        frames = 0;
                    }
                }
            }
        }).start();
    }
}
