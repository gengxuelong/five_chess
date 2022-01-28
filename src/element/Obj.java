package element;

import pages.gameframe.PlayFrame;

import java.awt.*;

public abstract class Obj {

    protected PlayFrame playFrame;

    public abstract void paintSelf(Graphics g);

    public PlayFrame getFrame() {
        return playFrame;
    }

    public void setFrame(PlayFrame playFrame) {
        this.playFrame = playFrame;
    }
}
