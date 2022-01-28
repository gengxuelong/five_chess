package shuangRenTongJi;


import element.QiZi;
import element.Tip;
import pages.gameframe.PlayFrame;
import tools.Tools;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayFrame_1 extends PlayFrame {
    PlayFrame_1 that = this;
    {
        init();
    }
    public PlayFrame_1() {
        init();

        //keyListener
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    System.out.println("按键了");
                    if(iswin){
                        init();
                    }
                }
            }
        });


        //mouseListener
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX()-340;
                int y = e.getY()-80;
                Point grid = Tools.getGridPoint(y,x);
                if(x>0&&y>0&&x<520&&y<520&&!iswin){
                    if(ints[grid.x][grid.y] == 0){
                        if(validSum%2==0){
                            ints[grid.x][grid.y] = 1;
                           QiZi qizi = new QiZi(1,grid,that);
                            objs.add(qizi);
                        }else{
                            ints[grid.x][grid.y] = 2;
                            QiZi qizi = new QiZi(2,grid,that);
                            objs.add(qizi);
                        }
                        System.out.println(x+";"+y);
                        repaint();
                        validSum++;
                        judge.isWin(grid);
                    }else{
                        Tip tip = new Tip("此处已经有棋子",that);
                        objs.add(tip);
                        repaint();
                    }
                } else if(iswin){}
                else{
                    Tip tip = new Tip("请点击有效区域",that);
                    objs.add(tip);
                }
            }
        });

    }

    public static void main(String[] args) {
        new PlayFrame_1();
    }
}
