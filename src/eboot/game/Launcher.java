package eboot.game;

import eboot.game.game.Game;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {

        JFrame frame = new JFrame(" 2D Game");
        Game game = new Game();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
