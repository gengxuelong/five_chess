package pages.entrys;

import lianWan.PlayFrame_net;
import tools.Tools;
import utils.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LianJi extends JFrame {
    LianJi that = this;
    Image image = Tools.beijing2;

    String open_port;

    String entry_port;
    String entry_remote_port;
    String entry_remote_IP;

    JTextField textField_4;
    JTextField textField_2;
    JTextField textField_3;


    public LianJi(){
        /*
        frame
         */
        this.setSize(1200,660);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        /*
        contentPane
         */
        BackgroundPanel contentPane = new BackgroundPanel();
        this.setContentPane(contentPane);
        contentPane.setImage(image);
        /*
        open room
         */
        JPanel openRoom = new JPanel();
        contentPane.add(openRoom);
        openRoom.setBounds(100,50,400,500);
        openRoom.setBorder(new TitledBorder("开启一个房间"));
        JLabel label = new JLabel("请输入您的端口号");
        JTextField textField_1 = new JTextField(30);
        JButton button_1 = new JButton("确定");
        openRoom.add(label);
        openRoom.add(textField_1);
        openRoom.add(button_1);


        /*
        entry room
         */
        JPanel entryRoom = new JPanel();
        contentPane.add(entryRoom);
        entryRoom.setBounds(600,50,400,500);
        entryRoom.setBorder(new TitledBorder("进入一个房间"));

        JLabel label_2 = new JLabel("请输入您的端口号");
         textField_2 = new JTextField(30);

        JLabel label_3 = new JLabel("请输入对方的IP");
         textField_3 = new JTextField(30);

        JLabel label_4 = new JLabel("请输入对方的端口号");
         textField_4 = new JTextField(20);

        JButton button_2 = new JButton("确定");
        entryRoom.setLayout(new GridLayout(4,2));
        entryRoom.add(label_2);
        entryRoom.add(textField_2);
        entryRoom.add(label_3);
        entryRoom.add(textField_3);
        entryRoom.add(label_4);
        entryRoom.add(textField_4);
        entryRoom.add(button_2);

        button_1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = textField_1.getText();
                if(str != null){
                    System.out.println(str);
                    open_port = str;
                    try{
                        int temp = Integer.parseInt(str);
                        new PlayFrame_net(1,temp);
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(that,"端口号信息格式不正确");
                    }
                }
            }
        });

        button_2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               do_button_2();
            }
        });

    }

    public void do_button_2(){
        int port = 0;
        String ip ="";
        int port_remote = 0;
        entry_port = textField_2.getText();
        entry_remote_IP = textField_3.getText();
        entry_remote_port = textField_4.getText();
        if(entry_port != null &&entry_remote_port!=null&&entry_remote_IP!=null){
            System.out.println(entry_port);
            System.out.println(entry_remote_IP);
            System.out.println(entry_remote_port);


            try{
                port = Integer.parseInt(entry_port);
                port_remote = Integer.parseInt(entry_remote_port);
                String[] strings = entry_remote_IP.split("\\.");
                if(strings.length!=4) {
                    throw new RuntimeException();
                }
                for(String str : strings){
                    Integer.parseInt(str);
                }
                ip = entry_remote_IP;
                new PlayFrame_net(2,port,port_remote,ip);
            } catch (RuntimeException runtimeException) {
                JOptionPane.showMessageDialog(that,"端口号信息格式不正确");
            }

        }else{
            JOptionPane.showMessageDialog(that,"请将信息填写完整");
        }
    }
}
