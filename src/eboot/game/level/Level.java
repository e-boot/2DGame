package eboot.game.level;

import eboot.game.platform.Platform;
import java.awt.*;

public class Level {
    private Platform[] platforms;


    public Level(int levelNumber){

        switch (levelNumber){
            case 1:
                createPlatformsLevel1();
        break;

            default:
                throw  new IllegalArgumentException("Unknown level" + levelNumber);
        }
    }

    private void createPlatformsLevel1(){
        platforms = new Platform[]{
                new Platform(50, 550, 100, 10),
                new Platform(170, 480, 50, 10),
                new Platform(270, 400, 100, 10),
                new Platform(420, 350, 20, 10),
                new Platform(500, 350, 15, 10),
                new Platform(580, 350, 15, 10),
        };
    }


    public void draw(Graphics g){
        for(Platform platform : platforms){
            platform.draw(g);
        }
    }

    public Platform[] getPlatforms() {
        return platforms;
    }
}
