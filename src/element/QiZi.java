package element;

import pages.gameframe.PlayFrame;
import tools.Tools;

import java.awt.*;

public class QiZi extends Obj {


    private Image image = Tools.qizi0;
    private Point gridPoint = new Point(1,1);//gridPoint (i,j)
    private final int size = 30;


    public void paintSelf(Graphics g){
        g.drawImage(image,gridPoint.y*37-size/2,gridPoint.x*37-size/2,size,size,null);
    }


    public QiZi(PlayFrame frame){
        this.playFrame = frame;
    }

    public QiZi(int camp, int x,int y ,PlayFrame frame) {
        if(camp == 1){
            image = Tools.qizi1;
        }else if(camp == 2){
            image = Tools.qizi0;
        }
        this.gridPoint = new Point(x,y);
        this.playFrame = frame;
    }

    public QiZi( int x,int y ,PlayFrame frame) {
        this.gridPoint = new Point(x,y);
        this.playFrame = frame;
    }

    public QiZi( int camp ,Point gridPoint, PlayFrame frame) {
        if(camp == 1){
            image = Tools.qizi1;
        }else if(camp == 2){
            image = Tools.qizi0;
        }
        this.gridPoint = gridPoint;
        this.playFrame = frame;
    }
}
