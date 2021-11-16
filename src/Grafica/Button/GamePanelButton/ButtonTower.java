package Grafica.Button.GamePanelButton;


import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.ToolTipManager;

import Grafica.MainFrame;

public class ButtonTower extends JButton {

	private int height;
	private int width;
	private String tower;
	private MainFrame mainFrame;
	private ImageIcon icon;
	
	public ButtonTower(String nameButton,int x,int y,int height,int width,String tower,MainFrame mainFrame) {
		this.height=height;
		this.width=width;
		this.mainFrame=mainFrame;
		this.tower=tower;
		icon=new ImageIcon(getImage("Towers/"+tower+".png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH));
	     setIcon(icon);
	     setName(nameButton);
	     setBorderPainted(false);
	     setContentAreaFilled(false);
	     setBounds(x, y, width, height);
	     ToolTipManager.sharedInstance().setDismissDelay(1500);// 15 seconds    
	}
    private Image getImage(String pathImage)
    {
    	return mainFrame.getImageProvider().getImage(pathImage);
    }
    public void setImage(String tower)
    {
    	icon.setImage(getImage("Towers/"+tower+".png").getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH));

    }

	public void setTower(String Tower)
	{
		this.tower=Tower;
	}
	public String getTower()
	{return tower;}
	
	public Point getToolTipLocation(MouseEvent e) {
        return new Point(-20, 30);
      }
	
}
