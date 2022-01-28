package element;

import pages.gameframe.PlayFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Tip extends Obj {

    int x = 30;
    int y = 30;
    String kind;
    String string = "";
    Tip that = this;
    Timer timer = new Timer(1000, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playFrame.objs.remove(that);
            playFrame.repaint();
        }
    });

    public void paintSelf(Graphics g){
        g.setColor(Color.BLUE);
        g.setFont(new Font(null,Font.BOLD,40));
        g.drawString(string,x,y);
    }
    public Tip(String string, PlayFrame frame){
        this.string = string;
        this.playFrame = frame;
        timer.start();
    }
    public Tip(String string, PlayFrame frame,String kind){
        this.string = string;
        this.playFrame = frame;
        playFrame.repaint();
    }
}
