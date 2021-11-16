package Grafica;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logicaOffline.utility.AePlayWave;

@SuppressWarnings("serial")
public class StrategySelectionPanel extends JPanel implements MouseListener{

	private MainFrame frame;
	private int tileSizeX;
	private int tileSizeY;
	private int ButtonWidth=150;
	private int ButtonHeight=80;
	private JButton buttonEasy;
	private JButton buttonMedium;
	private JButton buttonHard;
	private JButton buttonBack;
	private JLabel imagePreview;
	private ImageIcon easy;
	private ImageIcon medium;
	private ImageIcon hard;
	private ImageIcon empty;

	private int widthLabel;
	private int heightLabel;

	public StrategySelectionPanel(final MainFrame mainFrame) {
		frame=mainFrame;
		this.setSize(frame.getWidth(),frame.getHeight());
		this.setLayout(null);
		tileSizeX=(int)(this.getWidth()/4);
		tileSizeY=(int)this.getHeight()/5;

		setButton();
		setImagePreview();

	}
	private Image getImage(String PathImage)
	{
		return frame.getImageProvider().getImage(PathImage);
	}

	private void setImagePreview()
	{
		imagePreview=new JLabel();
		 widthLabel=(int)getWidth()/2;
		 heightLabel=(int)getHeight()/2;
		imagePreview.setBounds((int)getWidth()/4, (int)getHeight()/2-50, widthLabel,heightLabel);
	
		easy=new ImageIcon(getImage("strategyPreviewEasy.png").getScaledInstance( widthLabel, heightLabel, java.awt.Image.SCALE_SMOOTH));
		medium=new ImageIcon(getImage("strategyPreviewMedium.png").getScaledInstance( widthLabel, heightLabel, java.awt.Image.SCALE_SMOOTH));
		hard=new ImageIcon(getImage("strategyPreviewHard.png").getScaledInstance( widthLabel, heightLabel, java.awt.Image.SCALE_SMOOTH));
		empty=new ImageIcon(getImage("strategyPreviewEmpty.png").getScaledInstance( widthLabel, heightLabel, java.awt.Image.SCALE_SMOOTH));
		
		imagePreview.setIcon(empty);
		
		this.add(imagePreview);
	}
	
	private void setButton()
	{

		buttonEasy=new JButton(new ImageIcon(getImage("strategyEasy.png").getScaledInstance(tileSizeX, tileSizeY, java.awt.Image.SCALE_SMOOTH)));
		buttonMedium=new JButton(new ImageIcon(getImage("strategyMedium.png").getScaledInstance(tileSizeX, tileSizeY, java.awt.Image.SCALE_SMOOTH)));
		buttonHard=new JButton(new ImageIcon(getImage("strategyHard.png").getScaledInstance(tileSizeX, tileSizeY, java.awt.Image.SCALE_SMOOTH)));
		buttonBack=new JButton(new ImageIcon(getImage("buttonBack.png").getScaledInstance(ButtonWidth, ButtonHeight, java.awt.Image.SCALE_SMOOTH)));

		buttonEasy.setName("buttonEasy");
		buttonMedium.setName("buttonMedium");
		buttonHard.setName("buttonHard");
		buttonBack.setName("buttonBack");
		
		buttonEasy.setBorderPainted(false);
		buttonEasy.setContentAreaFilled(false);
		buttonMedium.setBorderPainted(false);
		buttonMedium.setContentAreaFilled(false);
		buttonHard.setBorderPainted(false);
		buttonHard.setContentAreaFilled(false);
		buttonBack.setBorderPainted(false);
		buttonBack.setContentAreaFilled(false);


		buttonEasy.setBounds(150,(int)getHeight()/6,tileSizeX, tileSizeY);
		buttonMedium.setBounds(170+tileSizeX, (int)getHeight()/6,tileSizeX, tileSizeY);
		buttonHard.setBounds(190+2*tileSizeX, (int)getHeight()/6,tileSizeX, tileSizeY);
		buttonBack.setBounds(this.getWidth()-(ButtonWidth+20),  this.getHeight()-(ButtonHeight+20),ButtonWidth, ButtonHeight);
		
		buttonEasy.addMouseListener(this);
		buttonMedium.addMouseListener(this);
		buttonHard.addMouseListener(this);
		buttonBack.addMouseListener(this);

		this.add(buttonMedium);
		this.add(buttonHard);
		this.add(buttonBack);
		this.add(buttonEasy);
	}
    private void setImage(String buttonName,String pathImage){
    	
    	switch (buttonName) {
		case "buttonEasy":
			buttonEasy.setIcon(new ImageIcon(getImage(pathImage).getScaledInstance(tileSizeX, tileSizeY, java.awt.Image.SCALE_SMOOTH)));
			break;
		case "buttonMedium":
			buttonMedium.setIcon(new ImageIcon(getImage(pathImage).getScaledInstance(tileSizeX, tileSizeY, java.awt.Image.SCALE_SMOOTH)));
			break;
		case "buttonHard":
			buttonHard.setIcon(new ImageIcon(getImage(pathImage).getScaledInstance(tileSizeX, tileSizeY, java.awt.Image.SCALE_SMOOTH)));
			break;
		case "buttonBack":
			buttonBack.setIcon(new ImageIcon(getImage(pathImage).getScaledInstance(ButtonWidth, ButtonHeight, java.awt.Image.SCALE_SMOOTH)));
			break;

		}
    }


	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage(frame.getImageProvider().getImage("strategySelectionPanel.png"), 0, 0, getWidth(), getHeight(), null);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		new AePlayWave("sound/buttonEnteredMenu.wav").start();

