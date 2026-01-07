package Games;

import Listener.KeyBoardInputs;
import Listener.MouseInputs;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel{
    private MouseInputs mouseInputs;
    private Game game;


    private void setPanelSize()
    {
        Dimension dimension = new Dimension(Game.GAME_WIDTH,Game.GAME_HEIGTH);
        setPreferredSize(dimension);
    }

    public GamePanel(Game game)
    {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void updateGame()
    {

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame()
    {
        return game;
    }
}
