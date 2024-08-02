package eboot.game.keyboard;

import eboot.game.game.Game;
import eboot.game.player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    Game game;
    Player player ;


    public Keyboard(Game game){
        this.game = game;
        player = game.getPlayer();
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(!game.gameOver) {
            if (key == KeyEvent.VK_A) {
                player.setDx(-5);
            }
            if (key == KeyEvent.VK_D) {
                player.setDx(5);
            }
            if (key == KeyEvent.VK_W) {
                player.jump();
            }
        }else {
            if(key == KeyEvent.VK_R){
                game.restart();
            }
            if (key == KeyEvent.VK_Q){
                System.exit(0);
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(!game.gameOver) {
            if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
                player.setDx(0);
            }
        }
    }
}
