package logicaOffline.utility;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import Grafica.EditorPanel.ItemsMenu;
import Grafica.MainFrame;
import Grafica.Button.EditorPanelButton.DirectionalButton;
import Online.PlayerOnline;

public class BackgroudSelector extends JPanel{




	private int width;
	private int height;


	private JPanel bgPanel;

	private JPanel centerPanel;
	JLabel currentBackground;
	private JPanel bottomPanel;

	private JPanel rightPanel;
	private DirectionalButton rightButton;

	private JPanel leftPanel;
	private DirectionalButton leftButton;

	private JButton buttonOk;
	private JButton	buttonExit;
	private JFrame frame;
	int frameWidth ;
	int frameHeight ;
	private MainFrame oldFrame;
	private ItemsMenu itemsMenu;
	private JLabel title;

	List <Image> backgrounds;
	String [] backgroundNames;
	private int imageIndex;
	


	public BackgroudSelector(final MainFrame oldFrame, final ItemsMenu itemsMenu) throws IOException  {


		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		this.oldFrame = oldFrame;
		this.itemsMenu = itemsMenu;
		
		initFrame();
		setOpaque(false);
		this.setLayout(new BorderLayout());

		final Image background = ImageIO.read(new File("Img/SfondoBG.png"));

		File file = new File("./Img/Background/");
		String [] bgList = file.list(); 
		
		backgrounds = new ArrayList<Image>();
		backgroundNames = new String [bgList.length];
		Image tempImage;
		String tempString;
		int cont = 0;
		String path = "";
		for (int i = 0; i < bgList.length; i++) {
			tempString = bgList[i];
			tempString.toLowerCase();
			if(tempString.endsWith(".jpg"))
			{

				path = "./Img/Background/"+ bgList[i];
				tempImage = ImageIO.read(new File(path));

				backgroundNames[cont] = bgList[i];
				backgrounds.add(tempImage);
				
				cont++;
			}

		}


		title = new JLabel("<html><u>Choose Background</u></html>");
		title.setForeground(Color.white);
		title.setHorizontalAlignment( JLabel.CENTER );
		title.setFont(new Font("Comic Sans MS", Font.ITALIC, 30));

		bgPanel=new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(background, 0, 0 , width, height, null);
			}
		};
		
		centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		imageIndex = 0;
		currentBackground = new JLabel();
		currentBackground.setIcon(new ImageIcon(backgrounds.get(imageIndex).getScaledInstance((frameWidth*80)/100,(frameHeight*80)/100, java.awt.Image.SCALE_SMOOTH)));
		centerPanel.add(currentBackground);


		bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);

		buttonOk = new JButton();
		buttonOk.setBorderPainted(false);
		buttonOk.setContentAreaFilled(false);
		buttonOk.setFocusPainted(false);
		buttonOk.setIcon(new ImageIcon(oldFrame.getImageProvider().getImage("OkButtonNoPressedBG.png").getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH)));
		buttonOk.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
				itemsMenu.bgIsValid(true);
				itemsMenu.setBackgroundImage(backgrounds.get(imageIndex),backgroundNames[imageIndex]);
				oldFrame.setEnabled(true);
				frame.dispose();

			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonOk.setIcon(new ImageIcon(oldFrame.getImageProvider().getImage("OkButtonNoPressedBG.png").getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonOk.setIcon(new ImageIcon(oldFrame.getImageProvider().getImage("OkButtonPressedBG.png").getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});


		buttonExit = new JButton();
		buttonExit.setBorderPainted(false);
		buttonExit.setContentAreaFilled(false);
		buttonExit.setFocusPainted(false);
		buttonExit.setIcon(new ImageIcon(oldFrame.getImageProvider().getImage("ExitButtonNoPressedBG.png").getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH)));
		buttonExit.addMouseListener(new  MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
				itemsMenu.bgIsValid(false);
				itemsMenu.setBackgroundImage(backgrounds.get(imageIndex),backgroundNames[imageIndex]);

				
				oldFrame.setEnabled(true);
				frame.dispose();

			}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {
				buttonExit.setIcon(new ImageIcon(oldFrame.getImageProvider().getImage("ExitButtonNoPressedBG.png").getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH)));

			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				buttonExit.setIcon(new ImageIcon(oldFrame.getImageProvider().getImage("ExitButtonPressedBG.png").getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH)));

			}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});


		bottomPanel.add(buttonOk);
		bottomPanel.add(buttonExit);
		


		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setOpaque(false);
		rightButton = new DirectionalButton("right", 50, 50, oldFrame);
		rightPanel.add(rightButton);
		rightButton.setFocusPainted(false);

		rightButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(imageIndex == backgrounds.size()-1)
				{
					imageIndex = 0;
					currentBackground.setIcon(new ImageIcon(backgrounds.get(imageIndex).getScaledInstance((frameWidth*80)/100,(frameHeight*80)/100, java.awt.Image.SCALE_SMOOTH)));

				}
				else
				{
					imageIndex++;
					currentBackground.setIcon(new ImageIcon(backgrounds.get(imageIndex).getScaledInstance((frameWidth*80)/100,(frameHeight*80)/100, java.awt.Image.SCALE_SMOOTH)));

				}

			}
		});


		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setOpaque(false);
		leftButton = new DirectionalButton("left", 50, 50, oldFrame);
		leftPanel.add(leftButton);
		leftButton.setFocusPainted(false);

		leftButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(imageIndex == 0)
				{
					imageIndex = backgrounds.size()-1;
					currentBackground.setIcon(new ImageIcon(backgrounds.get(imageIndex).getScaledInstance((frameWidth*80)/100,(frameHeight*80)/100, java.awt.Image.SCALE_SMOOTH)));

				}
				else
				{
					imageIndex--;
					currentBackground.setIcon(new ImageIcon(backgrounds.get(imageIndex).getScaledInstance((frameWidth*80)/100,(frameHeight*80)/100, java.awt.Image.SCALE_SMOOTH)));

				}

			}
		});


		bgPanel.setLayout(new BorderLayout());

		bgPanel.add(title, BorderLayout.NORTH);
		bgPanel.add(centerPanel,BorderLayout.CENTER);
		bgPanel.add(bottomPanel, BorderLayout.SOUTH);
		bgPanel.add(rightPanel, BorderLayout.EAST);
		bgPanel.add(leftPanel,BorderLayout.WEST);
		frame.add(bgPanel);


	}


	private void initFrame() {

		frameWidth = 700;
		frameHeight = 500;

		frame=new JFrame();
		frame.setSize(frameWidth, frameHeight); 
		frame.setLocation(new Point((int) (width/2)- frameWidth/2, (int) (height/2)- frameHeight/2));
		frame.setUndecorated(true);
		frame.setLayout(new BorderLayout());
		frame.setBackground(new Color(0, 255, 0, 0));
		frame.add(this);
		frame.setVisible(true);

	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);


	}

}
