package lianWan;

import element.QiZi;
import element.Tip;
import pages.gameframe.PlayFrame;
import tools.Tools;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PlayFrame_net extends PlayFrame {


    {
        init();
        //鼠标监听器
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() - 340;
                int y = e.getY() - 80;
                if (x > 0 && y > 0 && x < 520 && y < 520 && !iswin) {
                    if (myCamp == 1&&net_agency.remoteIP!=null&&net_agency.start) {
                        if (validSum % 2 == 0) {
                            Point grid = Tools.getGridPoint(y, x);
                            if (ints[grid.x][grid.y] == 0) {
                                play(grid, 1);
                                net_agency.sendMsg("A_" + grid.x + "_" + grid.y);
                            }
                        } else {
                            Tip tip = new Tip("不到您的回合", that);
                            that.objs.add(tip);
                        }
                    } else if (myCamp == 2) {
                        if (validSum % 2 == 1) {
                            Point grid = Tools.getGridPoint(y, x);
                            if (ints[grid.x][grid.y] == 0) {
                                play(grid, 2);
                                net_agency.sendMsg("B_" + grid.x + "_" + grid.y);
                            }
                        } else {
                            Tip tip = new Tip("不到您的回合", that);
                            that.objs.add(tip);
                        }
                    }else if(!net_agency.start&&net_agency.remoteIP==null){
                        Tip tip = new Tip("对方玩家尚未连接",that);
                        that.objs.add(tip);
                    }else if(!net_agency.start){
                        Tip tip = new Tip("抱歉，您进入了无存在的房间",that);
                        that.objs.add(tip);
                    }
                }else if(!iswin){
                    Tip tip = new Tip("点击位置无效",that);
                    that.objs.add(tip);

                }

            }
        });

        //keyListener
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    System.out.println("按键了");
                    if(iswin){
                        init();
                        net_agency.sendMsg("C_init");
                    }
                }
            }
        });


    }


    int myCamp ;
    udp_agency net_agency ;




    public void play(Point grid, int camp){
        ints[grid.x][grid.y] = camp;
        QiZi qizi = new QiZi(camp, grid, that);
        objs.add(qizi);
        validSum++;
        judge.isWin(grid);
        if(iswin){
            net_agency.sendMsg("C_iswin");
        }
        that.repaint();
    }


    public PlayFrame_net(int myCamp,int myPort,int remotePort ,String remoteIP){
        this.myCamp = myCamp;
        net_agency = new udp_agency(this,myPort,remotePort,remoteIP);
        init();
        String ip = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        net_agency.sendMsg("C_start_"+ip+"_"+myPort);
    }
    public PlayFrame_net(int myCamp,int myPort){
        this.myCamp = myCamp;
        net_agency = new udp_agency(this,myPort);

    }
}
