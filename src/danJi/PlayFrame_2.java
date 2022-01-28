package danJi;

import element.QiZi;
import element.Tip;
import pages.gameframe.PlayFrame;
import tools.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayFrame_2 extends PlayFrame {
    AI ai = new AI(this);
    PlayFrame_2 that = this;


    public void init(){
        super.init();
        this.ai = new AI(this);
    }

    public void myPlay(Point grid){
        ints[grid.x][grid.y] = 1;
        QiZi qizi = new QiZi(1, grid, that);
        objs.add(qizi);
        validSum++;
        judge.isWin(grid);
        that.repaint();
    }

    public PlayFrame_2() {
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
                int x = e.getX() - 340;
                int y = e.getY() - 80;
                Point grid = Tools.getGridPoint(y, x);
                if (x > 0 && y > 0 && x < 520 && y < 520 && !iswin) {
                    if (ints[grid.x][grid.y] == 0) {
                        that.myPlay(grid);
                        ai.play();
                    } else {
                        Tip tip = new Tip("此处已经有棋子", that);
                        objs.add(tip);
                        repaint();
                    }
                } else if (iswin) {
                } else {
                    Tip tip = new Tip("请点击有效区域", that);
                    objs.add(tip);
                    repaint();
                }
                new Timer(100, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        that.repaint();
                    }
                }).start();
            }
        });

    }


    /**
     * 让两个机器人玩耍
     */
    public void play() {//又是自己的失误，造成程序的运行逻辑不正确，还想怨系统，判断用的是ints[x][y],赋值用的却是ints[y][x]

        AI ai1 = new AI(this,1);
        AI ai2 = new AI(this);
        if(!iswin){
            ai1.play();
            ai2.play();
        }

}

    public static void main(String[] args) {
        new PlayFrame_2();
    }

}
