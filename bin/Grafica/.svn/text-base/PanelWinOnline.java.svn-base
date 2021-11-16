package Grafica;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelWinOnline extends JPanel{
	BufferedImage img = null;

	public PanelWinOnline()
	{
		try {
			img = ImageIO.read(new File("Img/pannelloVinto.jpg"));
		} catch (IOException e) {
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, MainFrame.getIstanceMainframe().getWidth(),MainFrame.getIstanceMainframe().getHeight(),null);
	}

}
