package lianWan;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class udp_agency {
    boolean start = false;
    int myPort ;
    int remotePort;
    String remoteIP;
    PlayFrame_net frame;

    DatagramSocket socket;
    udp_agency that = this;

    public udp_agency(PlayFrame_net frame ,int myPort, int remotePort, String remoteIP) {
        this.frame = frame;
        this.myPort = myPort;
        this.remotePort = remotePort;
        this.remoteIP = remoteIP;

        try {
            socket = new DatagramSocket(myPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                that.receiving();
            }
        }).start();

    }

    public udp_agency(PlayFrame_net frame ,int myPort) {
        this.frame = frame;
        this.myPort = myPort;
        try {
            socket = new DatagramSocket(myPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                that.receiving();
            }
        }).start();

    }

    public void receiving(){
        while(true){
            DatagramPacket packet = null;
            try {
                packet = new DatagramPacket(new byte[1024],1024);
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] bites = packet.getData();
            String res = new String(bites,0,packet.getLength());
            System.out.println(res);
            String[] strings = res.split("_");
            if(strings[0].equals("A")){
                Point point = new Point(Integer.parseInt(strings[1]),Integer.parseInt(strings[2]));
                frame.play(point,1);
            }else if(strings[0].equals("B")){
                Point point = new Point(Integer.parseInt(strings[1]),Integer.parseInt(strings[2]));
                frame.play(point,2);
            }else if(strings[0] .equals("C")){
                if(strings[1].equals("init"))
                    frame.init();
                else if(strings[1].equals("iswin")){
                    frame.iswin = true;
                }else if(strings[1].equals("start")&&frame.myCamp == 1){
                    this.remotePort = Integer.parseInt(strings[3]);
                    this.remoteIP = strings[2];
                    this.sendMsg("C_reply");
                    start = true;
                }else if(strings[1].equals("reply")&&frame.myCamp == 2){
                    start = true;
                }
            }


        }
    }

    public void sendMsg(String msg){
        DatagramPacket packet = null;
        try {
            packet = new DatagramPacket(msg.getBytes(StandardCharsets.UTF_8),0,
                    msg.getBytes().length, InetAddress.getByName(remoteIP),remotePort);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
