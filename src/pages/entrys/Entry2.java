package pages.entrys;

import danJi.PlayFrame_2;
import shuangRenTongJi.PlayFrame_1;
import tools.Tools;
import utils.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Entry2 extends JFrame {
    public Entry2() {

        //frame
        this.setSize(1200, 660);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        //contentPane
        BackgroundPanel contentPane = new BackgroundPanel();
        this.setContentPane(contentPane);
        contentPane.setImage(Tools.beijing);
        contentPane.setSize(1200, 660);
        //mouseListener
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + ";" + y);
            }
        });

        //buttons
        JButton button_1 = new JButton("双人同机模式");
        JButton button_2 = new JButton("单机模式");
        JButton button_3 = new JButton("联机模式");
        JButton button_4 = new JButton("back");
        contentPane.add(button_1);
        contentPane.add(button_2);
        contentPane.add(button_3);
        contentPane.add(button_4);
        button_1.setBounds(400, 370, 320, 80);
        button_2.setBounds(400, 460, 320, 75);
        button_3.setBounds(400, 550, 320, 70);
        button_4.setBounds(800, 550, 120, 50);
        button_1.setBackground(Color.ORANGE);
        button_2.setBackground(Color.ORANGE);
        button_3.setBackground(Color.ORANGE);
        button_4.setBackground(Color.ORANGE);
        button_1.setFont(new Font(null, Font.BOLD, 20));
        button_2.setFont(new Font(null, Font.BOLD, 20));
        button_3.setFont(new Font(null, Font.BOLD, 20));
        button_4.setFont(new Font(null, Font.BOLD, 20));
        //actionListener
        button_1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new PlayFrame_1();
            }
        });
        button_2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new PlayFrame_2();
            }
        });
        button_3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new LianJi();
            }
        });

    }
}
