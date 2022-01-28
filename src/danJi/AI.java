package danJi;

import element.QiZi;

import java.awt.*;

public class AI {
    int camp = 2;
    private PlayFrame_2 frame;
    private int[][] weights;
    {
        weights = new int[15][15];
        for(int i = 0;i<15;i++){
            for(int j = 0;j < 15;j++){
                weights[i][j] = 0;
            }
        }
    }
    public  AI(PlayFrame_2 frame){
        this.frame = frame;
    }

    public  AI(PlayFrame_2 frame,int camp){
        this.frame = frame;
        this.camp = camp;
    }


    public Point calculate_random(){
        System.out.println("随机计算开始");
        int i = (int)(Math.random()*15);
        int j = (int)(Math.random()*15);
        int count = 0;
        while(true){
            count++;
             i = (int)(Math.random()*15);
             j = (int)(Math.random()*15);
            if(count == 100){
                break;
            }
            if(frame.ints[i][j] == 0)
                return new Point(i,j);
            System.out.println("二次矫正");
        }
        return new Point(0,0);
    }


    public Point calculate_strategy(){

        Point res = null;
        for(int i = 0;i < 15; i++){
            for( int j = 0; j< 15 ; j++){
                if(frame.ints[i][j] == 0){
                   new MyThread(frame.ints,i,j).start();
                }else{
                    weights[i][j] = -10;
                }
            }
        }
        System.out.println();
        res = getTheMax();
        return res;

    }

