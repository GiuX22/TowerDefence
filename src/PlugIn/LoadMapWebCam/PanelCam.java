package PlugIn.LoadMapWebCam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanelCam extends JPanel {

	private BufferedImage image;
	private boolean drawSchema;

	public PanelCam() {
		drawSchema=false;
	}

	public void drawSchema()
	{
		drawSchema=true;
		repaint();
	}

	public void setImage(BufferedImage img)
	{
		image=img;
	}
	public void repaintPanel()
	{
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage(image,0,0,null);

		g.setColor(new Color(0, 255, 0));
		g.drawLine(50*2, 70*2, 275*2, 70*2);
		g.drawLine(50*2, 235*2, 275*2, 235*2);
		g.drawLine(50*2, 70*2, 50*2, 235*2);
		g.drawLine(275*2, 70*2, 275*2, 235*2);

		int sizeW=(int)((275-50)/25);
		int sizeH=(int)((235-70)/15);


		for (int i = 50; i <275; i+=sizeW) {
			g.drawLine(i*2, 70*2, i*2, 235*2);
		}
		for (int j = 70; j <235; j+=sizeH) {
			g.drawLine(50*2, j*2, 275*2, j*2);
		}



	}

}
