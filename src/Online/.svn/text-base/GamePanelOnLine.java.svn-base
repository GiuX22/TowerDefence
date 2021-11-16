package Online;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Grafica.ImageProvider;
import Grafica.MainFrame;
import Grafica.MenuPanel;
import Grafica.SaveRecordPanel;
import Grafica.Button.GamePanelButton.ButtonTower;
import Grafica.Button.GamePanelButton.HomeButton;
import Grafica.Button.GamePanelButton.ListTowerButton;
import Grafica.Button.GamePanelButton.StartButton;
import Grafica.Button.GamePanelButton.StartSpeedButton;
import Online.UtilityOnline.MyTextArea;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.utility.AePlayWave;
import logicaOffline.utility.TowerInfo;
import logicaOffline.world.Base;
import logicaOffline.world.Generator;
import logicaOffline.world.Street;

@SuppressWarnings("serial")
public class GamePanelOnLine extends JPanel implements MouseListener,MouseMotionListener{


	private volatile boolean running = true ;
	GamePanelOnLine copia=this;
	ThreadIncrementCont incrementation=new ThreadIncrementCont(copia);

	private int contAnimation=1;
	private BlockingQueue<String> monstersBuffer;
	private List<String> towersBuffer;
	private BlockingQueue<String> bulletsBuffer;
	private List<AbstractStaticObject> monsterPath;
	private PlayerOnline player;
	private Grafica.MainFrame mainframe;
	Generator objectGenerator;
	private ImageProvider imageProvider;
	private Image street;
	private Image generator;
	private Image base;

	private String[] towerAdd={"SlowTower","TowerFire","BlockTower","FrostyTower","NuclearTower","SpeedTower","BurnTower","PerfidiousTower","SuicideTower","TowerInvisible","UniversalTower"};// TODO
	private String towerToAdd="null";
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
	private ListTowerButton upListTower;
	private ListTowerButton downListTower;
	private List<ButtonTower> buttonGroup;
	private JScrollPane scrollPane;
	private JTextArea textArea;


	private boolean isGameArea;
	private String towerInfo;
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


	public GamePanelOnLine(final PlayerOnline playerOnline,final MainFrame frame)
	{
		this.setLayout(null);
		monstersBuffer=playerOnline.getMonstersBuffer();
		towersBuffer=playerOnline.getTowersBuffer();
		bulletsBuffer=playerOnline.getBulletsBuffer();
		this.mainframe=frame;
		player=playerOnline;
		imageProvider=frame.getImageProvider();
		RepainterThread repainterThread=new RepainterThread();
		repainterThread.start();
		this.setBounds(0, 0, frame.getWidth(), frame.getHeight());

		sizeXCellGame=frame.getWidth()/31;
		sizeYCellGame=frame.getHeight()/19;

		base= imageProvider.getImage("Base.png");
		generator = imageProvider.getImage("Generator.png");

		isGameArea=false;
		startClicked=true;
		gameStarted=false;
		exit=false;

		initTowerButton();
		initOtherButton();
		initChat();

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		incrementation.start();

	}