    public void play(){
        Point grid = calculate_strategy();
        frame.ints[grid.x][grid.y] = camp;
        QiZi qizi = new QiZi(camp,grid,frame);
        frame.objs.add(qizi);
        frame.repaint();
        frame.validSum =  frame.validSum+1;
        for(int i = 0;i<15;i++){
            for(int j = 0;j<15;j++){
                System.out.print(weights[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
        for(int i = 0;i<15;i++){
            for(int j = 0;j<15;j++){
                System.out.print(frame.ints[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();System.out.println();System.out.println();System.out.println();
        frame.judge.isWin(grid);
        frame.repaint();
    }

    public Point getTheMax(){
        Point max = new Point(-1,-1);
        int maxInt = 0;
        for(int i = 0;i < 15; i++){
            for( int j = 0; j< 15 ; j++){
               if(weights[i][j] > maxInt){
                   maxInt = weights[i][j];
                   max.x = i;
                   max.y = j;
               }
            }
        }
        if(maxInt ==0){
            max = calculate_random();
        }
        return max;
    }

    private class MyThread extends Thread{
        int[][] ints ;
        int i;
        int j;
        public MyThread(int[][] ints,int i,int j){
            this.ints = ints;
            this.i = i;
            this.j =j;
        }
        public void run(){
            calculate(frame.ints,i,j);
        }
    }



    public void calculate(int[][] ints,int i ,int j){

                   /*
        判五子，当步可绝杀
         */
        {
            //j方向
            if (j < 11 && frame.ints[i][j+1] == 2 && frame.ints[i][j+2]== 2 && frame.ints[i][j+3]== 2 && frame.ints[i][j+4]== 2) {
                weights[i][j] = 2430;
                return;
            }
            if (j >= 4 && frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2 && frame.ints[i][j - 3] == 2&& frame.ints[i][j - 4] == 2) {
                weights[i][j] = 2420;
                return;
            }
            if (j >= 1 && j < 12 && (frame.ints[i][j - 1] == 2 && frame.ints[j][j+1] == 2&& frame.ints[j][j+2]== 2&& frame.ints[j][j+3]== 2)) {
                weights[i][j] = 2410;
                return;
            }
            if (j >= 2 && j < 13 && (frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2&& frame.ints[j][j+1] == 2&& frame.ints[j][j+2]== 2)) {
                weights[i][j] = 2410;
                return;
            }
            if (j >= 3 && j < 14 && (frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2&& frame.ints[i][j - 3] == 2&& frame.ints[j][j+1] == 2)) {
                weights[i][j] = 2410;
                return;
            }



            if (j < 11 && frame.ints[j][j+1] == 1 && frame.ints[j][j+2]== 1 && frame.ints[j][j+3]== 1 && frame.ints[j][j+4]== 1) {
                weights[i][j] = 1430;
                return;
            }
            if (j >= 4 && frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1 && frame.ints[i][j - 3] == 1&& frame.ints[i][j - 4] == 1) {
                weights[i][j] = 1420;
                return;
            }
            if (j >= 1 && j < 12 && (frame.ints[i][j - 1] == 1 && frame.ints[j][j+1] == 1&& frame.ints[j][j+2]== 1&& frame.ints[j][j+3]== 1)) {
                weights[i][j] = 1410;
                return;
            }
            if (j >= 2 && j < 13 && (frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1&& frame.ints[j][j+1] == 1&& frame.ints[j][j+2]== 1)) {
                weights[i][j] = 1410;
                return;
            }
            if (j >= 3 && j < 14 && (frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1&& frame.ints[i][j - 3] == 1&& frame.ints[j][j+1] == 1)) {
                weights[i][j] = 1410;
                return;
            }

            //i方向
            if (i < 11 && frame.ints[i+1][j] == 2 && frame.ints[i+2][j] == 2 && frame.ints[i+3][j] == 2 && frame.ints[i+4][j] == 2) {
                weights[i][j] = 2430;
                return;
            }
            if (i >= 4 && frame.ints[i-1][j] == 2 && frame.ints[i-2][j] == 2 && frame.ints[i-3][j] == 2&& frame.ints[i-4][j] == 2) {
                weights[i][j] = 2420;
                return;
            }
            if (i >= 1 && i < 12 && (frame.ints[i-1][j] == 2 && frame.ints[i+1][j] == 2&& frame.ints[i+2][j] == 2&& frame.ints[i+3][j] == 2)) {
                weights[i][j] = 2410;
                return;
            }
            if (i >= 2 && i < 13 && (frame.ints[i-1][j] == 2 && frame.ints[i-2][j] == 2&& frame.ints[i+1][j] == 2&& frame.ints[i+2][j] == 2)) {
                weights[i][j] = 2410;
                return;
            }
            if (i >= 3 && i < 14 && (frame.ints[i-1][j] == 2 && frame.ints[i-2][j] == 2&& frame.ints[i-3][j] == 2&& frame.ints[i+1][j] == 2)) {
                weights[i][j] = 2410;
                return;
            }



            if (i < 11 && frame.ints[i+1][j] == 1 && frame.ints[i+2][j] == 1 && frame.ints[i+3][j] == 1 && frame.ints[i+4][j] == 1) {
                weights[i][j] = 1430;
                return;
            }
            if (i >= 4 && frame.ints[i-1][j] == 1 && frame.ints[i-2][j] == 1 && frame.ints[i-3][j] == 1&& frame.ints[i-4][j] == 1) {
                weights[i][j] = 1420;
                return;
            }
            if (i >= 1 && i < 12 && (frame.ints[i-1][j] == 1 && frame.ints[i+1][j] == 1&& frame.ints[i+2][j] == 1&& frame.ints[i+3][j] == 1)) {
                weights[i][j] = 1410;
                return;
            }
            if (i >= 2 && i < 13 && (frame.ints[i-1][j] == 1 && frame.ints[i-2][j] == 1&& frame.ints[i+1][j] == 1&& frame.ints[i+2][j] == 1)) {
                weights[i][j] = 1410;
                return;
            }
            if (i >= 3 && i < 14 && (frame.ints[i-1][j] == 1 && frame.ints[i-2][j] == 1&& frame.ints[i-3][j] == 1&& frame.ints[i+1][j] == 1)) {
                weights[i][j] = 1410;
                return;
            }

            //向下方向
            if (i < 11 &&j < 11 && frame.ints[i+1][j + 1] == 2 && frame.ints[i+2][j + 2] == 2 && frame.ints[i+3][j + 3] == 2 && frame.ints[i+4][j + 4] == 2) {
                weights[i][j] = 2430;
                return;
            }
            if (i >= 4 && j >= 4 && frame.ints[i-1][j - 1] == 2 && frame.ints[i-2][j - 2] == 2 && frame.ints[i-3][j - 3] == 2&& frame.ints[i-4][j - 4] == 2) {
                weights[i][j] = 2420;
                return;
            }
            if (i >= 1 && i < 12 && j >= 1 && j < 12 && (frame.ints[i-1][j - 1] == 2 && frame.ints[i+1][j + 1] == 2&& frame.ints[i+2][j + 2] == 2&& frame.ints[i+3][j + 3] == 2)) {
                weights[i][j] = 2410;
                return;
            }
            if (i >= 2 && i < 13 &&j >= 2 && j < 13 && (frame.ints[i-1][j - 1] == 2 && frame.ints[i-2][j - 2] == 2&& frame.ints[i+1][j + 1] == 2&& frame.ints[i+2][j + 2] == 2)) {
                weights[i][j] = 2410;
                return;
            }
            if (i >= 3 && i < 14 && j >= 3 && j < 14 && (frame.ints[i-1][j - 1] == 2 && frame.ints[i-2][j - 2] == 2&& frame.ints[i-3][j - 3] == 2&& frame.ints[i+1][j + 1] == 2)) {
                weights[i][j] = 2410;
                return;
            }
            ////
            if (i < 11 &&j < 11 && frame.ints[i+1][j + 1] == 1 && frame.ints[i+2][j + 2] == 1 && frame.ints[i+3][j + 3] == 1 && frame.ints[i+4][j + 4] == 1) {
                weights[i][j] = 1430;
                return;
            }
            if (i >= 4 && j >= 4 && frame.ints[i-1][j - 1] == 1 && frame.ints[i-2][j - 2] == 1 && frame.ints[i-3][j - 3] == 1&& frame.ints[i-4][j - 4] == 1) {
                weights[i][j] = 1420;
                return;
            }
            if (i >= 1 && i < 12 &&j >= 1 && j < 12 && (frame.ints[i-1][j - 1] == 1 && frame.ints[i+1][j + 1] == 1&& frame.ints[i+2][j + 2] == 1&& frame.ints[i+3][j + 3] == 1)) {
                weights[i][j] = 1410;
                return;
            }
            if (i >= 2 && i < 13 &&j >= 2 && j < 13 && (frame.ints[i-1][j - 1] == 1 && frame.ints[i-2][j - 2] == 1&& frame.ints[i+1][j + 1] == 1&& frame.ints[i+2][j + 2] == 1)) {
                weights[i][j] = 1410;
                return;
            }
            if (i >= 3 && i < 14 &&j >= 3 && j < 14 && (frame.ints[i-1][j - 1] == 1 && frame.ints[i-2][j - 2] == 1&& frame.ints[i-3][j - 3] == 1&& frame.ints[i+1][j + 1] == 1)) {
                weights[i][j] = 1410;
                return;
            }



            //向上方向
            if (i >=4 &&j < 11 && frame.ints[i-1][j + 1] == 2 && frame.ints[i-2][j + 2] == 2 && frame.ints[i-3][j + 3] == 2 && frame.ints[i-4][j + 4] == 2) {
                weights[i][j] = 2430;
                return;
            }
            if (i <11 &&j >= 4 && frame.ints[i+1][j - 1] == 2 && frame.ints[i+2][j - 2] == 2 && frame.ints[i+3][j - 3] == 2&& frame.ints[i+4][j - 4] == 2) {
                weights[i][j] = 2420;
                return;
            }
            if (i >= 3 && i < 14 &&j >= 1 && j < 12 && (frame.ints[i+1][j - 1] == 2 && frame.ints[i-1][j + 1] == 2&& frame.ints[i-2][j + 2] == 2&& frame.ints[i-3][j + 3] == 2)) {
                weights[i][j] = 2410;
                return;
            }
            if (i >= 2 && i < 13 &&j >= 2 && j < 13 && (frame.ints[i+1][j - 1] == 2 && frame.ints[i+2][j - 2] == 2&& frame.ints[i-1][j + 1] == 2&& frame.ints[i-2][j + 2] == 2)) {
                weights[i][j] = 2410;
                return;
            }
            if (i >= 1 && i < 12 && j >= 3 && j < 14 && (frame.ints[i+1][j - 1] == 2 && frame.ints[i+2][j - 2] == 2&& frame.ints[i+3][j - 3] == 2&& frame.ints[i-1][j + 1] == 2)) {
                weights[i][j] = 2410;
                return;
            }



            if (i >=4 &&j < 11 && frame.ints[i-1][j + 1] == 1 && frame.ints[i-2][j + 2] == 1 && frame.ints[i-3][j + 3] == 1 && frame.ints[i-4][j + 4] == 1) {
                weights[i][j] = 1430;
                return;
            }
            if (i <11 &&j >= 4 && frame.ints[i+1][j - 1] == 1 && frame.ints[i+2][j - 2] == 1 && frame.ints[i+3][j - 3] == 1&& frame.ints[i+4][j - 4] == 1) {
                weights[i][j] = 1420;
                return;
            }
            if (i >= 3 && i < 14 &&j >= 1 && j < 12 && (frame.ints[i+1][j - 1] == 1 && frame.ints[i-1][j + 1] == 1&& frame.ints[i-2][j + 2] == 1&& frame.ints[i-3][j + 3] == 1)) {
                weights[i][j] = 1410;
                return;
            }
            if (i >= 2 && i < 13 &&j >= 2 && j < 13 && (frame.ints[i+1][j - 1] == 1 && frame.ints[i+2][j - 2] == 1&& frame.ints[i-1][j + 1] == 1&& frame.ints[i-2][j + 2] == 1)) {
                weights[i][j] = 1410;
                return;
            }
            if (i >= 1 && i < 12 && j >= 3 && j < 14 && (frame.ints[i+1][j - 1] == 1 && frame.ints[i+2][j - 2] == 1&& frame.ints[i+3][j - 3] == 1&& frame.ints[i-1][j + 1] == 1)) {
                weights[i][j] = 1410;
                return;
            }


        }





        /*
        判断四子
         */
        {
            //j方向,带气泡,下步可绝杀
            if(j>=1&&j<11&&(frame.ints[i][j - 1] == 0 && frame.ints[j][j+1] == 2 && frame.ints[j][j+2]== 2 && frame.ints[j][j+3]== 2 && frame.ints[j][j+4]== 0)){
                weights[i][j] = 1100;
            }
            if (j >= 2 && j < 12 && ( frame.ints[i][j - 2] == 0&&frame.ints[i][j - 1] == 2 && frame.ints[j][j+1] == 2&& frame.ints[j][j+2]== 2&& frame.ints[j][j+3]== 0)) {
                weights[i][j] = 1100;
                return;
            }
            if (j >= 3 && j < 13 && (frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2&& frame.ints[i][j - 3] == 0&& frame.ints[j][j+1] == 2&& frame.ints[j][j+2]== 0)) {
                weights[i][j] = 1100;
                return;
            }
            if (j >= 4 && j < 14 && (frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2&& frame.ints[i][j - 3] == 2 &&frame.ints[i][j - 4] == 0&& frame.ints[j][j+1] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            ////对敌
            if(j>=1&&j<11&&(frame.ints[i][j - 1] == 0 && frame.ints[j][j+1] == 1 && frame.ints[j][j+2]== 1 && frame.ints[j][j+3]== 1 && frame.ints[j][j+4]== 0)){
                weights[i][j] = 1000;
            }
            if (j >= 2 && j < 12 && ( frame.ints[i][j - 2] == 0&&frame.ints[i][j - 1] == 1 && frame.ints[j][j+1] == 1&& frame.ints[j][j+2]== 1&& frame.ints[j][j+3]== 0)) {
                weights[i][j] = 1000;
                return;
            }
            if (j >= 3 && j < 13 && (frame.ints[i][j - 3] == 0&&frame.ints[i][j - 2] == 1 && frame.ints[i][j - 1] == 1&& frame.ints[j][j+1] == 1&& frame.ints[j][j+2]== 0)) {
                weights[i][j] = 1000;
                return;
            }
            if (j >= 4 && j < 14 && ( frame.ints[i][j - 4] == 0&&frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1&& frame.ints[i][j - 3] == 1 && frame.ints[j][j+1] == 0)) {
                weights[i][j] = 1000;
                return;
            }


            //i方向带气泡
            if (i>=1&&i < 11  && frame.ints[i-1][j] == 0&&frame.ints[i+1][j] == 2 && frame.ints[i+2][j] == 2 && frame.ints[i+3][j] == 2 && frame.ints[i+4][j] ==0) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 2 && i < 12 && (frame.ints[i-2][j] == 0 &&frame.ints[i-1][j] == 2 && frame.ints[i+1][j] == 2&& frame.ints[i+2][j] == 2&& frame.ints[i+3][j] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 3 && i < 13 && (frame.ints[i-3][j] == 0 &&frame.ints[i-1][j] == 2 && frame.ints[i-2][j] == 2&& frame.ints[i+1][j] == 2&& frame.ints[i+2][j] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 4 && i < 14 && (frame.ints[i-4][j] ==0 &&frame.ints[i-1][j] == 2 && frame.ints[i-2][j] == 2&& frame.ints[i-3][j] == 2&& frame.ints[i+1][j] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            ////对敌
            if (i>=1&&i < 11  && frame.ints[i-1][j] == 0&&frame.ints[i+1][j] == 1 && frame.ints[i+2][j] == 1 && frame.ints[i+3][j] == 1 && frame.ints[i+4][j] ==0) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 2 && i < 12 && (frame.ints[i-2][j] == 0 &&frame.ints[i-1][j] == 1 && frame.ints[i+1][j] == 1&& frame.ints[i+2][j] == 1&& frame.ints[i+3][j] == 0)) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 3 && i < 13 && (frame.ints[i-3][j] == 0 &&frame.ints[i-1][j] == 1 && frame.ints[i-2][j] == 1&& frame.ints[i+1][j] == 1&& frame.ints[i+2][j] == 0)) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 4 && i < 14 && (frame.ints[i-4][j] ==0 &&frame.ints[i-1][j] == 1 && frame.ints[i-2][j] == 1&& frame.ints[i-3][j] == 1&& frame.ints[i+1][j] == 0)) {
                weights[i][j] = 1000;
                return;
            }


            //向下方向带气泡
            if (i>=1&&j>=1&&i < 11 &&j < 11 && frame.ints[i-1][j - 1] == 0 &&frame.ints[i+1][j + 1] == 2 && frame.ints[i+2][j + 2] == 2 && frame.ints[i+3][j + 3] == 2 && frame.ints[i+4][j + 4] ==0) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 2 && i < 12 && j >= 2 && j < 12 && (frame.ints[i-2][j - 2] == 0 &&frame.ints[i-1][j - 1] == 2 && frame.ints[i+1][j + 1] == 2&& frame.ints[i+2][j + 2] == 2&& frame.ints[i+3][j + 3] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 3 && i < 13 &&j >= 3 && j < 13 && (frame.ints[i-3][j - 3] == 0 &&frame.ints[i-1][j - 1] == 2 && frame.ints[i-2][j - 2] == 2&& frame.ints[i+1][j + 1] == 2&& frame.ints[i+2][j + 2] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 4 && i < 14 && j >= 4 && j < 14 && (frame.ints[i-4][j - 4] == 0 &&frame.ints[i-1][j - 1] == 2 && frame.ints[i-2][j - 2] == 2&& frame.ints[i-3][j - 3] == 2&& frame.ints[i+1][j + 1] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            ////对敌
            if (i>=1&&j>=1&&i < 11 &&j < 11 && frame.ints[i-1][j - 1] == 0 &&frame.ints[i+1][j + 1] == 1 && frame.ints[i+2][j + 2] == 1 && frame.ints[i+3][j + 3] == 1 && frame.ints[i+4][j + 4] ==0) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 2 && i < 12 && j >= 2 && j < 12 && (frame.ints[i-2][j - 2] == 0 &&frame.ints[i-1][j - 1] == 1 && frame.ints[i+1][j + 1] == 1&& frame.ints[i+2][j + 2] == 1&& frame.ints[i+3][j + 3] == 0)) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 3 && i < 13 &&j >= 3 && j < 13 && (frame.ints[i-3][j - 3] == 0 &&frame.ints[i-1][j - 1] == 1 && frame.ints[i-2][j - 2] == 1&& frame.ints[i+1][j + 1] == 1&& frame.ints[i+2][j + 2] == 0)) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 4 && i < 14 && j >= 4 && j < 14 && (frame.ints[i-4][j - 4] == 0 &&frame.ints[i-1][j - 1] == 1 && frame.ints[i-2][j - 2] == 1&& frame.ints[i-3][j - 3] == 1&& frame.ints[i+1][j + 1] == 0)) {
                weights[i][j] = 1000;
                return;
            }

            //向上方向有气泡
            if (i >=4&& i<14&&j < 11&&j>=1 && frame.ints[i+1][j - 1] == 0 && frame.ints[i-1][j + 1] == 2 && frame.ints[i-2][j + 2] == 2 && frame.ints[i-3][j + 3] == 2 && frame.ints[i-4][j + 4] == 0) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 3 && i < 13 &&j >= 2 && j < 12 && (frame.ints[i+2][j - 2] == 2 &&frame.ints[i+1][j - 1] == 2 && frame.ints[i-1][j + 1] == 2&& frame.ints[i-2][j + 2] == 2&& frame.ints[i-3][j + 3] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 2 && i < 12 &&j >= 3 && j < 13 && (frame.ints[i+3][j - 3] == 2 &&frame.ints[i+1][j - 1] == 2 && frame.ints[i+2][j - 2] == 2&& frame.ints[i-1][j + 1] == 2&& frame.ints[i-2][j + 2] == 0)) {
                weights[i][j] = 1100;
                return;
            }
            if (i >= 1 && i < 11 && j >= 4 && j < 14 && (frame.ints[i+4][j - 4] == 2 &&frame.ints[i+1][j - 1] == 2 && frame.ints[i+2][j - 2] == 2&& frame.ints[i+3][j - 3] == 2&& frame.ints[i-1][j + 1] ==0)) {
                weights[i][j] = 1100;
                return;
            }
            ////对敌
            if (i >=4&& i<14&&j < 11&&j>=1 && frame.ints[i+1][j - 1] == 0 && frame.ints[i-1][j + 1] == 1 && frame.ints[i-2][j + 2] == 1 && frame.ints[i-3][j + 3] == 1 && frame.ints[i-4][j + 4] == 0) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 3 && i < 13 &&j >= 2 && j < 12 && (frame.ints[i+2][j - 2] == 0 &&frame.ints[i+1][j - 1] == 1&& frame.ints[i-1][j + 1] == 1&& frame.ints[i-2][j + 2] == 1&& frame.ints[i-3][j + 3] == 0)) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 2 && i < 12 &&j >= 3 && j < 13 && (frame.ints[i+3][j - 3] == 0 &&frame.ints[i+1][j - 1] == 1 && frame.ints[i+2][j - 2] == 1&& frame.ints[i-1][j + 1] == 1&& frame.ints[i-2][j + 2] == 0)) {
                weights[i][j] = 1000;
                return;
            }
            if (i >= 1 && i < 11 && j >= 4 && j < 14 && (frame.ints[i+4][j - 4] == 0 &&frame.ints[i+1][j - 1] == 1 && frame.ints[i+2][j - 2] == 1&& frame.ints[i+3][j - 3] == 1&& frame.ints[i-1][j + 1] ==0)) {
                weights[i][j] = 1000;
                return;
            }







             /*
        判断绝杀招数
         */
            if(j < 13 &&j >= 2 &&i < 13 &&i >= 2){
                boolean j_djrectjon = frame.ints[j][j+1] == 2 && frame.ints[j][j+2]== 2|| frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2;
                boolean i_djrectjon = frame.ints[i + 1][j] == 2 && frame.ints[i + 2][j] == 2||frame.ints[i - 1][j] == 2 && frame.ints[i - 2][j] == 2;
                boolean down_djrectjon = frame.ints[i + 1][j + 1] == 2 && frame.ints[i + 2][j + 2] == 2||frame.ints[i - 1][j - 1] == 2 && frame.ints[i - 2][j - 2] == 2;
                boolean up_djrectjon = frame.ints[i - 1][j + 1] == 2 && frame.ints[i - 2][j + 2] == 2||frame.ints[i + 1][j - 1] == 2 && frame.ints[i + 2][j - 2] == 2;
                if(j_djrectjon&&i_djrectjon||j_djrectjon&&up_djrectjon||j_djrectjon&&down_djrectjon||i_djrectjon&&up_djrectjon||i_djrectjon&&down_djrectjon||up_djrectjon&&down_djrectjon){
                    weights[i][j] = 3000;
                    return;
                }

                boolean j_djrectjon1 = frame.ints[j][j+1] == 1 && frame.ints[j][j+2]== 1|| frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1;
                boolean i_djrectjon1 = frame.ints[i + 1][j] == 1 && frame.ints[i + 2][j] == 1||frame.ints[i - 1][j] == 1 && frame.ints[i - 2][j] == 1;
                boolean down_djrectjon1 = frame.ints[i + 1][j + 1] == 1 && frame.ints[i + 2][j + 2] == 1||frame.ints[i - 1][j - 1] == 1 && frame.ints[i - 2][j - 2] == 1;
                boolean up_djrectjon1 = frame.ints[i - 1][j + 1] == 1 && frame.ints[i - 2][j + 2] == 1||frame.ints[i + 1][j - 1] == 1 && frame.ints[i + 2][j - 2] == 1;
                if(j_djrectjon1&&i_djrectjon1||j_djrectjon1&&up_djrectjon1||j_djrectjon1&&down_djrectjon1||i_djrectjon1&&up_djrectjon1||i_djrectjon1&&down_djrectjon1||up_djrectjon1&&down_djrectjon1){
                    weights[i][j] = 2700;
                    return;
                }

            }

            if(j < 14 &&j >= 1 &&i < 14 &&i >= 1){
                boolean j_djrectjon = frame.ints[j][j+1] == 2 && frame.ints[i][j-1] == 2;
                boolean i_djrectjon = frame.ints[i+1][j] == 2 && frame.ints[i-1][j] == 2;
                boolean up_djrectjon = frame.ints[i+1][j + 1] == 2 && frame.ints[i-1][j-1] == 2;
                boolean down_djrectjon = frame.ints[i-1][j + 1] == 2 && frame.ints[i+1][j-1] == 2;
                if(j_djrectjon&&i_djrectjon||j_djrectjon&&up_djrectjon||j_djrectjon&&down_djrectjon||i_djrectjon&&up_djrectjon||i_djrectjon&&down_djrectjon||up_djrectjon&&down_djrectjon){
                    weights[i][j] = 3000;
                    return;
                }

                boolean j_djrectjon1 = frame.ints[j][j+1] == 1 && frame.ints[i][j-1] == 1;
                boolean i_djrectjon1 = frame.ints[i+1][j] == 1 && frame.ints[i-1][j] == 1;
                boolean up_djrectjon1 = frame.ints[i+1][j + 1] == 1 && frame.ints[i-1][j-1] == 1;
                boolean down_djrectjon1 = frame.ints[i-1][j + 1] == 1 && frame.ints[i+1][j-1] == 1;
                if(j_djrectjon1&&i_djrectjon1||j_djrectjon1&&up_djrectjon1||j_djrectjon1&&down_djrectjon1||i_djrectjon1&&up_djrectjon1||i_djrectjon1&&down_djrectjon1||up_djrectjon1&&down_djrectjon1){
                    weights[i][j] = 2700;
                    return;
                }


            }




            /*
            判断四子，无气泡检验
             */

            //j 方向，有隔档，无气泡检验
            if (j < 12 && frame.ints[j][j+1] == 2 && frame.ints[j][j+2]== 2 && frame.ints[j][j+3]== 2 ) {
                weights[i][j] = 243;
                return;
            }
            if (j >= 3 && frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2 && frame.ints[i][j - 3] == 2) {
                weights[i][j] = 242;
                return;
            }
            if (j >= 1 && j < 13 && (frame.ints[i][j - 1] == 2 && frame.ints[j][j+1] == 2&& frame.ints[j][j+2]== 2)) {
                weights[i][j] = 241;
                return;
            }
            if (j >= 2 && j < 14 && (frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2&& frame.ints[j][j+1] == 2)) {
                weights[i][j] = 241;
                return;
            }
            if (j >= 3 && j < 15 && (frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2&& frame.ints[i][j - 3] == 2)) {
                weights[i][j] = 241;
                return;
            }
            /////
            if (j < 12 && frame.ints[j][j+1] == 1 && frame.ints[j][j+2]== 1 && frame.ints[j][j+3]== 1 ) {
                weights[i][j] = 143;
                return;
            }
            if (j >= 3 && frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1 && frame.ints[i][j - 3] == 1) {
                weights[i][j] = 142;
                return;
            }
            if (j >= 1 && j < 13 && (frame.ints[i][j - 1] == 1 && frame.ints[j][j+1] == 1&& frame.ints[j][j+2]== 1)) {
                weights[i][j] = 141;
                return;
            }
            if (j >= 2 && j < 14 && (frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1&& frame.ints[j][j+1] == 1)) {
                weights[i][j] = 141;
                return;
            }
            if (j >= 3 && j < 15 && (frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1&& frame.ints[i][j - 3] == 1)) {
                weights[i][j] = 141;
                return;
            }




            //i方向无气泡检验
            if (i < 12 && frame.ints[i+1][j] == 2 && frame.ints[i+2][j] == 2 && frame.ints[i+3][j] == 2 ) {
                weights[i][j] = 243;
                return;
            }
            if (i >= 3 && frame.ints[i-1][j] == 2 && frame.ints[i-2][j] == 2 && frame.ints[i-3][j] == 2) {
                weights[i][j] = 242;
                return;
            }
            if (i >= 1 && i < 13 && (frame.ints[i-1][j] == 2 && frame.ints[i+1][j] == 2&& frame.ints[i+2][j] == 2)) {
                weights[i][j] = 241;
                return;
            }
            if (i >= 2 && i < 14 && (frame.ints[i-1][j] == 2 && frame.ints[i-2][j] == 2&& frame.ints[i+1][j] == 2)) {
                weights[i][j] = 241;
                return;
            }
            if (i >= 3 && i < 15 && (frame.ints[i-1][j] == 2 && frame.ints[i-2][j] == 2&& frame.ints[i-3][j] == 2)) {
                weights[i][j] = 241;
                return;
            }
            ////
            if (i < 12 && frame.ints[i+1][j] == 1 && frame.ints[i+2][j] == 1 && frame.ints[i+3][j] == 1 ) {
                weights[i][j] = 143;
                return;
            }
            if (i >= 3 && frame.ints[i-1][j] == 1 && frame.ints[i-2][j] == 1 && frame.ints[i-3][j] == 1) {
                weights[i][j] = 142;
                return;
            }
            if (i >= 1 && i < 13 && (frame.ints[i-1][j] == 1 && frame.ints[i+1][j] == 1&& frame.ints[i+2][j] == 1)) {
                weights[i][j] = 141;
                return;
            }
            if (i >= 2 && i < 14 && (frame.ints[i-1][j] == 1 && frame.ints[i-2][j] == 1&& frame.ints[i+1][j] == 1)) {
                weights[i][j] = 141;
                return;
            }
            if (i >= 3 && i < 15 && (frame.ints[i-1][j] == 1 && frame.ints[i-2][j] == 1&& frame.ints[i-3][j] == 1&& frame.ints[i+1][j] == 1)) {
                weights[i][j] = 141;
                return;
            }




            //向下方向无气泡检验
            if (i < 12 &&j < 12 && frame.ints[i+1][j + 1] == 2 && frame.ints[i+2][j + 2] == 2 && frame.ints[i+3][j + 3] == 2 ) {
                weights[i][j] = 243;
                return;
            }
            if (i >= 3 && j >= 3 && frame.ints[i-1][j - 1] == 2 && frame.ints[i-2][j - 2] == 2 && frame.ints[i-3][j - 3] == 2) {
                weights[i][j] = 242;
                return;
            }
            if (i >= 1 && i < 13&& j >= 1 && j < 13 && (frame.ints[i-1][j - 1] == 2 && frame.ints[i+1][j + 1] == 2&& frame.ints[i+2][j + 2] == 2)) {
                weights[i][j] = 241;
                return;
            }
            if (i >= 2 && i < 14 &&j >= 2 && j < 14 && (frame.ints[i-1][j - 1] == 2 && frame.ints[i-2][j - 2] == 2&& frame.ints[i+1][j + 1] == 2)) {
                weights[i][j] = 241;
                return;
            }
            if (i >= 3 && i < 15 && j >= 3 && j < 15 && (frame.ints[i-1][j - 1] == 2 && frame.ints[i-2][j - 2] == 2&& frame.ints[i-3][j - 3] == 2)) {
                weights[i][j] = 241;
                return;
            }
            ////
            if (i < 12 &&j < 12 && frame.ints[i+1][j + 1] == 1 && frame.ints[i+2][j + 2] == 1 && frame.ints[i+3][j + 3] == 1 ) {
                weights[i][j] = 143;
                return;
            }
            if (i >= 3 && j >= 3 && frame.ints[i-1][j - 1] == 1 && frame.ints[i-2][j - 2] == 1 && frame.ints[i-3][j - 3] == 1) {
                weights[i][j] = 142;
                return;
            }
            if (i >= 1 && i < 13 &&j >= 1 && j < 13 && (frame.ints[i-1][j - 1] == 1 && frame.ints[i+1][j + 1] == 1&& frame.ints[i+2][j + 2] == 1)) {
                weights[i][j] = 141;
                return;
            }
            if (i >= 2 && i < 14 &&j >= 2 && j < 14 && (frame.ints[i-1][j - 1] == 1 && frame.ints[i-2][j - 2] == 1&& frame.ints[i+1][j + 1] == 1)) {
                weights[i][j] = 141;
                return;
            }
            if (i >= 3 && i < 15 &&j >= 3 && j < 15 && (frame.ints[i-1][j - 1] == 1 && frame.ints[i-2][j - 2] == 1&& frame.ints[i-3][j - 3] == 1)) {
                weights[i][j] = 141;
                return;
            }




            //向上，无气泡检测
            if (i >=3 &&j < 12 && frame.ints[i-1][j + 1] == 2 && frame.ints[i-2][j + 2] == 2 && frame.ints[i-3][j + 3] == 2 ) {
                weights[i][j] = 243;
                return;
            }
            if (i <12 &&j >= 3 && frame.ints[i+1][j - 1] == 2 && frame.ints[i+2][j - 2] == 2 && frame.ints[i+3][j - 3] == 2) {
                weights[i][j] = 242;
                return;
            }
            if (i >= 2 && i < 14 &&j >= 1 && j < 13 && (frame.ints[i+1][j - 1] == 2 && frame.ints[i-1][j + 1] == 2&& frame.ints[i-2][j + 2] == 2)) {
                weights[i][j] = 241;
                return;
            }
            if (i >= 1 && i < 13 &&j >= 2 && j < 14 && (frame.ints[i+1][j - 1] == 2 && frame.ints[i+2][j - 2] == 2&& frame.ints[i-1][j + 1] == 2)) {
                weights[i][j] = 241;
                return;
            }
            if (i >= 0 && i < 12 && j >= 3 && j < 15 && (frame.ints[i+1][j - 1] == 2 && frame.ints[i+2][j - 2] == 2&& frame.ints[i+3][j - 3] == 2)) {
                weights[i][j] = 241;
                return;
            }
            ////
            if (i >=3 &&j < 12 && frame.ints[i-1][j + 1] == 1 && frame.ints[i-2][j + 2] == 1 && frame.ints[i-3][j + 3] == 1 ) {
                weights[i][j] = 143;
                return;
            }
            if (i <12 &&j >= 3 && frame.ints[i+1][j - 1] == 1 && frame.ints[i+2][j - 2] == 1 && frame.ints[i+3][j - 3] == 1) {
                weights[i][j] = 142;
                return;
            }
            if (i >= 2 && i < 14 &&j >= 1 && j < 13 && (frame.ints[i+1][j - 1] == 1 && frame.ints[i-1][j + 1] == 1&& frame.ints[i-2][j + 2] == 1)) {
                weights[i][j] = 141;
                return;
            }
            if (i >= 1 && i < 13 &&j >= 2 && j < 14 && (frame.ints[i+1][j - 1] == 1 && frame.ints[i+2][j - 2] == 1&& frame.ints[i-1][j + 1] == 1)) {
                weights[i][j] = 141;
                return;
            }
            if (i >= 0 && i < 12 && j >= 3 && j < 15 && (frame.ints[i+1][j - 1] == 1 && frame.ints[i+2][j - 2] == 1&& frame.ints[i+3][j - 3] == 1)) {
                weights[i][j] = 141;
                return;
            }

        }



        /*
        判断三子
         */
        {

            //j方向
            if (j < 13 && frame.ints[j][j+1] == 2 && frame.ints[j][j+2]== 2) {
                weights[i][j] = 33;
                return;
            }
            if (j >= 2 && frame.ints[i][j - 1] == 2 && frame.ints[i][j - 2] == 2) {
                weights[i][j] = 32;
                return;
            }
            if (j >= 1 && j < 14 && frame.ints[i][j - 1] == 2 && frame.ints[j][j+1] == 2) {
                weights[i][j] = 31;
                return;
            }

            if ((j < 13 && frame.ints[j][j+1] == 1 && frame.ints[j][j+2]== 1)) {
                weights[i][j] = 23;
                return;
            }
            if (j >= 2 && frame.ints[i][j - 1] == 1 && frame.ints[i][j - 2] == 1) {
                weights[i][j] = 22;
                return;
            }
            if (j >= 1 && j < 14 && frame.ints[i][j - 1] == 1 && frame.ints[j][j+1] == 1) {
                weights[i][j] = 21;
                return;
            }






            //i方向
            if (i < 13 && frame.ints[i + 1][j] == 2 && frame.ints[i + 2][j] == 2) {
                weights[i][j] = 33 - 1;
                return;
            }
            if (i >= 2 && frame.ints[i - 1][j] == 2 && frame.ints[i - 2][j] == 2) {
                weights[i][j] = 32 - 1;
                return;
            }
            if (i >= 1 && i < 14 && frame.ints[i - 1][j] == 2 && frame.ints[i + 1][j] == 2) {
                weights[i][j] = 31 - 1;
                return;
            }

            if (i < 13 && frame.ints[i + 1][j] == 1 && frame.ints[i + 2][j] == 1) {
                weights[i][j] = 23 - 1;
                return;
            }
            if (i >= 2 && frame.ints[i - 1][j] == 1 && frame.ints[i - 2][j] == 1) {
                weights[i][j] = 22 - 1;
                return;
            }
            if (i >= 1 && i < 14 && frame.ints[i - 1][j] == 1 && frame.ints[i + 1][j] == 1) {
                weights[i][j] = 21 - 1;
                return;
            }


            //向下方向
            if (i < 13 && j < 13 && frame.ints[i + 1][j + 1] == 2 && frame.ints[i + 2][j + 2] == 2) {
                weights[i][j] = 33 - 2;
                return;
            }
            if (i >= 2 && j >= 2 && frame.ints[i - 1][j - 1] == 2 && frame.ints[i - 2][j - 2] == 2) {
                weights[i][j] = 32 - 2;
                return;
            }
            if (i >= 1 && i < 14 && j >= 1 && j < 14 && frame.ints[i - 1][j - 1] == 2 && frame.ints[i + 1][j + 1] == 2) {
                weights[i][j] = 31 - 2;
                return;
            }

            if (i < 13 && j < 13 && frame.ints[i + 1][j + 1] == 1 && frame.ints[i + 2][j + 2] == 1) {
                weights[i][j] = 23 - 2;
                return;
            }
            if (i >= 2 && j >= 2 && frame.ints[i - 1][j - 1] == 1 && frame.ints[i - 2][j - 2] == 1) {
                weights[i][j] = 22 - 2;
                return;
            }
            if (i >= 1 && i < 14 && j >= 1 && j < 14 && frame.ints[i - 1][j - 1] == 1 && frame.ints[i + 1][j + 1] == 1) {
                weights[i][j] = 21 - 2;
                return;
            }


            //向上方向
            if (i >= 2 && j < 13 && frame.ints[i - 1][j + 1] == 2 && frame.ints[i - 2][j + 2] == 2) {
                weights[i][j] = 33 - 3;
                return;
            }
            if (i < 13 && j >= 2 && frame.ints[i + 1][j - 1] == 2 && frame.ints[i + 2][j - 2] == 2) {
                weights[i][j] = 32 - 3;
                return;
            }
            if (i >= 1 && i < 14 && j >= 1 && j < 14 && frame.ints[i + 1][j - 1] == 2 && frame.ints[i - 1][j + 1] == 2) {
                weights[i][j] = 31 - 3;
                return;
            }

            if (i >= 2 && j < 13 && frame.ints[i - 1][j + 1] == 1 && frame.ints[i-2][j + 2] == 1) {
                weights[i][j] = 23 - 3;
                return;
            }
            if (i < 13 && j >= 2 && frame.ints[i + 1][j - 1] == 1 && frame.ints[i + 2][j - 2] == 1) {
                weights[i][j] = 22 - 3;
                return;
            }
            if (i >= 1 && i < 14 && j >= 1 && j<14&&frame.ints[i+1][j - 1] == 1 && frame.ints[i - 1][j + 1] == 1) {
                weights[i][j] = 21 - 3;
                return;
            }
        }

        /*
        判断两子
         */
        if(j>=1&&j<14&&i>=1&&i<14&&(frame.ints[i][j+1] == 2 ||frame.ints[i+1][j] == 2 ||frame.ints[i][j - 1] == 2 ||frame.ints[i-1][j] == 2 ||frame.ints[i+1][j - 1] ==2 ||frame.ints[i-1][j + 1] == 2 ||frame.ints[i+1][j + 1] == 2 ||frame.ints[i-1][j - 1] == 2  )){
            weights[i][j] = 4;return;
        }
        if(j>=1&&j<14&&i>=1&&i<14&&(frame.ints[i][j+1] == 1 ||frame.ints[i+1][j] == 1 ||frame.ints[i][j - 1] == 1 ||frame.ints[i-1][j] == 1 ||frame.ints[i+1][j - 1] == 1 ||frame.ints[i-1][j + 1] == 1 ||frame.ints[i+1][j + 1] == 1 ||frame.ints[i-1][j - 1] == 1  )){
            weights[i][j] = 2;return;
        }


    }
}
