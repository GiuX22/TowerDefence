package PlugIn;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;

import javax.swing.JPanel;


public class PanelQRCode extends JPanel implements KeyListener{

	BufferedImage bimage;
	
	public PanelQRCode(BufferedImage bufferedImage)
	{
		this.bimage=bufferedImage;
		this.addKeyListener(this);
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bimage, 0, 0, 400, 300,null);
	}
	
	public void setImage(BufferedImage image)
	{
		bimage=image;
		updateUI();
	}


	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyPressed(KeyEvent e) {}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==10)
		{
			UtilityQRCode utilityQRCode=UtilityQRCode.getIstanceSimple();
			utilityQRCode.catturata=true;	
		}		
	}


	public void refresh(BufferedImage image) {
		this.bimage = image;
		repaint();	
	}

}