	Object event=e.getSource();
	    if(event instanceof JButton){
	    	String key=((JButton)event).getName();
		switch (key) {
		
		case "buttonEasy":
			
			imagePreview.setIcon(easy);
			setImage("buttonEasy","strategyEasyClicked.png");
			break;
		case "buttonMedium":
			imagePreview.setIcon(medium);
			setImage("buttonMedium","strategyMediumClicked.png");

			break;
		case "buttonHard":
			imagePreview.setIcon(hard);
			setImage("buttonHard","strategyHardClicked.png");
			break;
		case "buttonBack":
			//setImage("buttonBack","strategyEasyClicked.png");
			break;

		}
	    }
	}
	@Override
	public void mouseExited(MouseEvent e) {
		Object event=e.getSource();
	    if(event instanceof JButton){
	    	String key=((JButton)event).getName();
			imagePreview.setIcon(empty);
		switch (key) {
		case "buttonEasy":
			setImage("buttonEasy","strategyEasy.png");
			break;
		case "buttonMedium":
			setImage("buttonMedium","strategyMedium.png");
			break;
		case "buttonHard":
			setImage("buttonHard","strategyHard.png");
			break;
		case "buttonBack":
			//setImage("buttonBack","strategyEasyClicked.png");
			break;

		}
	    }
		
	}
	@Override
	public void mousePressed(MouseEvent e) {

		Object event=e.getSource();
	    if(event instanceof JButton){
	    	String key=((JButton)event).getName();
		switch (key) {
		case "buttonEasy":
			new AePlayWave("sound/magicClick.wav").start();
			frame.getGameManager().setTypeStrategy("easy");
			frame.switchTo(new GamePanel(frame));
			break;
		case "buttonMedium":
			new AePlayWave("sound/magicClick.wav").start();
			frame.getGameManager().setTypeStrategy("medium");
			frame.switchTo(new GamePanel(frame));
			break;
		case "buttonHard":
			new AePlayWave("sound/magicClick.wav").start();
			frame.getGameManager().setTypeStrategy("hard");
			frame.switchTo(new GamePanel(frame));
			break;
		case "buttonBack":
			new AePlayWave("sound/magicClick.wav").start();
			frame.switchTo(new WorldSelectionPanel(frame));
			break;

		}
	    }
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}


