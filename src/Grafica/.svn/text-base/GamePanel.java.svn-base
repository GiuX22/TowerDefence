package Grafica;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Grafica.Button.GamePanelButton.ButtonTower;
import Grafica.Button.GamePanelButton.HomeButton;
import Grafica.Button.GamePanelButton.RemoveTowerButton;
import Grafica.Button.GamePanelButton.StartButton;
import Grafica.Button.GamePanelButton.StartSpeedButton;
import Grafica.Button.GamePanelButton.ListTowerButton;
import Grafica.Button.GamePanelButton.UpdateFireButton;
import Grafica.Button.GamePanelButton.UpdateRangeButton;
import PlugIn.StringEncrypter.EncryptionException;
import PlugIn.UtilityQRCode;
import logicaOffline.Manager.GameManager;
import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Monster.MonsterNormal;
import logicaOffline.character.Tower.AbstractTower;
import logicaOffline.character.Tower.SpeedTower;
import logicaOffline.character.Tower.TowerFire;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.utility.AePlayWave;
import logicaOffline.utility.TowerInfo;
import logicaOffline.utility.WorldsLinker;
import logicaOffline.world.Base;
import logicaOffline.world.Generator;
import logicaOffline.world.Street;
import logicaOffline.world.World;

public class GamePanel extends JPanel implements MouseListener,MouseMotionListener{

	private boolean bonus=false;
	private int contQRCODE=0;
	private int contBonus=0;
	private MainFrame mainframe;
	private World world;
	private ImageProvider imageProvider;
	private List<AbstractStaticObject> monsterPath;
	private Generator objectGenerator;
	private GameManager gameManager;

	private final RepainterThread repainterThread;
	private String towerToAdd="null";

	private String[] towerAdd={"SlowTower","TowerFire","BlockTower","FrostyTower","NuclearTower","SpeedTower","BurnTower","PerfidiousTower","SuicideTower","TowerInvisible","UniversalTower"};// TODO
	private int startIndexList=0;
	private int endIndexList=3;
	private int sizeXCellGame;
	private int sizeYCellGame;

	private ButtonTower button1;
	private ButtonTower button2;
	private ButtonTower button3;
	private ButtonTower button4;
	private StartButton startButton;
	private StartSpeedButton startSpeedButton;
	private HomeButton home;
	private JButton take;
	private JButton loadQR;
	private ListTowerButton upListTower;
	private ListTowerButton downListTower;
	private UpdateFireButton updateFireButton;
	private UpdateRangeButton updateRangeButton;
	private RemoveTowerButton removeTowerButton;
	private List<ButtonTower> buttonGroup;
	private boolean isGameArea;
	private TowerInfo towerInfo;
	private int mouseX;
	private int mouseY;
	private TowerInfo towerInfoToAdd;
	private boolean MouseSelectTower=false;
	private int mouseXTemp;
	private int mouseYTemp;

	private Font font=new Font("GEORGIA", 1, 50);
	private int idTowerSelected;
	private boolean onButton=false;
	private TowerInfo towerInfoSelected;
	private boolean startClicked;
	private boolean gameStarted;
	private boolean exit;

	private Image wall;
	private Image street;
	private Image generator;
	private Image base;
	private ImageIcon loadQRIcon;
	private ImageIcon takeQRIcon;



