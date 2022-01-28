package tools;

import element.Tip;
import pages.gameframe.PlayFrame;

import java.awt.*;

public class Judge {

    PlayFrame frame;
    int[][] ints ;
    boolean win = false;

    public Judge(PlayFrame frame){
        this.frame = frame;
        this.ints = frame.ints;
    }
     public void isWin(Point gridPoint) {
        int I = gridPoint.x;
        int J = gridPoint.y;
        boolean tempwin;
        //i方向
        for (int i = 0; i < 5; i++) {//忘记在新的for循环中更新数据了。居然还想着是Java自己的问题
             tempwin = true;
             for(int j= 0 ; j < 4; j++){
                 if(I-i+j>=0&&I-i+j+1<=14){
                     if(ints[I-i+j][J]!=ints[I-i+j+1][J]){
                         tempwin = false;
                     }
                 }else{
                     tempwin = false;
                     break;
                 }
             }
             if(tempwin){
                 win = true;
                 break;
             }
        }
        if(!win){
            //j方向
            for (int i = 0; i < 5; i++) {//忘记在新的for循环中更新数据了。居然还想着是Java自己的问题
                tempwin = true;
                for(int j= 0 ; j < 4; j++){
                    if(J-i+j>=0&&J-i+j+1<=14){
                        if(ints[I][J-i+j]!=ints[I][J-i+j+1]){
                            tempwin = false;
                        }
                    }else{
                        tempwin = false;
                        break;
                    }

                }
                if(tempwin){
                    win = true;
                    break;
                }
            }
        }
         if(!win){
             //右下方向
             for (int i = 0; i < 5; i++) {//忘记在新的for循环中更新数据了。居然还想着是Java自己的问题
                 tempwin = true;
                 for(int j= 0 ; j < 4; j++){
                     if(I-i+j>=0&&I-i+j+1<=14&&J-i+j>=0&&J-i+j+1<=14){
                         if(ints[I-i+j][J-i+j]!=ints[I-i+j+1][J-i+j+1]){
                             tempwin = false;
                         }
                     }else{
                         tempwin = false;
                         break;
                     }

                 }
                 if(tempwin){
                     win = true;
                     break;
                 }
             }
         }
         if(!win){
             //左下方向
             for (int i = 0; i < 5; i++) {//忘记在新的for循环中更新数据了。居然还想着是Java自己的问题
                 tempwin = true;
                 for(int j= 0 ; j < 4; j++){
                     if(I-i+j>=0&&I-i+j+1<=14&&J+i-j-1>=0&&J+i-j<=14){
                         if(ints[I-i+j][J+i-j]!=ints[I-i+j+1][J+i-j-1]){
                             tempwin = false;
                         }
                     }else{
                         tempwin = false;
                         break;
                     }

                 }
                 if(tempwin){
                     win = true;
                     break;
                 }
             }
         }
        if(win){
            System.out.println("胜利");
            this.frame.iswin = true;
            if(frame.validSum%2 == 1){
                Tip tip = new Tip("黑方获胜 ， 按空格重新开始",this.frame,"forever");
                frame.objs.add(tip);
            }else{
                Tip tip = new Tip("白方获胜 ， 按空格重新开始",this.frame,"forever");
                frame.objs.add(tip);
            }
        }
    }

    private class MyThread extends Thread{//不过线程少了几个确实还是问题。。问题解决，是我的打印语句写错位置了。反正就是，你好菜

    }

}
