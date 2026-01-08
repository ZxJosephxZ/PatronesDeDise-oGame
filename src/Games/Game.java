package Games;

import gameState.GameState;
import gameState.Menu;
import gameState.Playing;

import java.awt.*;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread thread;
    private final int FPS = 120;
    private final int UPS = 200;
    private Playing playing;
    private Menu menu;
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 2.0f;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGTH = TILES_SIZE * TILES_IN_HEIGHT;

    public Game()
    {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocusInWindow();
        gameStart();

    }

    private void initClasses()
    {

    }

    private void gameStart()
    {
        thread = new Thread(this);
        thread.start();
    }

    public void update()
    {
        switch (GameState.state){
            case MENU:
                break;
            case PLAYING:

                break;
            default:
                break;
        }
    }

    public void render(Graphics g)
    {

        switch (GameState.state){
            case MENU:
                break;
            case PLAYING:

                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        double currentFps = 1000000000.0/FPS;
        double currentUps = 1000000000.0/UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;
        while(true)
        {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / currentUps;
            deltaF += (currentTime - previousTime) / currentFps;
            previousTime = currentTime;

            if (deltaU >= 1)
            {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1)
            {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000)
            {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: "+frames+" UPS: "+ updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost()
    {

    }
    public Menu getMenu()
    {
        return menu;
    }
    public Playing getPlaying()
    {
        return playing;
    }
}
