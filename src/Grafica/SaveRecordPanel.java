package Grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Online.*;

public class SaveRecordPanel extends JPanel{
	private BufferedImage img = null;
	private Image name=null;
	private BufferedImage score=null;
	private JButton saveRecord;
	private JButton skip;
	private int width;
	private int height;
    private PlayerOnline player;
    private JFormattedTextField formattedTextField;
    private MainFrame frame;
    private Font font;
	
	public SaveRecordPanel()
	{
		this.setLayout(null);
		this.setOpaque(true);
		this.setVisible(true);
		frame=MainFrame.getIstanceMainframe();
		width=frame.getWidth();
		height=frame.getHeight();
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		player=PlayerOnline.getIstance();
		try {
			player.stopGame();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		//pulsante per salvare con il relativo listener
		
		addButtons();
		loadFont();
		
		try {
			img = ImageIO.read(new File("./Img/SaveRecord.png"));
			name= ImageIO.read(new File("./Img/SaveRecordName.png"));
			score=ImageIO.read(new File("./Img/SaveRecordScore.png"));
		} catch (IOException e) {}
		
		
	}
	
	
	private void loadFont()
	{
	    try {
	      font = Font.createFont(Font.TRUETYPE_FONT,new File("airstrike.ttf") );
	    } catch (Exception ex) {
	      font = new Font("serif", Font.PLAIN, 24);
	    }
	}
	
	private void addButtons()
	{
		saveRecord=new JButton("save");
		saveRecord.setIcon(new ImageIcon(frame.getImageProvider().getImage("ButtonSave.png").getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH)));
		saveRecord.setBorderPainted(false);
		saveRecord.setContentAreaFilled(false);
		add(saveRecord);
		saveRecord.setBounds((int)(width/2)-50,(int)(height/3)+150, 170, 70);
		saveRecord.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					player.writeRanking(formattedTextField.getText()+":"+Integer.toString(player.getScore()));
					player.closeRoomGame();
					MainFrame.getIstanceMainframe().switchTo(new PrincipalOnLinePanel(MainFrame.getIstanceMainframe()));
				} catch (IOException e1) {try {

					player.closeRoomGame();
				} catch (Exception e2) {
					// TODO: handle exception
				}finally{
					MainFrame.getIstanceMainframe().switchTo(new PrincipalOnLinePanel(MainFrame.getIstanceMainframe()));
				}
				}
			
			}
			
			@Override
			public void mouseExited(MouseEvent e) {	
				saveRecord.setIcon(new ImageIcon(frame.getImageProvider().getImage("ButtonSave.png").getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH)));

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				saveRecord.setIcon(new ImageIcon(frame.getImageProvider().getImage("ButtonSaveOver.png").getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH)));

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		//pulsante per tornare alla home con il relativo listener
		skip=new JButton("skip");
		skip.setContentAreaFilled(false);
		skip.setBorderPainted(false);
		skip.setIcon(new ImageIcon(frame.getImageProvider().getImage("ButtonSkip.png").getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH)));
		skip.setBounds((int)(width/2)+150, (int)(height/3)+150, 170, 70);
		add(skip);
		skip.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				try {

					player.closeRoomGame();
				} catch (Exception e2) {
					// TODO: handle exception
				}finally{
					MainFrame.getIstanceMainframe().switchTo(new PrincipalOnLinePanel(MainFrame.getIstanceMainframe()));
				}
			  
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				skip.setIcon(new ImageIcon(frame.getImageProvider().getImage("ButtonSkip.png").getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH)));

				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				skip.setIcon(new ImageIcon(frame.getImageProvider().getImage("ButtonSkipOver.png").getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH)));

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		 formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds((int)(width/4)+50, (int)(height/3)+170, 154, 28);
		formattedTextField.setBackground(new Color(171, 205, 239));
		formattedTextField.setVisible(true);
		formattedTextField.setText("inserisci nome");
		formattedTextField.setEnabled(true);
		add(formattedTextField);

		formattedTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		

	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(img, 0, 0,width,height,null);
		g.drawImage(name, (int)(width/4)+40, (int)(height/3)+120,140,40,null);
		g.drawImage(score, (int)(width/8)-100, (int)(height/6),300,100,null);
		paintScore(g);
		
	}
	
	private void paintScore(Graphics g){
		g.setFont(font.deriveFont(100f));
		g.setColor(new Color(51,0,102));
		g.drawString(Integer.toString(player.getScore()), (int)(width/8)+250, (int)(height/6)+75);
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize( (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
	      (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		SaveRecordPanel panel=new SaveRecordPanel();
		frame.add(panel);
		frame.setVisible(true);
	}
}
