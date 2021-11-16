package PlugIn;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import javax.swing.JFrame;


public class Frame extends JFrame{

	PanelQRCode panelQR;
	BufferedImage image;
	public Frame(BufferedImage bufferedImage)
	{
		this.image=bufferedImage;
			//this.setBounds(400, 200, 400, 300);
			 this.setUndecorated(true);
			this.setTitle("Prova icona");
			this.setResizable(false);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			panelQR=new PanelQRCode(bufferedImage);
			this.add(panelQR);
			panelQR.requestFocus();
	}
	
	public void refresh(BufferedImage a)
	{
		panelQR.setImage(a);
		panelQR.repaint();
	}
}
