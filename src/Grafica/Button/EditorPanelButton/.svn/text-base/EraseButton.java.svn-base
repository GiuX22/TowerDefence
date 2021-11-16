package Grafica.Button.EditorPanelButton;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Grafica.MainFrame;

public class EraseButton extends JButton {


	private int height;
	private int width;
	private MainFrame mainFrame;
	private boolean isNormal = true;
	public EraseButton(String nameButton,int height,int width,MainFrame mainFrame,String tipo) {
		this.height=height;
		this.width=width;
		this.mainFrame=mainFrame;
		this.setBorder(null);
		if (tipo.equals("GommaNormal"))
		{
			isNormal = true;
			setIcon(new ImageIcon(getImage("./ImgEditor/ButtonErase.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));
		}
		else if (tipo.equals("ClearAll"))
		{
			isNormal = false;
			setIcon(new ImageIcon(getImage("./ImgEditor/ButtonEraseAll.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));

		}

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
		if(isNormal)
		{
			setIcon(new ImageIcon(getImage("./ImgEditor/ButtonErasePressed.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));
		}
		else
		{
			setIcon(new ImageIcon(getImage("./ImgEditor/ButtonEraseAllPressed.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));
		}
	}

	public void ReleaseButton () 
	{
		
		if(isNormal)
		{
			setIcon(new ImageIcon(getImage("./ImgEditor/ButtonErase.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));
		}
		else
		{
			setIcon(new ImageIcon(getImage("./ImgEditor/ButtonEraseAll.png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)));

		}
	}


}
