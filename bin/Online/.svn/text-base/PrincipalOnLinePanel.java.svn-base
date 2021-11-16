package Online;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;




import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;


import Grafica.ImageProvider;
import Grafica.MainFrame;
import Grafica.MenuPanel;
import Grafica.RankingPanel;
import PlugIn.AutoclosePane;

import java.awt.event.KeyEvent;

import logicaOffline.utility.AePlayWave;

@SuppressWarnings("serial")
public class PrincipalOnLinePanel extends JPanel{

	public Grafica.MainFrame frame;
	@SuppressWarnings("unused")
	private static int cont=0;
	private int contRoom=0;
	private static ImageProvider imageProvider; 
	private RankingPanel rankingPanel;
	private final JFormattedTextField formattedTextField;
	final JFormattedTextField formattedRoom;

	private static JButton openConnection;
	private static JButton openRoom;
	private static JButton startGame;
	private static JButton ranking;



	private JLayeredPane content=new JLayeredPane();
	private MenuOnlinePanel menuOnlinePanel;
	private WorldPanel worldPanel=new WorldPanel();
	private int width;
	private int height;
	private RepainterThread threadStampatore;
	static String nameUser;
	private boolean closeRepainter;


	private String nameRoom;


	private DefaultListModel<String> defaultlistUser;
	private JList<String> listUser;
	private JScrollPane scrollPaneUser;

	private DefaultListModel<String> defaultlistRoom;
	private JList<String> listRoom;
	private JScrollPane scrollPaneRoom;

	private DefaultListModel<String> defaultlistRoomA;
	private JList<String> listRoomA;
	private JScrollPane scrollPaneRoomA;

	private DefaultListModel<String> defaultlistMessage;
	private JList<String> listMessage;
	private JScrollPane scrollPaneMessage;

	private PlayerOnline playerOnline;
	protected PrincipalOnLinePanel copy=this;

	private void setButtonEnable(JButton button)
	{
		button.setEnabled(true);
	}

	public static JButton getOpenConnection() {
		return openConnection;
	}


	private final class RepainterThread extends Thread
	{

		public void run() {

			while (!closeRepainter)
			{
				try {
					sleep(200);
					calculateMessage();
					calculateRooms();
					calculateRoomsAssociated();
					calculateUser();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}   
		}
	}



