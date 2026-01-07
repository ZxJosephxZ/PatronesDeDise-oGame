package entities;

import Games.Game;
import UtilZ.HelpMethods;
import UtilZ.LoadSave;
import UtilZ.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    private BufferedImage[][] animationPlayer;
    private int playerStatus = Utils.PlayerConstants.IDLE;
    private int playerFrame ;
    private int tickFrame;
    private static final int playerWidth = (int)(32*Game.SCALE);
    private static final int playerHeigth = (int)(32*Game.SCALE);
    private int playerSpeed = 15;
    private boolean moving = false;
    private boolean atacking = false;
    private boolean up,left,down,right;
    private float playerVel = 2.0f;
    private int [][] lvlData;
    private float xDrawOffSet = 10 * Game.SCALE;
    private float yDrawOffSet = 7 * Game.SCALE;
    public Player(float x, float y) {
        super(x, y, playerWidth,playerHeigth);
        loadImage();
        initHitBox(x,y,12*Game.SCALE, 20*Game.SCALE);
    }
    public void update()
    {
        updatePosition();
        updateFrame();
        setAnimation();
    }

    public void render(Graphics g)
    {
        g.drawImage(animationPlayer[playerStatus][playerFrame],(int)(hitBox.x-xDrawOffSet),(int)(hitBox.y-yDrawOffSet),playerWidth,playerHeigth,null);
        drawHitBox(g);
    }

    public void setAtacking(boolean atacking)
    {
        this.atacking = atacking;
    }

    private void setAnimation()
    {
        int tempStatus = playerStatus;
        if (moving)
        {
            playerStatus = Utils.PlayerConstants.MOVING;
        }
        else {
            playerStatus = Utils.PlayerConstants.IDLE;
        }

        if(atacking)
        {
            playerStatus = Utils.PlayerConstants.ATACK;
        }
        if (tempStatus != playerStatus)
        {
            resetAnimation();
        }
    }

    private void resetAnimation()
    {
        tickFrame = 0;
        playerFrame = 0;
    }

    private void updatePosition()
    {
        moving = false;
        if(!left && ! right && !up && !down)
        {
            return;
        }

        float xSpeed = 0;
        float ySpeed = 0;

        if (left && !right)
        {
            xSpeed = -playerVel;
        }
        else if (right && !left)
        {
            xSpeed = playerVel;
        }

        if (up && !down)
        {
            ySpeed = -playerVel;
        }
        else if (down && !up)
        {
            ySpeed = playerVel;
        }

        if (HelpMethods.CanMoveHere(hitBox.x +xSpeed, hitBox.y +ySpeed, hitBox.width, hitBox.height, lvlData))
        {
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            moving = true;
        }
    }

    private void updateFrame()
    {
        tickFrame++;
        if(tickFrame >= playerSpeed)
        {
            tickFrame = 0;
            playerFrame++;
            if(playerFrame >= Utils.PlayerConstants.getFrames(playerStatus))
            {
                playerFrame = 0;
                atacking = false;
            }
        }
    }


    private void loadImage()
    {
        BufferedImage image = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animationPlayer = new BufferedImage[6][5];
        for(int i = 0; i < animationPlayer.length; i++)
        {
            for(int j = 0; j < animationPlayer[i].length; j++)
            {
                animationPlayer[i][j] = image.getSubimage(j*32,i*32,32,32);
            }
        }
    }

    public void loadLvData(int [][]lvlData)
    {
        this.lvlData = lvlData;
    }

    public void resetDirBooleans()
    {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
