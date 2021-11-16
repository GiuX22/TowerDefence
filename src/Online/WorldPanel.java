package Online;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Grafica.ImageProvider;
import Grafica.MainFrame;

public class WorldPanel extends JPanel {
	
	public WorldPanel copy=this;
	private File path;
	private ArrayList<String> listOfWorld=new ArrayList<String>();
	private BufferedImage showcaseWall;
	private PlayerOnline player;
	private String nameRoom;
	public  WorldPanel Copy=this;
	private JButton arrowRight;
	private JButton arrowLeft;
	private JButton backButton;
	private String worldSelected;
	private int indexWorld;
	private int width;
	private int height;
	private JButton world;
	
	ImageProvider imageProvider;
	protected PrincipalOnLinePanel pr;

	public void init(PlayerOnline player,String nameRoom,PrincipalOnLinePanel pr)
	{
		this.pr=pr;
		this.player=player;
		this.nameRoom=nameRoom;
	}

	public void fillList()
	{
		path = new File("FILEWORLD");
		for (int i = 0; i < path.listFiles().length; i++) {
			String nameFile=path.listFiles()[i].getName();
			String type=nameFile.substring(nameFile.length()-4,nameFile.length());
			if(type.equals(".txt"))
			{
				if(nameFile.equals("world1.txt") || nameFile.equals("world2.txt") || nameFile.equals("world3.txt"))
			listOfWorld.add(nameFile.substring(0,nameFile.length()-4));
			}
		}

		if(!listOfWorld.isEmpty())
		worldSelected=listOfWorld.get(indexWorld);
	}
	public WorldPanel()
	{
		width=MainFrame.getIstanceMainframe().getWidth();
		height=MainFrame.getIstanceMainframe().getHeight();
		imageProvider=ImageProvider.getInstance(MainFrame.getIstanceMainframe());
		this.setLayout(null);
        this.setBounds(0, 0, width, height);
        indexWorld=0;
		fillList();
		setButtonsSelection();
		setWorld();
		
		try { showcaseWall = ImageIO.read(new File("./Img/magic.jpg")); }
		catch (IOException e) {}
		
		this.setFocusable(true);
		
		
	}
	
	private void setButtonsSelection()
	{
		
		arrowRight=new JButton(new ImageIcon(imageProvider.getImage("downListOnline.png").getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH)));
		arrowRight.setOpaque(false);
		arrowRight.setContentAreaFilled(false);
		arrowRight.setBorderPainted(false);
		add(arrowRight);
		arrowRight.setBounds((int)width/3+(int)width/3 +25, (int)height/3+100,75,75);
		
		
	
		
		arrowRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  if(indexWorld<listOfWorld.size()-1)
						indexWorld++;
		                else
		                indexWorld=0;
		                
		                worldSelected=listOfWorld.get(indexWorld);
		        		world.setIcon(new ImageIcon(imageProvider.getImage("WorldPreview/"+worldSelected+".png").getScaledInstance((int)width/3,(int)height/3, java.awt.Image.SCALE_SMOOTH)));

			}
		});
		
		
		
		arrowLeft=new JButton(new ImageIcon(imageProvider.getImage("upListOnline.png").getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH)));
		arrowLeft.setOpaque(false);
		arrowLeft.setContentAreaFilled(false);
		arrowLeft.setBorderPainted(false);
		add(arrowLeft);
		arrowLeft.setBounds((int)width/3-100, (int)height/3+100, 75, 75);
		
		
		
		arrowLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                if(indexWorld>0)
				indexWorld--;
                else
                indexWorld=listOfWorld.size()-1;
                
                worldSelected=listOfWorld.get(indexWorld);
        		world.setIcon(new ImageIcon(imageProvider.getImage("WorldPreview/"+worldSelected+".png").getScaledInstance((int)width/3,(int)height/3, java.awt.Image.SCALE_SMOOTH)));

			}
		});
		
		backButton=new JButton(new ImageIcon(imageProvider.getImage("backButton.png").getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH)));
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		add(backButton);
		backButton.setBounds((int)width/2+300, (int)height/2+200,75,75);
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				copy.setVisible(false);
				pr.resetBack();
				
			}
		});
	}

	
	private void setWorld()
	{
	    world=new JButton();
		world.setBounds((int)width/3,(int)height/3,(int)width/3,(int)height/3);
		world.setContentAreaFilled(false);
		world.setBorderPainted(false);
		world.setOpaque(false);
		world.setIcon(new ImageIcon(imageProvider.getImage("WorldPreview/"+worldSelected+".png").getScaledInstance((int)width/3,(int)height/3, java.awt.Image.SCALE_SMOOTH)));

		this.add(world);
		
		world.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.openRoomGame(nameRoom, worldSelected);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
	                PrincipalOnLinePanel.resetGui();
					player.resetPlayer();
				}
				Copy.setVisible(false);
				
			}
		});
	}
	
	

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(showcaseWall, 0,0,width,height, null);
	}



}