	public void calculatePrivateMessage()
	{
		for (int i = 0; i < 3 && player.getMessagePrivateChat().size()>0 ; i++) 
		{
			try {
				append(textArea, player.getMessagePrivateChat().take()+"\n");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	

	private final class RepainterThread extends Thread
	{

		private RepainterThread()
		{
			super("Repainter");
		}

		@Override
		public void run() {

			while (running)
			{
				if(player.getMessagePrivateChat().size()>0)
				{calculatePrivateMessage();}
				repaint();
				try
				{
					Thread.sleep(player.getGameSpeed());
				}
				catch (final InterruptedException e)
				{
					//do nothing
				}

			}
			if(player.isGameOver())
			{MainFrame frame=MainFrame.getIstanceMainframe();
			player.setPlayRoom(false);
			frame.switchTo(new SaveRecordPanel());}
		}
	}

	public void initTowerButton()
	{
		buttonGroup=new ArrayList<ButtonTower>();

		button1=new ButtonTower("buttonTower1",mainframe.getWidth()-1000, mainframe.getHeight()-90, 50, 80, towerAdd[0], mainframe);
		buttonGroup.add(button1);
		button2=new ButtonTower("buttonTower2",mainframe.getWidth()-900, mainframe.getHeight()-90, 50, 80,  towerAdd[1], mainframe);
		buttonGroup.add(button2);
		button3=new ButtonTower("buttonTower3",mainframe.getWidth()-800, mainframe.getHeight()-90, 50, 80, towerAdd[2], mainframe);
		buttonGroup.add(button3);
		button4=new ButtonTower("buttonTower4",mainframe.getWidth()-700, mainframe.getHeight()-90, 50, 80,  towerAdd[3], mainframe);
		buttonGroup.add(button4);

		for (ButtonTower button : buttonGroup) {
			add(button);
			button.addMouseListener(this);
		}

		upListTower=new ListTowerButton("upList",mainframe.getWidth()-1100,mainframe.getHeight()-90,50, 70, mainframe);
		upListTower.addMouseListener(this);
		add(upListTower);
		downListTower=new ListTowerButton("downList",mainframe.getWidth()-600, mainframe.getHeight()-90, 50, 70, mainframe);
		downListTower.addMouseListener(this);
		add(downListTower);
	}

	public void initOtherButton()
	{

		startButton=new StartButton("playGame",mainframe.getWidth()-340, mainframe.getHeight()-140, 100, 100, mainframe);
		startButton.addMouseListener(this);
		add(startButton);
		startSpeedButton=new StartSpeedButton("speedGame",mainframe.getWidth()-200, mainframe.getHeight()-200, 100, 100, mainframe);
		startSpeedButton.addMouseListener(this);
		add(startSpeedButton);

		home=new HomeButton("home",25,  mainframe.getHeight()-125,130, 130, mainframe);
		home.addMouseListener(this);
		add(home);
	}

	public void initChat()
	{
		textArea=new MyTextArea();
        textArea.setBounds(mainframe.getWidth()-200, 100,200,350);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N


        textArea.setFocusable(false);
        textArea.setAutoscrolls(true);
        Font font=new Font("Serif", Font.ROMAN_BASELINE, 15);
        textArea.setFont(font);
        textArea.setForeground(Color.cyan);
        textArea.setOpaque(false);

        scrollPane=new JScrollPane(textArea);
        scrollPane.setBounds(mainframe.getWidth()-200, 250,150,330);
        scrollPane.setOpaque(false);
        scrollPane.setAutoscrolls(true);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.setBorder(null);
        this.add(scrollPane);

        final JFormattedTextField insText=new JFormattedTextField();
        insText.setBounds(mainframe.getWidth()-200, 250+335, 150,30);
        insText.setBackground(new Color(171, 205, 239));
        this.add(insText);

		insText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode()==ke.VK_ENTER)
				{
					String text=insText.getText();
					if(!text.equals(""))
					{
					player.sendPrivateMessage(text);
					append(textArea,player.getNameUser()+": "+insText.getText()+"\n");
					insText.setText("");
					}
				}

			}
		});

		insText.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				insText.setText("");

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void append(JTextArea area, String newText){
		area.setText(area.getText() + newText);
	}

	void printWorld(Graphics g)
	{

		monsterPath =player.getWorld().getPathWorld();
		objectGenerator = (Generator) player.getWorld().getGenerator();

		Image img = imageProvider.getImage("Background/SfondoClassico.jpg");

		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);



		for (int i = 0; i < monsterPath.size(); i++) 
		{
			AbstractStaticObject object=monsterPath.get(i);
			if (monsterPath.get(i) instanceof Street) 
			{
				street=imageProvider.getImage("street"+((Street)object).getType()+".png");
				g.drawImage(street, (int) (monsterPath.get(i).getX()*sizeXCellGame), (int) (monsterPath.get(i).getY()*sizeYCellGame), 46, 46, null);
			}

			if (monsterPath.get(i) instanceof Base)
			{
				g.drawImage(base, (int) (monsterPath.get(i).getX()*sizeXCellGame), (int) (monsterPath.get(i).getY()*sizeYCellGame), 46, 46, null);
			}
		}

