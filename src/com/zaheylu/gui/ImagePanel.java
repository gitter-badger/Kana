package com.zaheylu.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private static final long serialVersionUID = -2775796911308818580L;
	private BufferedImage image;

    public ImagePanel() {
       try {                
          image = ImageIO.read(ImagePanel.class.getResource("/res/schnecke.png"));
       } catch (IOException ex) {
    	   ex.printStackTrace();
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); 
    }

}