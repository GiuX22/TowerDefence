package Grafica;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Online.PlayerOnline;

@SuppressWarnings("serial")
public class RankingPanel extends JPanel {
 
	private ImageIcon wallpaperImage;
	private JLabel loadingLabel;
	private int width;
	private int height;
	@SuppressWarnings("rawtypes")
	private final DefaultListModel model = new DefaultListModel();
	private PlayerOnline player;
	private boolean loaded;
	private JPanel panel;
	private JFrame frame;
	
	
	public RankingPanel(PlayerOnline player) {
		this.player=player;
		loaded=false;
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setOpaque(false);
		this.setLayout(null);
		wallpaperImage = new ImageIcon("Img/loading.gif");
		this.loadingLabel = new JLabel(wallpaperImage);
		loadingLabel.setBounds(((int)width/2)-100, ((int)height/2)-100,150, 150);
		try {
			drawPanel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.add(loadingLabel);
		panel.setVisible(false);
		loadingLabel.setVisible(true);
		
		initFrame();
		downloadRanking();
	}
	
	
	
	public void drawPanel() throws IOException
	{   
		final Image showcaseWall = ImageIO.read(new File("Img/Sfondo2.jpg"));
		panel=new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(showcaseWall.getScaledInstance((int)(width/3), height-100,java.awt.Image.SCALE_SMOOTH ), 0, 0, (int)(width/3), height-100,this);
			}
		};
	
		JList list=new JList<String>(model);
		list.setFont(new Font("serif", Font.PLAIN, 24));
		list.setForeground(Color.white);
		JScrollPane scroll=new JScrollPane(list);
		list.setOpaque(false); 
		list.setBackground(new Color(0,0,0,0));
		scroll.setOpaque(false); 
		scroll.getViewport().setOpaque(false);
		scroll.setBorder(null);
	    scroll.setPreferredSize(new Dimension((int)(width/3)-250, height-110));
	    scroll.setLocation((int)(width/3), 60);
	    scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
	    scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		panel.add(scroll);
		panel.setBounds((int)(width/3),50, (int)(width/3), height-100);
		this.add(panel);
		
		JButton back=new JButton();
		back.setIcon(new ImageIcon(MainFrame.getIstanceMainframe().getImageProvider().getImage("backButton.png").getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH)));
		back.setPreferredSize(new Dimension(100, 50));
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(loaded)
				{
					MainFrame.getIstanceMainframe().setEnabled(true);
					frame.dispose();
				}
				
			}
		});
		panel.add(back);
		
	}
	
	public void initFrame()
	{
		frame=new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(width, height); 
		frame.setUndecorated(true);
		// Apply a transparent color to the background
		// This is ALL important, without this, it won't work!
		frame.setBackground(new Color(0, 255, 0, 0));
		frame.add(this);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.getIstanceMainframe().getImageProvider().getImage("wand.png"), new Point(0,0),"img");
		  frame.setCursor (c);
		frame.setVisible(true);
	}
	
	private void loadRanking()
	{
		for (String record : player.getRanking()) {
			model.addElement(record);
		}
		loaded=true;
	}
	
	private void downloadRanking()
	{
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					Thread.sleep(1000);
					while(!player.isRankingLoad()){
						Thread.sleep(400); 
					}
					loadRanking();
					loadingLabel.setVisible(false);
					panel.setVisible(true);
					repaint();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		t.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.repaint();
	}
}