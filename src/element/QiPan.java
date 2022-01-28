package element;

import tools.Tools;

import java.awt.*;

public class QiPan {
    private  Image image = Tools.qipan1;
    private Point point = new Point(-40,-40);
    private int size = 600;
    public void paintSelf(Graphics g){
        g.drawImage(image,point.x,point.y,size,size,null);
    }

    public QiPan(){}

    public QiPan(Image image, Point point) {
        this.image = image;
        this.point = point;
    }
}
