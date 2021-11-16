package Grafica;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import logicaOffline.Manager.GameManager;
import logicaOffline.utility.AePlayWave;
import Online.PrincipalOnLinePanel;


@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements MouseListener,MouseMotionListener,KeyListener{

	private Image menu;
	private MainFrame frame;

	public MenuPanel(final MainFrame mainFrame) {
		frame=mainFrame;
		this.setLayout(null);
		menu=frame.getImageProvider().getImage("ImgMenuPanel/SfondoMenu.jpg");
		this.setSize(frame.getWidth(), frame.getHeight());
		addButtons(this);
	}
	private void addButtons(MenuPanel panel)
	{
		final JButton play=new JButton();
		 play.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonPlayNoPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));
	     play.setBorderPainted(false);
	     play.setContentAreaFilled(false);
	     play.setBounds(150,125,300, 125);
	     final JButton online=new JButton();
	     online.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonOnlineNoPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));
	     online.setBorderPainted(false);
	     online.setContentAreaFilled(false);
	     online.setBounds(150,250,300, 125);
	     final JButton editor=new JButton();
	     editor.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonEditorNoPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));
	     editor.setBorderPainted(false);
	     editor.setContentAreaFilled(false);
	     editor.setBounds(150,375,300, 125);
	     final JButton exit=new JButton();
	     exit.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonExitNoPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));
	     exit.setBorderPainted(false);
	     exit.setContentAreaFilled(false);
	     exit.setBounds(150,500,300, 125);
	     
	     
	     play.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				GameManager manager=frame.getGameManager();
				manager.setGameInOffline(true);
				frame.switchTo(new WorldSelectionPanel(frame));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				new AePlayWave("sound/magicClick.wav").start();

				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				play.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonPlayNoPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				play.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonPlayPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
	     
	     editor.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				new AePlayWave("sound/magicClick.wav").start();

				frame.switchTo(new EditorPanel(frame));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {				
				editor.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonEditorNoPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				editor.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonEditorPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

	     online.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				new AePlayWave("sound/magicClick.wav").start();

                frame.switchTo(new PrincipalOnLinePanel(frame));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {		
				online.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonOnlineNoPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				online.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonOnlinePressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	     
	     exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				new AePlayWave("sound/magicClick.wav").start();

				System.exit(0);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonExitNoPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				exit.setIcon(new ImageIcon(frame.getImageProvider().getImage("ImgMenuPanel/ButtonExitPressed.png").getScaledInstance(300,125, java.awt.Image.SCALE_SMOOTH)));

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
	     
	    panel.add(play);
	    panel.add(online);
	    panel.add(editor);
	    panel.add(exit);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(menu, 0, 0, getWidth(),getHeight(), null);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent me) {


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent me) {
		
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		
		if(ke.getKeyCode()==27)
		{
			System.exit(0);
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
