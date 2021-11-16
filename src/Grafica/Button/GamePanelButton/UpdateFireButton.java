package Grafica.Button.GamePanelButton;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Grafica.MainFrame;

public class UpdateFireButton extends JButton {
	
	private int height;
	private int width;
	private MainFrame mainFrame;
	private ImageIcon icon;

	public UpdateFireButton(String nameButton,int x,int y,int width,int height,MainFrame mainFrame) {
		this.height=height;
		this.width=width;
		this.mainFrame=mainFrame;
		icon=new ImageIcon(getImage("updateFire.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH));
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