		g.drawImage(generator, (int) (objectGenerator.getX()*sizeXCellGame), (int) (objectGenerator.getY()*sizeYCellGame), 46, 46, null);

	}

	private void printTowerAddInfoPanel(Graphics g)
	{
		if(!MouseSelectTower)
		{mouseXTemp=mouseX/sizeXCellGame;
		mouseYTemp=mouseY/sizeYCellGame;}
		Graphics2D g2d = (Graphics2D)g;
		String split[]=towerInfo.split(":");
		double range=Double.parseDouble(split[5]);
		Ellipse2D.Double circle = new Ellipse2D.Double(((mouseXTemp*sizeXCellGame)+(sizeXCellGame/2)-(range*sizeXCellGame))-20,((mouseYTemp*sizeYCellGame)+(sizeYCellGame/2)-(range*sizeYCellGame))-20,sizeXCellGame*2*range+40,sizeYCellGame*2*range+40);
		g2d.setColor(new Color(0.7f, 0.2f, 0,0.2f));
		g2d.fill(circle);
		g.setColor(new Color(0,0,0));
		font=new Font("GEORGIA", 1, 35);
		g.setFont(font);
		//g.drawString(split[0], getWidth()-1000, getHeight()-90);
		font=new Font("GEORGIA", 1, 20);
		g.setFont(font);
		//g.drawString("RANGE: "+split[5], getWidth()-1000, getHeight()-70);
		//g.drawString("DAMAGE: "+split[4], getWidth()-1000, getHeight()-50);
		//g.drawString("COST: "+split[6], getWidth()-1000, getHeight()-30);

		MouseSelectTower=true;

	}

	private void printTowerGeneralInfoPanel(Graphics g){
		font=new Font("GEORGIA", 1, 35);
		g.setFont(font);
		//g.drawString(towerInfoToAdd.getType(), getWidth()-1000, getHeight()-90);
		font=new Font("GEORGIA", 1, 20);
		g.setFont(font);
		//g.drawString("RANGE: "+((Object)towerInfoToAdd.getRange()).toString(), getWidth()-1000, getHeight()-70);
		//g.drawString("DAMAGE: "+((Object)towerInfoToAdd.getDamage()).toString(), getWidth()-1000, getHeight()-50);
		//g.drawString("COST: "+((Object)towerInfoToAdd.getCost()).toString(), getWidth()-1000, getHeight()-30);
	}

	private void printCommandPanel(Graphics g)
	{

		g.drawImage(mainframe.getImageProvider().getImage("commandPanel2.png"), 0, 0, this.getWidth(),this.getHeight(), null);

	}

	public void setContAnimation() {
		if(contAnimation==5)
			contAnimation=0;
		else
			contAnimation++;

	}


	private void printMonsters(Graphics g)
	{
		String stringaLetta="";
		Image monsterImage ;

		while(!(stringaLetta.equals("endMonster")) && !player.getStopGame()&& !player.isGameOver() && player.isLoaded())
		{
			try {
				if(monstersBuffer.size()>0){
					stringaLetta=monstersBuffer.take();
					if(!stringaLetta.equals("endMonster"))
					{String [] split=new String[6];
					split=stringaLetta.split(":");
					monsterImage = imageProvider.getImage("Monsters/"+split[0]+split[5]+contAnimation+".png");

					g.drawImage(monsterImage, (int)(Double.parseDouble(split[1])*sizeXCellGame),(int)(Double.parseDouble(split[2])*sizeYCellGame),46,46,null);

					Graphics2D g2=(Graphics2D) g;
					g2.setColor(Color.green);
					int defaultLife=Integer.parseInt(split[4]);
					int life=Integer.parseInt(split[3]);
					g2.fill3DRect((int)(Double.parseDouble(split[1])*sizeXCellGame), (int) (Double.parseDouble(split[2])*sizeYCellGame)-10, 50-(((defaultLife-life)*50)/defaultLife),5, true);
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void printTowers(Graphics g)
	{
		for (String tower : towersBuffer) {
			String [] split=new String[3];
			split=tower.split(":");
			g.drawImage(imageProvider.getImage("Towers/"+split[0]+".png"), (int)(Double.parseDouble(split[1])*sizeXCellGame),(int)(Double.parseDouble(split[2])*sizeYCellGame),46,46,null);

		}

	}

	private void printBullets(Graphics g)
	{String stringaLetta="";

	while(!(stringaLetta.equals("endBullet"))&& !player.getStopGame() && !player.isGameOver() && bulletsBuffer.size()>0)
	{
		try {
			stringaLetta=bulletsBuffer.take();
			if(!stringaLetta.equals("endBullet"))
			{String [] split=new String[3];
			split=stringaLetta.split(":"); 
			g.drawImage(imageProvider.getImage("Bullets/"+split[0]+".png"), (int) ((Double.parseDouble(split[1])*sizeXCellGame)+22), (int) ((Double.parseDouble(split[2])*sizeYCellGame)+20), 14, 14, null);

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}



	@Override
	protected void paintComponent(Graphics g) {

		printWorld(g);
		printCommandPanel(g);


		g.setColor(new Color(255,255,255));
		font=new Font("GEORGIA", 1, 50);
		g.setFont(font);
		g.drawString(((Object)player.getLifePlayer()).toString(), getWidth()-150, getHeight()/5-55);
		g.drawString(((Object)(int)player.getScore()).toString(), getWidth()-150, getHeight()/5-5);
		g.drawString(((Object)(player.getLevel()+1)).toString(), getWidth()-150, getHeight()/5+50-5);
		if(towerInfo!=null)
		{
			this.printTowerAddInfoPanel(g);
		}
		if(onButton && towerInfo==null )
		{
			this.printTowerGeneralInfoPanel(g);
		}

		//stampa il tipo di torretta che si vuole posizionare in corrispondenza del mouse!
		if(!towerToAdd.equals("null") && isGameArea)
		{
			int mouseXTemp=mouseX/sizeXCellGame;
			int mouseYTemp=mouseY/sizeYCellGame;
			Graphics2D g2d = (Graphics2D)g;
			double range=towerInfoSelected.getRange();
			Ellipse2D.Double circle = new Ellipse2D.Double(((mouseXTemp*sizeXCellGame)+(sizeXCellGame/2)-(range*sizeXCellGame))-20,((mouseYTemp*sizeYCellGame)+(sizeYCellGame/2)-(range*sizeYCellGame))-20,sizeXCellGame*2*range+40,sizeYCellGame*2*range+40);
			g2d.setColor(new Color(0.7f, 0.2f, 0,0.2f));
			g2d.fill(circle);
			g.drawImage(mainframe.getImageProvider().getImage("Towers/"+towerToAdd+".png"),(mouseXTemp*sizeXCellGame),(mouseYTemp*sizeYCellGame),46,46,null);

		}

		printTowers(g);

		if(!player.getStopGame() && !player.isGameOver() && player.isPlayRoom())
		{

			printMonsters(g);
			printBullets(g);
		}
		else 
		{  
			if(player.isGameOver())
			{
				running=false;
			}
			if(player.getStopGame() && !player.isGameOver())
			{ 
				running=false;
				player.closeRoomGame();
				player.setPlayRoom(false);
				mainframe.switchTo(new PrincipalOnLinePanel(mainframe));}

		}

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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {


		if(!isGameArea)  // click area game!!
		{
			Object event=e.getSource();
			if(event instanceof JButton){
				String key=((JButton)event).getName();
				switch (key) {
				case "buttonTower1":
					new AePlayWave("sound/buttonEntered.wav").start();
					onButton=true;
					towerInfoToAdd=player.getGeneralTower((buttonGroup.get(0).getTower()));
					button1.setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'>"+(Object)towerInfoToAdd.getType()+"</b> <br>"+"<br>"+"&nbsp&nbsp Range: "+((Object)towerInfoToAdd.getRange()).toString()+"&nbsp&nbsp "+"<br>"+" &nbsp&nbsp Damage: "+((Object)towerInfoToAdd.getDamage()).toString()+"&nbsp&nbsp <br/>"+"&nbsp&nbsp Cost: "+((Object)towerInfoToAdd.getCost()).toString()+"&nbsp&nbsp </div></html>");

					break;
				case "buttonTower2":
					new AePlayWave("sound/buttonEntered.wav").start();
					onButton=true;
					towerInfoToAdd=player.getGeneralTower((buttonGroup.get(1).getTower()));
					button2.setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'>"+(Object)towerInfoToAdd.getType()+"</b> <br>"+"<br>"+"&nbsp&nbsp Range: "+((Object)towerInfoToAdd.getRange()).toString()+"&nbsp&nbsp "+"<br>"+" &nbsp&nbsp Damage: "+((Object)towerInfoToAdd.getDamage()).toString()+"&nbsp&nbsp <br/>"+"&nbsp&nbsp Cost: "+((Object)towerInfoToAdd.getCost()).toString()+"&nbsp&nbsp </div></html>");

					break;
				case "buttonTower3":
					new AePlayWave("sound/buttonEntered.wav").start();
					onButton=true;
					towerInfoToAdd=player.getGeneralTower((buttonGroup.get(2).getTower()));
					button3.setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'>"+(Object)towerInfoToAdd.getType()+"</b> <br>"+"<br>"+"&nbsp&nbsp Range: "+((Object)towerInfoToAdd.getRange()).toString()+"&nbsp&nbsp "+"<br>"+" &nbsp&nbsp Damage: "+((Object)towerInfoToAdd.getDamage()).toString()+"&nbsp&nbsp <br/>"+"&nbsp&nbsp Cost: "+((Object)towerInfoToAdd.getCost()).toString()+"&nbsp&nbsp </div></html>");

					break;
				case "buttonTower4":
					new AePlayWave("sound/buttonEntered.wav").start();
					onButton=true;
					towerInfoToAdd=player.getGeneralTower((buttonGroup.get(3).getTower()));
					button4.setToolTipText("<html><div style='font-style:italic;color:blue;'>"+"<b  style=color:red;'>"+(Object)towerInfoToAdd.getType()+"</b> <br>"+"<br>"+"&nbsp&nbsp Range: "+((Object)towerInfoToAdd.getRange()).toString()+"&nbsp&nbsp "+"<br>"+" &nbsp&nbsp Damage: "+((Object)towerInfoToAdd.getDamage()).toString()+"&nbsp&nbsp <br/>"+"&nbsp&nbsp Cost: "+((Object)towerInfoToAdd.getCost()).toString()+"&nbsp&nbsp </div></html>");

					break;
				case "upList":

					break;
				case "downList":

					break;
				case "home":
					home.setImage("homeOver.png");
					break;
				case "playGame":
					if(startClicked)
						startButton.setImage("playGameOver.png");
						else
						startButton.setImage("pauseGameOver.png");
					break;

				case "speedGame":
					startSpeedButton.setImage("speedGameOver.png");
					break;
				
				case "pauseGame":
					startButton.setImage("pauseGameOver.png");
					break;
					

				}
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
				
				case"pauseGame":
					startButton.setImage("pauseGame.png");
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
		//downButtonInfo();
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
						towerInfoSelected=player.getTowersGeneralInfo(towerToAdd);
						MouseSelectTower=false;
						break;
					case "buttonTower2":
						towerInfo=null;
						towerToAdd=buttonGroup.get(1).getTower();
						towerInfoSelected=player.getTowersGeneralInfo(towerToAdd);
						MouseSelectTower=false;
						break;
					case "buttonTower3":
						towerInfo=null;
						towerToAdd=buttonGroup.get(2).getTower();
						towerInfoSelected=player.getTowersGeneralInfo(towerToAdd);
						MouseSelectTower=false;
						break;
					case "buttonTower4":
						towerInfo=null;
						towerToAdd=buttonGroup.get(3).getTower();
						towerInfoSelected=player.getTowersGeneralInfo(towerToAdd);
						MouseSelectTower=false;
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
						exit=true;

						try {
							player.stopGame();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
							incrementation.setActive(false);
							mainframe.switchTo(new MenuPanel(mainframe));
						}


						break;
					case "playGame":
						if(startClicked)
						{startClicked=false;
						try {
							player.play();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
						}
						startButton.setImage("pauseGame.png");
						}
						else
						{   
							try {
								player.pauseGame();
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
							}
							startClicked=true;
							startButton.setImage("playGame.png");
						}
						break;
					case "speedGame":
						player.speedGame();
						startButton.setImage("playGame.png");
						startClicked=true;
						break;


					}
				}

			}

			else  //  sono in gameArea!
			{
				if(!towerToAdd.equals("null")) //ho selezionato una tower!
				{player.AddTower(towerToAdd, e.getX()/sizeXCellGame, e.getY()/sizeYCellGame);
				}
				else
				{
					idTowerSelected=player.getIdTowerSelected(e.getX()/sizeXCellGame,e.getY()/sizeYCellGame);
					if(idTowerSelected!=0){
						//					towerInfo=player.getTowersAddInfo(idTowerSelected);
						//					MouseSelectTower=false;
						//					if(towerInfo!=null)
						//					{
						//					}
						//					
					}
					else
					{towerInfo=null;
					towerInfoToAdd=null;
					}
				}

			}
		}


	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


}
