package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import logicaOffline.Manager.GameManager;
import logicaOffline.utility.AePlayWave;
import logicaOffline.utility.WorldsLinker;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{


	private ImageProvider imageProvider = new ImageProvider(this);
	private GameManager gameManager;
	private JPanel contentPanel;
    private static WorldsLinker worldsLinker;
	
	private int width;
	private int height;
	private static MainFrame mainFrame=null;
	
	private MainFrame() {
		initFrame();
		contentPanel = new JPanel(new BorderLayout());
		
		contentPanel.setOpaque(false);
		add(contentPanel);
		
		worldsLinker = new WorldsLinker();
		
		gameManager = new GameManager();

       switchTo(new MenuPanel(this));

	}
	public static MainFrame getIstanceMainframe()
    {
    	if(mainFrame==null)
    	{
    		mainFrame=new MainFrame();

    		return mainFrame;
    	}
		return mainFrame;
    	
    }


	public int getWidth() {	
		return width;
	}


	public int getHeight() {
		return height;
	}


	public ImageProvider getImageProvider(){
		return imageProvider;
	}

	GameManager getGameManager()
	{
		return gameManager;
	}

	public WorldsLinker getWorldsLinker ()
	{
		return worldsLinker;
	}
	
	
	private void initFrame() {
		
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setSize(width, height); //-40 per non scendere sotto la barra di windows
		 this.setUndecorated(true);
		 setBackground(new Color(0, 255, 0, 0));
		this.setTitle("Prova icona");
		this.setResizable(false);
		this.setIconImage(ImageProvider.getInstance(this).getProgramIcon());
		
		this.setVisible(true); 
		
			 Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(imageProvider.getImage("wand.png"), new Point(0,0),"img");
			  this.setCursor (c);
			
		
	    
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}



	public void setWorldGame(String string) {
		this.gameManager.setWorld(string);
	}
	public void switchTo(final JPanel panel)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				contentPanel.removeAll();
				contentPanel.add(panel, BorderLayout.CENTER);
				contentPanel.updateUI();
				panel.requestFocus();
			}
		});
	}



	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		

		@SuppressWarnings("unused")
		MainFrame main = MainFrame.getIstanceMainframe();
		new AePlayWave("sound/magic.wav").start();

	}







}
