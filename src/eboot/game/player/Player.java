package eboot.game.player;

import eboot.game.platform.Platform;

import java.awt.*;

public class Player {

    /** Properties */
    private int x, y, width, height, dx, dy;
    private boolean jumping, falling;
    private int gravity = 2;
    private int jumpStrength =  -15;

/* Player Constructor */
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        width = 50;
        height = 50;
        this.dx = 0;
        this.dy = 0;
        this.jumping = false;
        this.falling = true;
    }


    /* updates player's position */
    public void update(){
        x += dx;
        y += dy;

        if(jumping){
            dy -= gravity;
            if(dy <=0){
                jumping = false;
                falling = true;
            }
        } else if(falling){
            dy += gravity;
        }
    }


    /* Draws player   */
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x,y,width,height);
    }


    public void jump(){
        if(!jumping && !falling){
            jumping = true;
            dy = jumpStrength;
        }
    }

    /* lands the player to a platform  */
    public void landOnPlatform(Platform platform){
        y = platform.getY() - height;
        dy = 0;
        falling = false;
    }


    /** @return rectangle object with player bounds */
    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }


    public boolean intersects(Platform platform){
        Rectangle playerBounds = getBounds();
        Rectangle platformBounds = platform.getBounds();
        boolean intersects = playerBounds.intersects((platformBounds));
        if(intersects){
            if(x < playerBounds.x){
                x = playerBounds.x - width;
                dx = 0;
            } else if (x + width > playerBounds.x + playerBounds.width) {
                x = playerBounds.x + playerBounds.width;
                dx = 0;
            }
        }
        return intersects;
    }

    public void fall() {
        falling = true;
    }


    public void setDx(int dx){
        this.dx = dx;
    }


    public int getY() {
        return y;
    }

}
