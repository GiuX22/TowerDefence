package Grafica.Button.EditorPanelButton;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Grafica.MainFrame;

public class DirectionalButton extends JButton{

	
	private int height;
	private int width;
	private MainFrame mainFrame;


	public DirectionalButton(String direction,int height,int width,MainFrame mainFrame) {
		this.height=height;
		this.width=width;
		this.mainFrame=mainFrame;
		if(direction.equals("left"))
		{
			setIcon(new ImageIcon(getImage("leftList.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));
		}
		if(direction.equals("right"))
		{
			setIcon(new ImageIcon(getImage("rightList.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));
		}
		setBorderPainted(false);
		setContentAreaFilled(false);
		
	}
	private Image getImage(String pathImage)
	{
		return mainFrame.getImageProvider().getImage(pathImage);
	}
	public void setImage(String pathImage)
	{
		setIcon(new ImageIcon(getImage(pathImage).getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));

	}
	
}