	public GamePanel(final MainFrame mainFrame) 
	{
		this.mainframe = mainFrame;
		this.imageProvider = mainFrame.getImageProvider();
		gameManager=mainFrame.getGameManager();
		this.setLayout(null);
		try {
			gameManager.initGame();
		} catch (IOException e) {
			throw new RuntimeException("game not Intialized");
		}
		this.world =  mainframe.getGameManager().getWorld();

		repainterThread = new RepainterThread(gameManager,this);
		repainterThread.start();


		sizeXCellGame=mainFrame.getWidth()/31;
		sizeYCellGame=mainFrame.getHeight()/19;
		isGameArea=false;
		startClicked=true;
		gameStarted=false;
		exit=false;


		initWorldImage();

		generator = imageProvider.getImage("Generator.png");
		base = imageProvider.getImage("Base.png");
		loadQRIcon=new ImageIcon( imageProvider.getImage("loadQRIcon.png").getScaledInstance(145,60, java.awt.Image.SCALE_SMOOTH));

		takeQRIcon=new ImageIcon( imageProvider.getImage("takeQRIcon.png").getScaledInstance(145,60, java.awt.Image.SCALE_SMOOTH));


		buttonGroup=new ArrayList<ButtonTower>();

		button1=new ButtonTower("buttonTower1",mainframe.getWidth()-225, 300, 45, 80, towerAdd[0], mainframe);
		buttonGroup.add(button1);
		button2=new ButtonTower("buttonTower2",mainframe.getWidth()-125, 300, 45, 80,  towerAdd[1], mainframe);
		buttonGroup.add(button2);
		button3=new ButtonTower("buttonTower3",mainframe.getWidth()-225, 350, 45, 80, towerAdd[2], mainframe);
		buttonGroup.add(button3);
		button4=new ButtonTower("buttonTower4",mainframe.getWidth()-125, 350, 45, 80,  towerAdd[3], mainframe);
		buttonGroup.add(button4);

		for (ButtonTower button : buttonGroup) {
			button.createToolTip();
			add(button);
			button.addMouseListener(this);
		}

		startButton=new StartButton("playGame",mainframe.getWidth()-340, mainframe.getHeight()-125, 100, 100, mainFrame);
		startButton.addMouseListener(this);
		add(startButton);
		startSpeedButton=new StartSpeedButton("speedGame",mainframe.getWidth()-200, mainframe.getHeight()-200, 100, 100, mainFrame);
		startSpeedButton.addMouseListener(this);
		add(startSpeedButton);
		upListTower=new ListTowerButton("upList",mainframe.getWidth()-185, 250, 50, 100, mainFrame);
		upListTower.addMouseListener(this);
		add(upListTower);
		downListTower=new ListTowerButton("downList",mainframe.getWidth()-185, 400, 50, 100, mainFrame);
		downListTower.addMouseListener(this);
		add(downListTower);
		home=new HomeButton("home",25,  mainframe.getHeight()-125,130, 130, mainFrame);
		
		setQRButton();
		
		loadQR.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AePlayWave("sound/magicClicked.wav").start();

				QrCodeBonusSelection panel=new QrCodeBonusSelection(gameManager);

			}
		});
		take.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AePlayWave("sound/magicClicked.wav").start();
				new Thread(new Runnable() {

					@Override
					public void run() {

						UtilityQRCode utility;
						try {
							utility = UtilityQRCode.getIstance(gameManager);
							utility.readfromWebcam();
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();

			}
		});
		home.addMouseListener(this);
		add(home);
		updateFireButton=new UpdateFireButton("updateFireButton", mainframe.getWidth()/2-100, mainframe.getHeight()-125, 175,45, mainFrame);
		updateFireButton.addMouseListener(this);
		add(updateFireButton);
		updateRangeButton=new UpdateRangeButton("updateRangeButton", mainframe.getWidth()/2-100, mainframe.getHeight()-85, 175, 45, mainFrame);
		updateRangeButton.addMouseListener(this);
		add(updateRangeButton);
		removeTowerButton=new RemoveTowerButton("removeTowerButton", mainframe.getWidth()/2-100,mainframe.getHeight()-45, 175,45, mainFrame);
		removeTowerButton.addMouseListener(this);
		add(removeTowerButton);
		downButtonInfo();


		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}


	private void initWorldImage() {
		String[] split=gameManager.getPathWorld().split("\\.");
		String imageName=WorldsLinker.getImageName(split[0]);
		wall = imageProvider.getImage("Background/"+imageName);

	}


	//per far partire il thread
	private final class RepainterThread extends Thread
	{
		private final GameManager gameManager;
		private GamePanel gamePanel;


		private RepainterThread(final GameManager gameManager,GamePanel panel)
		{
			super("Repainter");
			this.gameManager = gameManager;
			gamePanel=panel;
		}

		@Override
		public void run() {

			while (!gameManager.isGameOver() && !exit)
			{
				if(!gameManager.isGamePause())
				{gameManager.update();}
				gamePanel.repaint();
				try
				{
					Thread.sleep(gameManager.getGAMESPEED());
				}

				catch (final InterruptedException e)
				{

				}

			}
			if(!gameManager.isWin() && gameManager.getLifePlayer()==0)
				MainFrame.getIstanceMainframe().switchTo(new PanelLose(gameManager));
			if(gameManager.isWin() && gameManager.getLifePlayer()>0)
				MainFrame.getIstanceMainframe().switchTo(new PanelWin(gameManager));

		}
	}

	void printWorld(Graphics g)
	{

		monsterPath =world.getPathWorld();
		objectGenerator = (Generator) world.getGenerator();
		int xBase=0;
		int yBase=0;

		g.drawImage(wall, 0, 0, this.getWidth() -  (int) (this.getWidth()/7) , this.getHeight() - (int) (this.getHeight()/6), null);

		for (int i = 0; i < monsterPath.size(); i++) 
		{
			AbstractStaticObject object=monsterPath.get(i);
			if ( object instanceof Street) 
			{
				street=imageProvider.getImage("street"+((Street)object).getType()+".png");
				g.drawImage(street, (int) (monsterPath.get(i).getX()*sizeXCellGame), (int) (monsterPath.get(i).getY()*sizeYCellGame), 46, 46, null);
			}

			if (object instanceof Base)
			{

				xBase = (int) monsterPath.get(i).getX();
				yBase = (int) monsterPath.get(i).getY();

			}
		}

		g.drawImage(generator, (int) (objectGenerator.getX()*sizeXCellGame)-7, (int) (objectGenerator.getY()*sizeYCellGame)-7, 60, 60, null);
		g.drawImage(base, (int) (xBase *sizeXCellGame)-17, (int) (yBase *sizeYCellGame)-17, 80, 80, null);
	}

	public void printCommandPanel(Graphics g)
	{

		g.drawImage(mainframe.getImageProvider().getImage("commandPanel2.png"), 0, 0, this.getWidth(),this.getHeight(), null);

	}

	public void printTowerAddInfoPanel(Graphics g)
	{
		if(!MouseSelectTower)
		{mouseXTemp=mouseX/sizeXCellGame;
		mouseYTemp=mouseY/sizeYCellGame;}
		Graphics2D g2d = (Graphics2D)g;
		double range=towerInfo.getRange();
		Ellipse2D.Double circle = new Ellipse2D.Double(((mouseXTemp*sizeXCellGame)+(sizeXCellGame/2)-(range*sizeXCellGame))-20,((mouseYTemp*sizeYCellGame)+(sizeYCellGame/2)-(range*sizeYCellGame))-20,sizeXCellGame*2*range+40,sizeYCellGame*2*range+40);
		g2d.setColor(new Color(0.7f, 0.2f, 0,0.2f));
		g2d.fill(circle);
		g.setColor(Color.cyan);
		font=new Font("GEORGIA", 1, 35);
		g.setFont(font);
		g.drawString(towerInfo.getType(), (int)getWidth()/6, getHeight()-90);
		font=new Font("GEORGIA", 1, 20);
		g.setFont(font);
		g.drawString("RANGE: "+((Object)towerInfo.getRange()).toString(), (int)getWidth()/6, getHeight()-70);
		g.drawString("DAMAGE: "+((Object)towerInfo.getDamage()).toString(), (int)getWidth()/6, getHeight()-50);
		g.drawString("COST: "+((Object)towerInfo.getCost()).toString(), (int)getWidth()/6, getHeight()-30);

		MouseSelectTower=true;

	}



	public void  printMonsters(Graphics g)
	{

		Image monsterImage ;

		for (int i = 0; i < gameManager.getMonstersUtility().size(); i++) 
		{	
			try {
				monsterImage = imageProvider.getImageMonster(gameManager.getMonstersUtility().get(i));


				//Aggiunto controllo per non far stare fermi i mostri quando premo pausa, altrimenti si muovevano (sempre sullo stesso posto)!
				if(!gameManager.isGamePause())
					gameManager.getMonstersUtility().get(i).setContAnimation();
				g.drawImage(monsterImage, (int) (gameManager.getMonstersUtility().get(i).getX()*sizeXCellGame), (int) (gameManager.getMonstersUtility().get(i).getY()*sizeYCellGame), 46, 46, null);
				Graphics2D g2=(Graphics2D) g;
				g2.setColor(Color.green);
				int vitaIniziale=gameManager.getMonstersUtility().get(i).getDefaultlife();
				g2.fill3DRect((int)(gameManager.getMonstersUtility().get(i).getX()*sizeXCellGame), (int) (gameManager.getMonstersUtility().get(i).getY()*sizeYCellGame)-10, 50-(((vitaIniziale-gameManager.getMonstersUtility().get(i).getLife())*50)/vitaIniziale),5, true);

			} catch (Exception e) {
				repaint();
				break;
			}

		}

	}

	public void printTowers(Graphics g)
	{
		Image towerImage ;
		for (int i = 0; i < gameManager.getTowersUtility().size(); i++) {
			towerImage = imageProvider.getImage("Towers/"+gameManager.getTowersUtility().get(i).getClass().getSimpleName()+".png");
			g.drawImage(towerImage, (int) (gameManager.getTowersUtility().get(i).getX()*sizeXCellGame), (int) (gameManager.getTowersUtility().get(i).getY()*sizeYCellGame), 46, 46, null);
		}
	}

	public void printBullets(Graphics g)
	{
		Image bulletImage ;

		for (int i = 0; i < gameManager.getBulletsUtility().size(); i++) {
			try {
				bulletImage = imageProvider.getImage("Bullets/"+gameManager.getBulletsUtility().get(i).getClass().getSimpleName()+".png");
				g.drawImage(bulletImage, (int) ((gameManager.getBulletsUtility().get(i).getX()*sizeXCellGame)+17), (int) ((gameManager.getBulletsUtility().get(i).getY()*sizeYCellGame)+17), 16, 16, null);
			}
			catch (Exception e) {
				repaint();
				break;
			}
		}

	}
	@Override
	public void paintComponent(Graphics g) {



		printWorld(g);
		printCommandPanel(g);

		g.setColor(Color.white);
		font=new Font("GEORGIA", 1, 40);
		g.setFont(font);
		g.drawString(((Object)gameManager.getLifePlayer()).toString(), getWidth()-170, 90);
		g.drawString(((Object)(int)gameManager.getScore()).toString(), getWidth()-170, 140);
		g.drawString(((Object)(gameManager.getLevel()+1)).toString(), getWidth()-170, 190);
		if(towerInfo!=null)
		{
			this.printTowerAddInfoPanel(g);
		}

		//stampa il tipo di torretta che si vuole posizionare in corrispondenza del mouse!
		if(!towerToAdd.equals("null") && isGameArea)
		{
			int mouseXTemp=mouseX/sizeXCellGame;
			int mouseYTemp=mouseY/sizeYCellGame;
			Graphics2D g2d = (Graphics2D)g;
			double range=towerInfoSelected.getRange();
			Ellipse2D.Double circle = new Ellipse2D.Double(((mouseXTemp*sizeXCellGame)+(sizeXCellGame/2)-(range*sizeXCellGame))-20,((mouseYTemp*sizeYCellGame)+(sizeYCellGame/2)-(range*sizeYCellGame))-20,sizeXCellGame*2*range+40,sizeYCellGame*2*range+40);
			//System.out.println(towerInfoToAdd.getType()+" "+towerInfoToAdd.getRange());
			g2d.setColor(new Color(0.7f, 0.2f, 0,0.2f));
			g2d.fill(circle);
			g.drawImage(mainframe.getImageProvider().getImage("Towers/"+towerToAdd+".png"),(mouseXTemp*sizeXCellGame),(mouseYTemp*sizeYCellGame),46,46,null);
		}


		printTowers(g);
		printMonsters(g);
		printBullets(g);

		if(gameManager.isBonusR() && contQRCODE<30)
		{
			g.drawImage(mainframe.getImageProvider().getImage("bonus.png"),getWidth()-900, getHeight()-120,180,80,null);

			contQRCODE++;

			if(contQRCODE==30)
			{
				contQRCODE=0;
				gameManager.setBonusR(false);
			}
		}
	}



	@Override
	public void mouseClicked(MouseEvent e) {
	}


	@Override
	public void mouseEntered(MouseEvent e) {


		
			Object event=e.getSource();
			if(event instanceof JButton){
				String key=((JButton)event).getName();
				switch (key) {
				case "buttonTower1":
					onButton=true;
					new AePlayWave("sound/buttonEntered.wav").start();
					towerInfoToAdd=gameManager.getTowersGeneralInfo(buttonGroup.get(0).getTower());
					buttonGroup.get(0).setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'>"+(Object)towerInfoToAdd.getType()+"</b> <br>"+"<br>"+"&nbsp&nbsp Range: "+((Object)towerInfoToAdd.getRange()).toString()+"&nbsp&nbsp "+"<br>"+" &nbsp&nbsp Damage: "+((Object)towerInfoToAdd.getDamage()).toString()+"&nbsp&nbsp <br/>"+"&nbsp&nbsp Cost: "+((Object)towerInfoToAdd.getCost()).toString()+"&nbsp&nbsp </div></html>");
					break;
				case "buttonTower2":
					onButton=true;
					new AePlayWave("sound/buttonEntered.wav").start();
					towerInfoToAdd=gameManager.getTowersGeneralInfo(buttonGroup.get(1).getTower());
					buttonGroup.get(1).setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'>"+(Object)towerInfoToAdd.getType()+"</b> <br>"+"<br>"+"&nbsp&nbsp Range: "+((Object)towerInfoToAdd.getRange()).toString()+"&nbsp&nbsp "+"<br>"+" &nbsp&nbsp Damage: "+((Object)towerInfoToAdd.getDamage()).toString()+"&nbsp&nbsp <br/>"+"&nbsp&nbsp Cost: "+((Object)towerInfoToAdd.getCost()).toString()+"&nbsp&nbsp </div></html>");

					break;
				case "buttonTower3":
					onButton=true;
					new AePlayWave("sound/buttonEntered.wav").start();
					towerInfoToAdd=gameManager.getTowersGeneralInfo(buttonGroup.get(2).getTower());
					buttonGroup.get(2).setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'>"+(Object)towerInfoToAdd.getType()+"</b> <br>"+"<br>"+"&nbsp&nbsp Range: "+((Object)towerInfoToAdd.getRange()).toString()+"&nbsp&nbsp "+"<br>"+" &nbsp&nbsp Damage: "+((Object)towerInfoToAdd.getDamage()).toString()+"&nbsp&nbsp <br/>"+"&nbsp&nbsp Cost: "+((Object)towerInfoToAdd.getCost()).toString()+"&nbsp&nbsp </div></html>");

					break;
				case "buttonTower4":
					onButton=true;
					new AePlayWave("sound/buttonEntered.wav").start();
					towerInfoToAdd=gameManager.getTowersGeneralInfo(buttonGroup.get(3).getTower());
					buttonGroup.get(3).setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'>"+(Object)towerInfoToAdd.getType()+"</b> <br>"+"<br>"+"&nbsp&nbsp Range: "+((Object)towerInfoToAdd.getRange()).toString()+"&nbsp&nbsp "+"<br>"+" &nbsp&nbsp Damage: "+((Object)towerInfoToAdd.getDamage()).toString()+"&nbsp&nbsp <br/>"+"&nbsp&nbsp Cost: "+((Object)towerInfoToAdd.getCost()).toString()+"&nbsp&nbsp </div></html>");

					break;
				case "upList":
					new AePlayWave("sound/buttonEnteredMenu.wav").start();
					break;
				case "downList":
					new AePlayWave("sound/buttonEnteredMenu.wav").start();
					break;
				case "home":
					new AePlayWave("sound/buttonEnteredMenu.wav").start();
					home.setImage("homeOver.png");
					break;
				case "playGame":
					new AePlayWave("sound/buttonEnteredMenu.wav").start();
					if(startClicked)
						startButton.setImage("playGameOver.png");
						else
						startButton.setImage("pauseGameOver.png");
					break;
				case "speedGame":
					new AePlayWave("sound/buttonEnteredMenu.wav").start();
					startSpeedButton.setImage("speedGameOver.png");
					break;
			
				case "updateFireButton":
					new AePlayWave("sound/buttonEnteredMenu.wav").start();
					updateFireButton.setImage("updateFireOver.png");
					if(towerInfo != null)
						updateFireButton.setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'> Update Fire Cost:"+"<b style=color:blue;>"+(Object)towerInfo.getUpdateFireCost()+"</b></b> </div></html>");

					break;
				case "updateRangeButton":
					new AePlayWave("sound/buttonEnteredMenu.wav").start();
					updateRangeButton.setImage("updateRangeOver.png");
					if(towerInfo != null)
						updateRangeButton.setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'> Update Range Cost:"+"<b style=color:blue;>"+(Object)towerInfo.getUpdateRangeCost()+"</b></b> </div></html>");

					break;
				case "removeTowerButton":
					new AePlayWave("sound/buttonEnteredMenu.wav").start();
					removeTowerButton.setImage("removeOver.png");
					break;

				}
			}
		}
	


	@Override
	public void mouseExited(MouseEvent e) {
		if(!isGameArea)  // click area game!!
		{
			Object event=e.getSource();
			if(event instanceof JButton){
				String key=((JButton)event).getName();
				switch (key) {
				case "buttonTower1":
					onButton=false;
					break;
				case "buttonTower2":
					onButton=false;
					break;
				case "buttonTower3":
					onButton=false;
					break;
				case "buttonTower4":
					onButton=false;
					break;
				case "upList":

					break;
				case "downList":

					break;
				case "home":
					home.setImage("home.png");
					break;
				case "playGame":
					if(startClicked)
					startButton.setImage("playGame.png");
					else
					startButton.setImage("pauseGame.png");
					break;
				case "speedGame":
					startSpeedButton.setImage("speedGame.png");
					break;
				case "updateFireButton":
					updateFireButton.setImage("updateFire.png");
					break;
				case "updateRangeButton":
					updateRangeButton.setImage("updateRange.png");
					break;
				case "removeTowerButton":
					removeTowerButton.setImage("remove.png");
					break;

				}
			}
		}

	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e))//click destro mouse
		{towerToAdd="null";
		towerInfo=null;
		MouseSelectTower=false;
		downButtonInfo();
		}
		else{  // click sinistro mouse


			if(!isGameArea)  // click area game!!
			{   
				Object event=e.getSource();
				if(event instanceof JButton){
					String key=((JButton)event).getName();
					switch (key) {
					case "buttonTower1":
						towerInfo=null;
						towerToAdd=buttonGroup.get(0).getTower();
						towerInfoSelected=gameManager.getTowersGeneralInfo(towerToAdd);
						MouseSelectTower=false;
						downButtonInfo();
						break;
					case "buttonTower2":
						towerInfo=null;
						towerToAdd=buttonGroup.get(1).getTower();
						towerInfoSelected=gameManager.getTowersGeneralInfo(towerToAdd);
						MouseSelectTower=false;
						downButtonInfo();
						break;
					case "buttonTower3":
						towerInfo=null;
						towerToAdd=buttonGroup.get(2).getTower();
						towerInfoSelected=gameManager.getTowersGeneralInfo(towerToAdd);
						MouseSelectTower=false;
						downButtonInfo();
						break;
					case "buttonTower4":
						towerInfo=null;
						towerToAdd=buttonGroup.get(3).getTower();
						towerInfoSelected=gameManager.getTowersGeneralInfo(towerToAdd);
						MouseSelectTower=false;
						downButtonInfo();
						break;
					case "upList":
						if(startIndexList>0)
						{
							startIndexList--;
							endIndexList--;
							for (int i = startIndexList, j=0; i <=endIndexList && j<4; i++,j++) {
								buttonGroup.get(j).setTower(towerAdd[i]);
								buttonGroup.get(j).setImage(towerAdd[i]);
							}

						}
						break;
					case "downList":
						if(endIndexList<towerAdd.length-1)
						{
							startIndexList++;
							endIndexList++;
							for (int i = startIndexList, j=0; i <=endIndexList && j<4; i++,j++) {
								buttonGroup.get(j).setTower(towerAdd[i]);
								buttonGroup.get(j).setImage(towerAdd[i]);
							}

						}
						break;
					case "home":
						new AePlayWave("sound/magicClick.wav").start();
						exit=true;
						gameManager.setEndGame(true);
						gameManager.reset();
						mainframe.switchTo(new MenuPanel(mainframe));
						break;


					case "playGame":
						new AePlayWave("sound/magicClick.wav").start();
						if(startClicked)
						{startClicked=false;
						gameManager.setGamePause(false);
						gameManager.setGAMESPEED(50);
						if(!gameStarted)
						{gameManager.startGame();
						gameStarted=true;}
						startButton.setImage("pauseGame.png");
						}
						else
						{   
							gameManager.setGamePause(true);
							startClicked=true;
							startButton.setImage("playGame.png");
						}
						break;
					case "speedGame":
						gameManager.setGAMESPEED(25);
						gameManager.setGamePause(false);
						if(!gameStarted)
						{gameManager.startGame();
						gameStarted=true;}
						startButton.setImage("playGame.png");
						startClicked=true;
						break;
					case "updateFireButton":
						new AePlayWave("sound/magicClick.wav").start();

						if(towerInfo!=null)
						{
							if(!towerInfo.isUpdateFireTower())
							{
								gameManager.upgradeTower(idTowerSelected, "fire");
								updateFireButton.setEnabled(false);
							}	
						}

						break;
					case "updateRangeButton":
						new AePlayWave("sound/magicClicked.wav").start();

						if(towerInfo!=null){
							if(!towerInfo.isUpdateRangeTower())
							{gameManager.upgradeTower(idTowerSelected, "range");
							updateRangeButton.setEnabled(false);
							}
						}
						break;
					case "removeTowerButton":
						new AePlayWave("sound/magicClick.wav").start();

						gameManager.removeTower(idTowerSelected);
						towerInfo=null;
						downButtonInfo();
						break;

					}
				}

			}

			else  //  sono in gameArea!
			{
				if(!towerToAdd.equals("null")) //ho selezionato una tower!
				{gameManager.addTower(towerToAdd, e.getX()/sizeXCellGame, e.getY()/sizeYCellGame);
				}
				else
				{
					idTowerSelected=gameManager.getIdTowerSelected(e.getX()/sizeXCellGame,e.getY()/sizeYCellGame);
					if(idTowerSelected!=0)
					{towerInfo=gameManager.getTowersAddInfo(idTowerSelected);
					MouseSelectTower=false;
					upButtonInfo();
					updateFireButton.setEnabled(true);
					updateRangeButton.setEnabled(true);
					if(towerInfo!=null)
					{if(towerInfo.isUpdateFireTower())
					{
						updateFireButton.setEnabled(false);
					}
					if(towerInfo.isUpdateRangeTower())
					{
						updateRangeButton.setEnabled(false);
					}
					}
					}
					else
					{towerInfo=null;
					towerInfoToAdd=null;
					downButtonInfo();
					}
				}

			}
		}

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
		mouseX=me.getX();
		mouseY=me.getY();


		if(me.getX()>=sizeXCellGame*25)
		{
			isGameArea=false;
		}
		if(me.getY()>=sizeYCellGame*15)
		{
			isGameArea=false;
		}
		if(me.getX()<sizeXCellGame*25 && me.getY()<sizeYCellGame*15)
		{isGameArea=true;}



	}

	private void upButtonInfo()
	{
		updateFireButton.setVisible(true);
		updateRangeButton.setVisible(true);
		removeTowerButton.setVisible(true);
	}
	private void downButtonInfo()
	{
		updateFireButton.setVisible(false);
		updateRangeButton.setVisible(false);
		removeTowerButton.setVisible(false);
	}
	
	private void setQRButton()
	{
		take=new JButton("TakePhoto");
		take.setIcon(takeQRIcon);
		take.setBorderPainted(false);
		take.setContentAreaFilled(false);
		loadQR=new JButton("Load QR");
		loadQR.setIcon(loadQRIcon);
		loadQR.setBorderPainted(false);
		loadQR.setContentAreaFilled(false);
		take.setBounds(750, mainframe.getHeight()-125,150,50);
		loadQR.setBounds(750,mainframe.getHeight()-65,150,50);

		
		take.addMouseListener(new MouseListener() {
			
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
				takeQRIcon.setImage(imageProvider.getImage("takeQRIcon.png").getScaledInstance(145,60, java.awt.Image.SCALE_SMOOTH));

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				takeQRIcon.setImage(imageProvider.getImage("takeQRIconOver.png").getScaledInstance(145,60, java.awt.Image.SCALE_SMOOTH));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		loadQR.addMouseListener(new MouseListener() {
			
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
				loadQRIcon.setImage(imageProvider.getImage("loadQRIcon.png").getScaledInstance(145,60, java.awt.Image.SCALE_SMOOTH));

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				new AePlayWave("sound/buttonEnteredMenu.wav").start();
				loadQRIcon.setImage(imageProvider.getImage("loadQRIconOver.png").getScaledInstance(145,60, java.awt.Image.SCALE_SMOOTH));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		add(take);
		add(loadQR);
	}
}

