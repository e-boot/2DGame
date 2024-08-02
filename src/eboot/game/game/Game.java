package eboot.game.game;

import eboot.game.keyboard.Keyboard;
import eboot.game.platform.PlatformFactory;
import eboot.game.platform.Platform;
import eboot.game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    /* Properties */
    private Timer timer;
    private Player player;
    private Platform[] platforms;
    public boolean gameOver;


    /* Game Constructor */
    public Game() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.CYAN);

        player = new Player(100, 500);
        spawnPlatforms();

        timer = new Timer(20, this);
        timer.start();

        setFocusable(true);
        Keyboard keyboard = new Keyboard(this);
        addKeyListener(keyboard);
    }


    private void spawnPlatforms() {
        platforms = new Platform[]{
                PlatformFactory.createPlatform(50, 550, 100, 5),
                PlatformFactory.createPlatform(200, 450, 50, 5),
                PlatformFactory.createPlatform(300, 350, 50, 5),
                PlatformFactory.createPlatform(400, 250, 100, 5),
        };

    }


    @Override
    protected void paintComponent(Graphics g){
            super.paintComponent(g);
            if(!gameOver){
                player.draw(g);
                for(Platform platform: platforms){
                    platform.draw(g);
                }
            }else {
              paintGameOverScreen(g);
            }
        }


        // helper method
        private void paintGameOverScreen(Graphics g){
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.setColor(Color.RED);
            g.drawString("Game Over",300,200);
            g.setFont(new Font("Arial",Font.PLAIN,24));
            g.drawString("Press R to retry",300,450);
            g.setFont(new Font("Arial",Font.PLAIN,24));
            g.drawString("Press Q to Quit",300,500);

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            player.update();
            checkCollisions();
            checkGameOver();
            repaint();
        }
    }


    /* helper method */
    private void checkCollisions(){
            boolean onPlatform = false;
            for (Platform platform : platforms) {
                if (player.intersects(platform)) {
                    player.landOnPlatform(platform);
                    onPlatform = true;
                }
            }
            if (!onPlatform){
                player.fall();
            }
        }


    /* helper method */
    private void checkGameOver(){
        if(player.getY() > getHeight()){
            gameOver = true;
        }
    }


    public void restart() {
        player = new Player(100,500);
        gameOver = false;
        requestFocusInWindow();
        Keyboard keyboard = new Keyboard(this);
        addKeyListener(keyboard);
    }

    public Player getPlayer() {
        return player;
    }
}
