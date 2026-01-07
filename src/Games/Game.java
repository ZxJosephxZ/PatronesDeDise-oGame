package Games;

import Levels.LevelManager;
import entities.Player;

import java.awt.*;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread thread;
    private final int FPS = 120;
    private final int UPS = 200;
    private Player player;
    private LevelManager levelManager;
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
        levelManager = new LevelManager(this);
        player = new Player(200,300);
        player.loadLvData(levelManager.getCurrentLv().getLevelData());
    }

    private void gameStart()
    {
        thread = new Thread(this);
        thread.start();
    }

    public void update()
    {
        player.update();
        levelManager.update();
    }

    public void render(Graphics g)
    {
        levelManager.draw(g);
        player.render(g);

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
        player.resetDirBooleans();
    }

    public Player getPlayer()
    {
        return player;
    }
}
