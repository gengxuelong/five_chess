package tools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tools {
    static public String path = "./resource/images/pages/";
    static public String path2 = "./resource/sounds/";
    static public String path3 = "./resource/images/element/";

    static public Image beijing = Toolkit.getDefaultToolkit().createImage(path + "beijing.jpeg");
    static public Image beijing2 = Toolkit.getDefaultToolkit().createImage(path + "beijing2.jpeg");
    static public Image shezhi = Toolkit.getDefaultToolkit().createImage(path + "shezhi.jpeg");

    static public Image qipan0 = Toolkit.getDefaultToolkit().createImage(path3 + "qipan0.jpg");
    static public Image qipan1 = Toolkit.getDefaultToolkit().createImage(path3 + "qipan1.jpg");
    static public Image playbg = Toolkit.getDefaultToolkit().createImage(path + "playbg.jpeg");

    static public Image qizi0 = Toolkit.getDefaultToolkit().createImage(path3 + "c0.png");
    static public Image qizi1 = Toolkit.getDefaultToolkit().createImage(path3 + "c1.png");

    static public ImageIcon start = new ImageIcon(Toolkit.getDefaultToolkit().createImage(path + "start.png"));
    static public ImageIcon close = new ImageIcon(Toolkit.getDefaultToolkit().createImage(path + "close.png"));

    static public ImageIcon makeIconInSize(int width, int height, Image image) {
        BufferedImage buffer = new BufferedImage(width, height, 1);
        ImageIcon icon_temp = new ImageIcon(image);
        Graphics gImage = buffer.createGraphics();
        gImage.drawImage(icon_temp.getImage(), 0, 0, width, height, null);
        return new ImageIcon(buffer);
    }

    static public Point getGridPoint(int x, int y) {

        int xGrid = x / 37;
        int yGrid = y / 37;
        int yu_x = x - 37 * xGrid;
        int yu_y = y - 37 * yGrid;
        int resX = 0;
        int resY = 0;
        if (yu_x < 18) {
            resX = xGrid;
        } else {
            resX = xGrid + 1;
        }
        if (yu_y < 18) {
            resY = yGrid;
        } else {
            resY = yGrid + 1;
        }
        Point res = new Point(resX, resY);
        return res;
    }
}
