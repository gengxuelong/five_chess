package pages.entrys;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tools.Tools;
import utils.BackgroundPanel;
import utils.Player;

public class SheZhi extends JFrame {

    ImageIcon[] icons = new ImageIcon[]{Tools.start,Tools.close};
    int index = 0;
    Player player = new Player(Tools.path2+"bgm.wav");

    public SheZhi(){
        //frame
        this.setSize(1200,660);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //contentPane
        BackgroundPanel contentPane = new BackgroundPanel();
        this.setContentPane(contentPane);
        contentPane.setSize(1200,660);
        contentPane.setImage(Tools.shezhi);

        //label
        JLabel label = new JLabel("背景音乐: ");
        contentPane.add(label);
        label.setBounds(300,200,300,150);
        label.setFont(new Font(null,Font.BOLD,50));

        //button
        JButton button = new JButton();
        contentPane.add(button);
        button.setIcon(Tools.start);
        button.setBounds(600,200,150,150);
        //actionListener
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index++;
                int temp = index%2;
                ImageIcon icon = icons[temp];
                button.setIcon(icon);
                if (temp == 0) {
                    player.startBgm();
                }else {
                    player.stopBgm();
                }
            }
        });

        //player
        player.startBgm();
    }
}
