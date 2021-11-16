package Grafica.Button.EditorPanelButton;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;


import Grafica.MainFrame;

public class InfoButton extends JButton{
	private int height;
	private int width;
	private MainFrame mainFrame;
	
	public InfoButton(String nameButton,int height,int width,MainFrame mainFrame) {
		
		this.height=height;
		this.width=width;
		this.mainFrame=mainFrame;
		
		setIcon(new ImageIcon(getImage("./ImgEditor/ButtonInfo.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));
		this.setBorder(null);
		this.setOpaque(false);
		setName(nameButton);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
	}
	

	private Image getImage(String pathImage)
	{
		return mainFrame.getImageProvider().getImage(pathImage);
	}
	public void setImage(String pathImage)
	{
		setIcon(new ImageIcon(getImage(pathImage).getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));

	}
	
	public void PressButton ()
	{
		setIcon(new ImageIcon(getImage("./ImgEditor/ButtonInfoPressed.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));

	}
	
	public void ReleaseButton () 
	{
		setIcon(new ImageIcon(getImage("./ImgEditor/ButtonInfo.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));

	}
	
	
}
