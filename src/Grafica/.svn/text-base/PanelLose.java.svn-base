package Grafica;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logicaOffline.Manager.GameManager;

public class PanelLose extends JPanel{
	private BufferedImage img = null;
	private JButton home;
	private JButton restart;
	private ImageProvider imageProvider;
	private MainFrame frame;
	private GameManager gameManager;
	private JLabel gif;
	private Image text;
	
	public PanelLose(GameManager manager)
	{
		this.gameManager=manager;
		this.setLayout(null);
		this.setOpaque(true);
		this.setVisible(true);
		try {
			img = ImageIO.read(new File("Img/youWin.png"));
			text=ImageIO.read(new File("./Img/youLoseText.png"));
		} catch (IOException e) {
		}
		
		frame=MainFrame.getIstanceMainframe();
		imageProvider=new ImageProvider(frame);
		home=new JButton(new ImageIcon(imageProvider.getImage("homeOnline.png").getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		home.setBounds(100, frame.getHeight()-150, 100, 100);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.setEndGame(true);
				gameManager.reset();
				frame.switchTo(new MenuPanel(frame));
			}
		});
		add(home);
		
		restart=new JButton(new ImageIcon(imageProvider.getImage("restart.png").getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		restart.setBounds(250, frame.getHeight()-150, 100, 100);
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.setEndGame(true);
				gameManager.reset();
				GameManager manager=frame.getGameManager();
				manager.setGameInOffline(true);
				frame.switchTo(new WorldSelectionPanel(frame));
			}
		});
		add(restart);
		
		

		
		gif=new JLabel(new ImageIcon("./Img/lose.gif"));
		gif.setBounds(frame.getWidth()-700,frame.getHeight()-500, 800, 450);
		this.add(gif);

		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, MainFrame.getIstanceMainframe().getWidth(),MainFrame.getIstanceMainframe().getHeight(),null);
		g.drawImage(text, (int)getWidth()/3, 50,400,200, null);
	}

}
