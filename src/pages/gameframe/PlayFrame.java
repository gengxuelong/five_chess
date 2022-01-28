package pages.gameframe;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.Timer;

import element.Obj;
import element.QiPan;
import tools.Judge;
import tools.Tools;

public abstract class PlayFrame extends JFrame {


    {

        //frame
        this.setSize(1200, 660);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);



        //閫愬抚缁樺埗
        new Timer(100, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.repaint();
            }
        }).start();
    }
    public PlayFrame that = this;
     public List<Obj> objs ;
    public QiPan qiPan ;
    public  int[][] ints;
    public  int validSum ;
    public  boolean iswin;
    public Judge judge;




     public void init(){
         objs = new LinkedList<>();
         qiPan = new QiPan();
         ints = new int[15][15];
         validSum = 0;
         iswin = false;
         judge = new Judge(this);
         for(int i = 0;i<15;i++){
             for(int j=0;j<15;j++){
                 ints[i][j] = 0;
             }
         }
     }
    /**
     * 妫嬬洏鐨勬牸瀛恠ize涓�37
     * @param g
     */
    private Image cache = null;
    public void paint(Graphics g){

        if(cache == null){
            cache = this.createImage(this.getWidth(),this.getHeight());
        }
        Graphics gImage = cache.getGraphics();

        if(qiPan!=null){
            gImage.translate(340,80);
            gImage.drawImage(Tools.playbg,-340,-80,this.getWidth(),this.getHeight(),null);
            qiPan.paintSelf(gImage);
            for(Obj o : objs){
                o.paintSelf(gImage);
            }
        }

        g.drawImage(cache,0,0,this.getWidth(),this.getHeight(),null);
    }


}
