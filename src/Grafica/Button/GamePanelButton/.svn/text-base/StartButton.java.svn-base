package Grafica.Button.GamePanelButton;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Grafica.GamePanel;
import Grafica.MainFrame;

public class StartButton extends JButton {

	private int height;
	private int width;
	private MainFrame mainFrame;
	private ImageIcon icon;
	
	public StartButton(String nameButton,int x,int y,int height,int width,MainFrame mainFrame) {
		this.height=height;
		this.width=width;
		this.mainFrame=mainFrame;
		icon=new ImageIcon(getImage(nameButton+".png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH));
		setIcon(icon);
		setName(nameButton);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setBounds(x, y, width, height);
		
	}
	private Image getImage(String pathImage)
	{
		return mainFrame.getImageProvider().getImage(pathImage);
	}
	public void setImage(String pathImage)
	{
		icon.setImage(getImage(pathImage).getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH));
		setIcon(icon);

	}
	
	
	
}