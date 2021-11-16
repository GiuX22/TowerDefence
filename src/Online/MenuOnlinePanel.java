package Online;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Grafica.ImageProvider;
import Grafica.MainFrame;

public class MenuOnlinePanel extends JPanel{
	private BufferedImage showcaseWall;
    private MainFrame mainframe;
    private Image chat;

	public MenuOnlinePanel(MainFrame mainframe)
	{
		this.mainframe=mainframe;
		this.setBounds(0, 0, this.mainframe.getWidth(), this.mainframe.getHeight());
		this.setLayout(null);
		this.setOpaque(true);
		this.setVisible(true);
		try {

			showcaseWall = ImageIO.read(new File("Img/Sfondo1.jpeg")) ;

			showcaseWall = ImageIO.read(new File("Img/sfondoViolet.jpeg")) ;
			chat=mainframe.getImageProvider().getImage("chatBackground.png");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(showcaseWall, 0, 0,mainframe.getWidth(), mainframe.getHeight(),null);
		g.setColor(Color.cyan);
		g.drawString("Lista Users:",450,60);
		g.drawString("Lista Rooms:",850,60);
		g.drawString("Room-World:",1050,60);

		
        g.drawImage(chat, (int)getWidth()/3 -58, (int)getHeight()/2,(int)getWidth()/2+205,250,null);


	}
}
