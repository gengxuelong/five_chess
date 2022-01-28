package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player {

    private  Clip bgm = null;
    public  Player(String soundPath){
        try {
            bgm = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundPath));
            bgm.open(ais);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    public void startBgm(){
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopBgm(){
        bgm.stop();
    }

    public static void main(String[] args) {
        Player player = new Player("./resource/sounds/bgm.wav");
        player.startBgm();
    }
}