	public PrincipalOnLinePanel(final Grafica.MainFrame frame)
	{		
		setLayout(null);
		this.frame=frame;
		width=frame.getWidth();
		height=frame.getHeight();
		imageProvider=ImageProvider.getInstance(frame);
		menuOnlinePanel=new MenuOnlinePanel(this.frame);
		menuOnlinePanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e))
				{
					formattedRoom.setVisible(false);
					formattedTextField.setVisible(false);
				}				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub	
			}
		});


		content.setBounds(0, 0, frame.getWidth(),frame.getHeight());
		this.add(content);

		content.add(menuOnlinePanel,new Integer(0),0);

		worldPanel.setVisible(false);
		content.add(worldPanel,new Integer(1),0);

		playerOnline=PlayerOnline.getIstance();

		closeRepainter=false;
		threadStampatore=new RepainterThread();




		final PanelSelectedWorld panelWorld=new PanelSelectedWorld();
		panelWorld.setBounds(150, 150, 500, 500);
		panelWorld.setVisible(false);

		defaultlistUser=new DefaultListModel<String>();

		listUser=new JList<String>(defaultlistUser);
		listUser.setForeground(Color.cyan);
		listUser.setFont(new Font(getFont().getName(),getFont().getStyle(),20));
		listUser.setBackground(new Color(171, 205, 239,50));
		listUser.setOpaque(false);
		listUser.setBorder(null);
		scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBorder(null);


		defaultlistRoom=new DefaultListModel<String>();
		listRoom=new JList<String>(defaultlistRoom);
		listRoom.setForeground(Color.cyan);
		listRoom.setFont(new Font(getFont().getName(),getFont().getStyle(),20));
		listRoom.setBackground(new Color(171, 205, 239,50));
		listRoom.setOpaque(false);
		scrollPaneRoom = new JScrollPane();
		scrollPaneRoom.setBorder(null);


		defaultlistRoomA=new DefaultListModel<String>();
		listRoomA=new JList<String>(defaultlistRoomA);
		listRoomA.setForeground(Color.cyan);
		listRoomA.setFont(new Font(getFont().getName(),getFont().getStyle(),20));
		listRoomA.setBackground(new Color(171, 205, 239,50));
		listRoomA.setOpaque(false);
		scrollPaneRoomA = new JScrollPane();
		scrollPaneRoomA.setBorder(null);


		defaultlistMessage=new DefaultListModel<String>();
		listMessage=new JList<String>(defaultlistMessage);
		listMessage.setFont(new Font(getFont().getName(),getFont().getStyle(),15));
		listMessage.setForeground(Color.cyan);
		listMessage.setAutoscrolls(true);
		listMessage.setOpaque(false);

		//listMessage.setBackground(new Color(171, 205, 239));

		listMessage.setBackground(new Color(133, 95, 167,50));

		scrollPaneMessage = new JScrollPane();
		scrollPaneMessage.add(listMessage);
		scrollPaneMessage.setBorder(null);

		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(290, 110, 154, 28);
		formattedTextField.setBackground(new Color(171, 205, 239));
		menuOnlinePanel.add(formattedTextField);
		formattedTextField.setVisible(false);
		formattedTextField.setHorizontalAlignment(JTextField.CENTER);

		formattedTextField.setText("inserisci user");
		formattedTextField.setEnabled(true);
		nameUser=null;


		formattedTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				formattedTextField.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});




		formattedRoom = new JFormattedTextField();
		formattedRoom.setBounds(290, 245, 154, 28);
		formattedRoom.setBackground(new Color(171, 205, 239));
		menuOnlinePanel.add(formattedRoom);
		formattedRoom.setVisible(false);
		formattedRoom.setHorizontalAlignment(JTextField.CENTER);

		formattedRoom.setText("inserisci Room");
		formattedRoom.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				formattedRoom.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		formattedRoom.setEnabled(true);



		final JFormattedTextField formattedMessage = new JFormattedTextField();
		formattedMessage.setBounds( (int)width/3+87, (int)height/2+260,(int)width/2-84,20);
		formattedMessage.setBackground(new Color(133, 95, 167));
		menuOnlinePanel.add(formattedMessage);
		formattedMessage.setVisible(true);
		formattedMessage.setEnabled(true);
		formattedMessage.setBorder(null);
		formattedMessage.setForeground(Color.cyan);
		formattedMessage.setText("inserisci un messaggio...");
		formattedMessage.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				formattedMessage.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		formattedMessage.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10)
				{
					String message=formattedMessage.getText();

					try {

						playerOnline.sendMessage(message);
					} catch (IOException | NullPointerException e1) {
						JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);

					}

					calculateMessage();
					formattedMessage.setText("");
				}
			}
		});

		ranking = new JButton(new ImageIcon(imageProvider.getImage("podio.png").getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
		ranking.setOpaque(false);
		ranking.setContentAreaFilled(false);
		ranking.setBorderPainted(false);
		ranking.setBounds(30, 30, 60,60);
		menuOnlinePanel.add(ranking);
		ranking.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					playerOnline.getRanking().clear();
					playerOnline.downLoadRanking();
					rankingPanel=new RankingPanel(playerOnline);
					MainFrame.getIstanceMainframe().setEnabled(false);
				} catch (NullPointerException | IOException e) {
					JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();

				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});




		startGame = new JButton(new ImageIcon(imageProvider.getImage("START.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH)));
		startGame.setOpaque(false);
		startGame.setContentAreaFilled(false);
		startGame.setBorderPainted(false);
		startGame.setBounds(90,340, 270, 120);
		if(playerOnline.isOpenRoomClicked())
		{
			startGame.setEnabled(true);
		}
		else
		{
			startGame.setEnabled(false);
		}
		menuOnlinePanel.add(startGame);
		startGame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				frame.switchTo(new WaitingPanel());
				try {
					new AePlayWave("sound/magicClicked.wav").start();
					closeRepainter=true;
					playerOnline.startGame();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		startGame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {}

			@Override
			public void mouseDragged(MouseEvent e) {}
		});




		openRoom = new JButton();

		if(playerOnline.isClientConnected())
			openRoom.setEnabled(true);
		else
			openRoom.setEnabled(false);

		if(!playerOnline.isOpenRoomClicked())
		{
			openRoom.setIcon(new ImageIcon(imageProvider.getImage("OPENROOM.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH)));
		}
		else
		{
			openRoom.setIcon(new ImageIcon(imageProvider.getImage("CLOSEROOM.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH)));
		}	
		openRoom.setOpaque(false);
		openRoom.setContentAreaFilled(false);
		openRoom.setBorderPainted(false);
		openRoom.setBounds(90, 210, 270, 120);
		menuOnlinePanel.add(openRoom);
		openRoom.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				if(playerOnline.isOpenRoomClicked()==false)
				{
					formattedRoom.setVisible(true);
					formattedRoom.requestFocus();
					formattedRoom.addKeyListener(new KeyListener() {

						@Override
						public void keyTyped(KeyEvent e) {
						}

						@Override
						public void keyReleased(KeyEvent e) {
							if(e.getKeyCode()==10)
							{
								if(!formattedRoom.getText().equals(""))
								{
									if(formattedRoom.getText().length()<15)
									{
										nameRoom=formattedRoom.getText();
										if(nameRoom!=null && contRoom==0)
										{
											contRoom++;
											playerOnline.setOpenRoomClicked(true);
											openRoom.setIcon((new ImageIcon(imageProvider.getImage("CLOSEROOM.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
											openRoom.setOpaque(false);
											openRoom.setContentAreaFilled(false);
											openRoom.setBorderPainted(false);

											boolean trovato=false;
											for (int i = 0; i < defaultlistRoom.size(); i++) {
												if(defaultlistRoom.get(i).equals(nameRoom))
													trovato=true;
											}
											if(!trovato)
											{
												worldPanel.init(playerOnline, nameRoom,copy);
												worldPanel.setVisible(true);
												worldPanel.requestFocus();
											}
											else
											{
												for (int j = 0; j < defaultlistRoomA.size(); j++) {
													String tmp=defaultlistRoomA.get(j);
													String[] caso=tmp.split(":");

													if(caso[0].equals(nameRoom))
													{
														try {
															playerOnline.openRoomGame(nameRoom,caso[1]);
														} catch (IOException e1) {
															JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
															resetGui();
															playerOnline.resetPlayer();
														}
													}
												}
											}
											formattedRoom.setVisible(false);

											try {
												Thread.sleep(50);
											} catch (InterruptedException e1) {
												e1.printStackTrace();
											}
											calculateRooms();
											setButtonEnable(startGame);
										}
									}
									else
									{
										AutoclosePane tmp=new AutoclosePane(1500);
										tmp.confirmOrTimeout("Hai inserito un nome troppo lungo", "Inserisci nuovamente");
									}
								}
								else
								{
									formattedRoom.setVisible(false);
								}

							}

						}
						@Override
						public void keyPressed(KeyEvent e) {
						}
					});
				}
				else{
					playerOnline.setOpenRoomClicked(false);
					contRoom=0;
					System.out.println("resetto tutto one tipe e pressed");

					openRoom.setIcon((new ImageIcon(imageProvider.getImage("OPENROOM.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
					playerOnline.closeRoomGame();
					worldPanel.setVisible(false);
					calculateRooms();
					startGame.setEnabled(false);
				}
			}
		}
				);

		openRoom.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {
				if(playerOnline.isOpenRoomClicked()==true)
					openRoom.setIcon((new ImageIcon(imageProvider.getImage("CLOSEROOM.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
				else
					openRoom.setIcon((new ImageIcon(imageProvider.getImage("OPENROOM.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				if(playerOnline.isOpenRoomClicked()==true)
					openRoom.setIcon((new ImageIcon(imageProvider.getImage("CLOSEROOMMOD.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
				else
					openRoom.setIcon((new ImageIcon(imageProvider.getImage("OPENROOMMOD.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));



			}

			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		final JButton buttonExit = new JButton((new ImageIcon(imageProvider.getImage("EXIT.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
		buttonExit.setOpaque(false);
		buttonExit.setContentAreaFilled(false);
		buttonExit.setBorderPainted(false);
		add(buttonExit);
		buttonExit.setBounds(90, 470, 270, 120);
		menuOnlinePanel.add(buttonExit);

		buttonExit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				if(playerOnline.isClientConnected())
				{	try {
					closeRepainter=true;
					playerOnline.closeConnection();
					playerOnline.resetPlayer();

					Thread.sleep(100);
				} catch (InterruptedException | IOException e1) {
					resetGui();
					playerOnline.resetPlayer();
				}

				}
				Grafica.MainFrame.getIstanceMainframe().switchTo(new MenuPanel(frame));
			}
		});

		buttonExit.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonExit.setIcon((new ImageIcon(imageProvider.getImage("EXIT.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				buttonExit.setIcon((new ImageIcon(imageProvider.getImage("EXITMOD.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
			}

			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		//		openConnection = new JButton((new ImageIcon(getButtonLoginImage().getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH))));

		openConnection = new JButton();
		playerOnline.setLoginClicked(playerOnline.isClientConnected());
		if(!playerOnline.isClientConnected())
		{openConnection.setIcon((new ImageIcon(imageProvider.getImage("LOGIN.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));}
		else
		{
			openConnection.setIcon((new ImageIcon(imageProvider.getImage("LOGOUT.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
		}
		openConnection.setOpaque(false);
		openConnection.setContentAreaFilled(false);
		openConnection.setBorderPainted(false);
		add(openConnection);
		openConnection.setBounds(90, 80, 270, 120);
		menuOnlinePanel.add(openConnection);
		formattedTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==10)//press Enter
				{
					if(!formattedTextField.getText().equals(""))
					{
					if(!playerOnline.isClientConnected() )
					{
						nameUser=formattedTextField.getText();
						formattedTextField.setVisible(false);

						try{
							playerOnline.openConnection(nameUser);
							if(playerOnline.isClientConnected())
							{

								playerOnline.setLoginClicked(true);
								openConnection.setIcon((new ImageIcon(imageProvider.getImage("LOGOUT.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
								openConnection.setOpaque(false);
								openConnection.setContentAreaFilled(false);
								openConnection.setBorderPainted(false);
								setButtonEnable(openRoom);}
							Thread.sleep(100);
						} catch (InterruptedException | IOException e1) {
							playerOnline.setLoginClicked(false);
							openRoom.setEnabled(false);

							openConnection.setIcon((new ImageIcon(imageProvider.getImage("LOGIN.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));


							if(nameUser!=null)
								JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
							nameUser=null;
						}

						calculateUser();
					}
				}
					else
					{
						formattedTextField.setVisible(false);
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		openConnection.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				if(playerOnline.isLoginClicked()==false)
				{
					formattedTextField.setVisible(true);
					formattedTextField.requestFocus();

				}

				else
				{
					playerOnline.setLoginClicked(false);
					nameUser=null;

					worldPanel.setVisible(false);
					try {
						playerOnline.closeConnection();

						Thread.sleep(400);
					} catch (InterruptedException | IOException e1) {
						JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
						resetGui();
						playerOnline.resetPlayer();
					}
					openConnection.setIcon((new ImageIcon(imageProvider.getImage("LOGIN.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
					playerOnline.getUserConnected().clear();
					openConnection.setOpaque(false);
					openConnection.setContentAreaFilled(false);
					openConnection.setBorderPainted(false);
					playerOnline.getRooms().clear();
					playerOnline.getRoomsA().clear();
					openRoom.setEnabled(false);
					playerOnline.setOpenRoomClicked(false);
					openRoom.setText("OpenRoom");
					startGame.setEnabled(false);
				}

			}
		});

		openConnection.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				if(playerOnline.isLoginClicked()==false)
					openConnection.setIcon(new ImageIcon(imageProvider.getImage("LOGINMOD.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH)));
				else
					openConnection.setIcon(new ImageIcon(imageProvider.getImage("LOGOUTMOD.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {
				if(playerOnline.isLoginClicked()==false)
					openConnection.setIcon(new ImageIcon(imageProvider.getImage("LOGIN.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH)));
				else
					openConnection.setIcon(new ImageIcon(imageProvider.getImage("LOGOUT.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH)));
			}
		});


		scrollPaneUser.setBounds(450, 70, 250, 250);
		scrollPaneUser.add(listUser);
		scrollPaneUser.setBackground(new Color(171, 205, 239,50));
		scrollPaneUser.getViewport().setBorder(null);
		scrollPaneUser.getViewport().setOpaque(false);
		scrollPaneUser.setOpaque(false);
		scrollPaneUser.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneUser.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneUser.setViewportView(listUser);
		menuOnlinePanel.add(scrollPaneUser);

		scrollPaneRoom.setBounds(850, 70, 150, 200);
		scrollPaneRoom.add(listRoom);
		scrollPaneRoom.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneRoom.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneRoom.getViewport().setOpaque(false);
		scrollPaneRoom.setOpaque(false);
		scrollPaneRoom.setViewportView(listRoom);
		listRoom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listRoom.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();

				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JList list=(JList)e.getSource();
				int index=list.locationToIndex(e.getPoint());
				String nameRoom = defaultlistRoom.get(index);
				for (int j = 0; j < defaultlistRoomA.size(); j++) {
					String tmp=defaultlistRoomA.get(j);
					String[] caso=tmp.split(":");

					if(caso[0].equals(nameRoom))
					{
						try {
							playerOnline.openRoomGame(nameRoom,caso[1]);

						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
							resetGui();
							playerOnline.resetPlayer();
						}
					}

				}
				playerOnline.setOpenRoomClicked(true);
				openRoom.setIcon((new ImageIcon(imageProvider.getImage("CLOSEROOM.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
				openRoom.setOpaque(false);
				openRoom.setContentAreaFilled(false);
				openRoom.setBorderPainted(false);
				setButtonEnable(startGame);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});





		menuOnlinePanel.add(scrollPaneRoom);

		scrollPaneRoomA.setBounds(1050, 70, 150, 200);
		scrollPaneRoomA.add(listRoomA);
		scrollPaneRoomA.getViewport().setOpaque(false);
		scrollPaneRoomA.setOpaque(false);
		scrollPaneRoomA.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneRoomA.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneRoomA.setViewportView(listRoomA);
		menuOnlinePanel.add(scrollPaneRoomA);

		scrollPaneMessage.setBounds( (int)width/3+100, (int)height/2+5,(int)width/2-110,250);
		scrollPaneMessage.getViewport().setOpaque(false);
		scrollPaneMessage.setAutoscrolls(true);
		scrollPaneMessage.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneMessage.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneMessage.setOpaque(false);
		scrollPaneMessage.setViewportView(listMessage);

		//scrollPaneMessage.setBackground(new Color(171, 205, 239));

		menuOnlinePanel.add(scrollPaneMessage);
		worldPanel.add(panelWorld);
		threadStampatore.start();
	}


	public static void resetUserLogin() {
		nameUser=null;
	}
	public void calculateUser()
	{
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DefaultListModel<String> defaultlist=new DefaultListModel<>();

		for (int i = 0; i < playerOnline.getUserConnected().size(); i++) {
			defaultlist.addElement(playerOnline.getUserConnected().get(i));
		}
		defaultlistUser=defaultlist;
		listUser.setModel(defaultlistUser);
		defaultlist=null;
	}
	
	public void resetBack()
	{
		contRoom--;
		playerOnline.setOpenRoomClicked(false);
		openRoom.setIcon((new ImageIcon(imageProvider.getImage("OPENROOM.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
		startGame.setEnabled(false);
	}

	public void calculateRooms()
	{
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		DefaultListModel<String> defaultlist=new DefaultListModel<>();

		for (int i = 0; i < playerOnline.getRooms().size(); i++) {
			defaultlist.addElement(playerOnline.getRooms().get(i));
		}	
		defaultlistRoom=defaultlist;
		listRoom.setModel(defaultlistRoom);
	}

	public void calculateRoomsAssociated()
	{
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		DefaultListModel<String> defaultlist=new DefaultListModel<>();
		for (int i = 0; i < playerOnline.getRoomsA().size(); i++) {
			defaultlist.addElement(playerOnline.getRoomsA().get(i));
		}	
		defaultlistRoomA=defaultlist;
		listRoomA.setModel(defaultlistRoomA);


	}

	public void calculateMessage()
	{
		for (int i = 0; i < 3 && playerOnline.getMessageChat().size()>0 ; i++) 
		{
			try {
				String message=playerOnline.getMessageChat().take();
				defaultlistMessage.addElement(message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		listMessage.setModel(defaultlistMessage);
	}
	public  static void resetGui()
	{
		openConnection.setIcon((new ImageIcon(imageProvider.getImage("LOGIN.png").getScaledInstance(300, 120, java.awt.Image.SCALE_SMOOTH))));
		openRoom.setEnabled(false);
		startGame.setEnabled(false);
	}

	@Override
	protected void paintComponent(Graphics g) {}



}
