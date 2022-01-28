package utils;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel{

    private Image image;

   public void setImage(Image image){

       this.image = image ;
   }

   public void paintComponent(Graphics g){
       g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
   }

   public BackgroundPanel(){
       this.setLayout(null);
   }

}
