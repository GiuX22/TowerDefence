package Online;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JOptionPane;

import logicaOffline.utility.TowerInfo;
import logicaOffline.world.World;
import logicaOffline.world.WorldManager;
import Online.UtilityOnline.PlayerThreadListener;
//import Online.UtilityOnline.TestConnectionThread;






public class PlayerOnline {


	private Socket s;
	private OutputStream os;
	private BufferedWriter outputStream;
	private InputStream is;
	private BufferedReader inputFromServer;
	private String user;
	private boolean clientConnected;
	private String nameRoom;
	private World world;
	@SuppressWarnings("unused")
	private String worldSelected;
	private WorldManager worldManager;
	private List<TowerInfo> TowersGeneralInfo;
	private boolean stopGame;
	private boolean playRoom;
	private  boolean loginClicked;
	private boolean rankingLoad;

	private boolean OpenRoomClicked;
	private ArrayList<TowerInfo> generalInfo;


	private BlockingQueue<String> monstersBuffer;
	private List<String> towersBuffer;
	private BlockingQueue<String> bulletsBuffer;
	private BlockingQueue<String> messageChat;
	private BlockingQueue<String> messagePrivateChat;
	private List<String> towerAddInfo;
	private List<String> userConnected;
	private List<String> rooms;
	public List<String> associatedRoomClient;
	private List<String> ranking;


	private long gameSpeed;
	private int score;
	private boolean isLevelOver;
	private boolean isGameOver;

	private int lifePlayer;
	private int level;

	private boolean closeConnection;
	private boolean loaded;
	private static PlayerOnline player=null;

	public static PlayerOnline getIstance()
	{
		if(player==null)
		{
			return player=new PlayerOnline();
		}
		return player;
	}


	private PlayerOnline() {
		generalInfo=new ArrayList<>();
		loginClicked=false;
		OpenRoomClicked=false;
		closeConnection=false;
		clientConnected=false;
		rankingLoad=false;
		user=null;
		playRoom=false;
		monstersBuffer=new ArrayBlockingQueue<String>(100);
		towersBuffer=new ArrayList<String>();
		bulletsBuffer=new ArrayBlockingQueue<String>(100);
		messageChat=new ArrayBlockingQueue<String>(20);
		messagePrivateChat=new ArrayBlockingQueue<String>(20);
		towerAddInfo=new ArrayList<String>();
		userConnected=new ArrayList<String>();
		rooms=new ArrayList<String>();
		ranking=new ArrayList<String>();
		stopGame=false;
		associatedRoomClient=new ArrayList<String>();
		worldManager=new WorldManager();
		TowersGeneralInfo=new ArrayList<TowerInfo>();
		initTowersGeneralInfo();
	}

	public World getWorld() {
		return world;
	}
	private void setWorld(String world)
	{
		try {
			this.world=worldManager.getNextWorld(world+".txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addToGeneralInfo(TowerInfo tmp){
		generalInfo.add(tmp);
	}
	
	public TowerInfo getGeneralTower(String tower) {
		for (TowerInfo towerInfo : generalInfo) {
			if(towerInfo.getType().equals(tower))return towerInfo;
		}
		return null;
	}


	public String readFromServer() throws ClassNotFoundException, IOException
	{

		return inputFromServer.readLine();
	}


	public void openConnection(String User) throws UnknownHostException, IOException
	{


		if(!clientConnected)
		{
			s=new Socket("localhost",2002);
			os=s.getOutputStream();
			outputStream=new BufferedWriter(new OutputStreamWriter(os));
			is=s.getInputStream();
			inputFromServer=new BufferedReader(new InputStreamReader(is));


			user=User;

			outputStream.write("User<:>"+User+"\n");
			outputStream.flush();
			String read=inputFromServer.readLine();

			if(read.equals("Connected"))
			{clientConnected=true;
			closeConnection=false;
			this.start();
			}
			else if(read.equals("Error>user"))
			{
				JOptionPane.showMessageDialog(null, "UserName already exist!", "ERRORE",  JOptionPane.ERROR_MESSAGE);
				setLoginClicked(false);
				this.setNameUser(null);
				clientConnected=false;
			}
		}

	}

	public boolean closeConnection() throws IOException
	{


		if(user!=null)
		{outputStream.write(("CloseConnection<:>"+user+"\n"));}
		else
		{outputStream.write(("CloseConnection<:>#Close#\n"));}
		outputStream.flush();
		closeConnection=true;
		clientConnected=false;
		outputStream.close();
		os.close();
		s.close();


		return true;

	}

	public void refreshListUser()
	{

		String read="read";
		userConnected.clear();
		while(!read.equals("ListUser:end"))
		{ try {
			read=inputFromServer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(!read.equals("ListUser:end"))
		{userConnected.add(read);
		}
		}
	}

	public void refreshListRoomsAssociated()
	{

		String read="read";
		associatedRoomClient.clear();
		while(!read.equals("ListRoomAssociated:end"))
		{
			try {
				read=inputFromServer.readLine();
				if(!read.equals("ListRoomAssociated:end"))
				{
					associatedRoomClient.add(read);
				}

			} catch (Exception e) {
			}

		}
	}

	public void refreshListRooms()
	{

		String read="read";
		rooms.clear();
		while(!read.equals("ListRoom:end"))
		{ try {
			read=inputFromServer.readLine();
			if(!read.equals("ListRoom:end"))
			{
				rooms.add(read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		}
	}

	public List<String> getRooms() {
		return rooms;
	}
	public List<String> getRoomsA()
	{
		return associatedRoomClient;
	}

	public void startGame() throws IOException
	{
		outputStream.write("Start<:>start\n");
		outputStream.flush();
	}

	public void play() throws IOException
	{
		outputStream.write("Play<:>"+nameRoom+"\n");
		outputStream.flush();
	}
	public void stopGame() throws IOException
	{
		outputStream.write("Stop<:>"+nameRoom+"\n");
		outputStream.flush();

	}
	public void pauseGame() throws IOException
	{
		outputStream.write("Pause<:>"+nameRoom+"\n");
		outputStream.flush();

	}

	public void speedGame()
	{
		try {
			outputStream.write("Speed<:>"+nameRoom+"\n");
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openRoomGame(String nameRoom, String nameWorld) throws IOException
	{

		this.nameRoom=nameRoom;
		this.resetStructure();
		outputStream.write("Room<:>"+nameRoom+"<:>"+nameWorld+"\n");
		outputStream.flush();
		this.stopGame=false;

		this.setWorld(nameWorld);

	}

	public void closeRoomGame()
	{
		try {

			outputStream.write("CloseRoom<:>"+nameRoom+"<:>"+user+"\n");
			outputStream.flush();
			OpenRoomClicked=false;
		} catch (IOException e) {
		}
	}

	public void AddTower(String typeTower,int x,int y)
	{
		try {
			outputStream.write("AddTower<:>"+nameRoom+"<:>"+typeTower+"\n");
			outputStream.flush();
			outputStream.write(x);
			outputStream.flush();
			outputStream.write(y);
			outputStream.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String text) throws IOException
	{

		outputStream.write("Chat<:>"+user+": "+text+"\n");
		outputStream.flush();
	}

	public void sendPrivateMessage(String text)
	{
		try {
			outputStream.write("PrivateChat<:>"+nameRoom+"<:>"+user+"<:>"+text+"\n");
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void downLoadRanking() throws IOException
	{
		outputStream.write("Ranking<:>read\n");
		outputStream.flush();
	}
	public void writeRanking(String record) throws IOException
	{
		outputStream.write("Ranking<:>write<:>"+record+"\n");
		outputStream.flush();
	}


	public void start() {


		PlayerThreadListener listen=new PlayerThreadListener(this);
		listen.start();
	}


	public BlockingQueue<String> getMonstersBuffer() {
		return monstersBuffer;
	}

	public List<String> getTowersBuffer() {
		return towersBuffer;
	}

	public BlockingQueue<String> getBulletsBuffer() {
		return bulletsBuffer;
	}

	public BlockingQueue<String> getMessageChat() {
		return messageChat;
	}
	public BlockingQueue<String> getMessagePrivateChat()
	{
		return messagePrivateChat;
	}

	public List<String> getRanking() {
		return ranking;
	}


	public List<String> getUserConnected() {
		return userConnected;


	}

	public long getGameSpeed() {
		return gameSpeed;
	}

	public int getScore() {
		return score;
	}
	public String getNameUser() {
		return user;
	}


	public void setNameUser(String nameUser) {
		this.user = nameUser;
	}


	public int getLevel() {
		return level;
	}
	public boolean getStopGame()
	{return stopGame;}

	public void setLevel(int level) {
		this.level = level;
	}


	public List<String> getTowerAddInfo() {
		return towerAddInfo;
	}

	public boolean isLevelOver() {
		return isLevelOver;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameSpeed(long gameSpeed) {
		this.gameSpeed = gameSpeed;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setLevelOver(boolean isLevelOver) {
		this.isLevelOver = isLevelOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public void setStopGame(boolean state)
	{
		stopGame=state;
	}

	public boolean isCloseConnection() {
		return closeConnection;
	}



	public boolean isLoginClicked() {
		return loginClicked;
	}


	public void setLoginClicked(boolean login) {
		loginClicked = login;
	}


	public  boolean isOpenRoomClicked() {
		return OpenRoomClicked;
	}


	public void setOpenRoomClicked(boolean openRoom) {
		OpenRoomClicked = openRoom;
	}


	public boolean isPlayRoom() {
		return playRoom;
	}


	public boolean isClientConnected() {
		return clientConnected;
	}


	public boolean isRankingLoad() {
		return rankingLoad;
	}


	public void setRankingLoad(boolean rankingLoad) {
		this.rankingLoad = rankingLoad;
	}


	public void setPlayRoom(boolean playRoom) {
		this.playRoom = playRoom;
	}


	public int getLifePlayer() {
		return lifePlayer;
	}

	public void setLifePlayer(int lifePlayer) {
		this.lifePlayer = lifePlayer;
	}

	public int getIdTowerSelected(int x, int y)
	{
		for (int i = 0; i < towerAddInfo.size(); i++) {
			String split[]=towerAddInfo.get(i).split(":");
			if(Integer.parseInt(split[1])==x && Integer.parseInt(split[2])==y){return Integer.parseInt(split[3]);}
		}
		return 0;
	}
	public String getTowersAddInfo(int idTower) {
		for (String towerInfo : towerAddInfo) {
			String split[]=towerInfo.split(towerInfo);
			int i=Integer.parseInt(split[3]);
			if(i ==idTower) return towerInfo;
		}
		return null;
	}

	public TowerInfo getTowersGeneralInfo(String tower) {
		for (TowerInfo towerInfo : TowersGeneralInfo) {
			if(towerInfo.getType().equals(tower))return towerInfo;
		}
		return null;
	}

	public void initTowersGeneralInfo()
	{
		TowersGeneralInfo.add(new TowerInfo("BlockTower", 2.0,600,5));
		TowersGeneralInfo.add(new TowerInfo("TowerFire", 2.0,400,10));
		TowersGeneralInfo.add(new TowerInfo("BurnTower", 5.0,1200,300));
		TowersGeneralInfo.add(new TowerInfo("FrostyTower", 3.5,1000,210));
		TowersGeneralInfo.add(new TowerInfo("NuclearTower", 3.0,700,50));
		TowersGeneralInfo.add(new TowerInfo("PerfidiousTower", 3.0,1000,150));
		TowersGeneralInfo.add(new TowerInfo("SlowTower", 1.0,500,7));
		TowersGeneralInfo.add(new TowerInfo("SpeedTower", 1.8,600,15));
		TowersGeneralInfo.add(new TowerInfo("SuicideTower", 7.0,4500,500));
		TowersGeneralInfo.add(new TowerInfo("TowerInvisible", 2.0,800,50));
		TowersGeneralInfo.add(new TowerInfo("UniversalTower", 2.0,900,50));

	}


	public void resetPlayer()
	{
		user=null;
		clientConnected=false;
		closeConnection=true;
		OpenRoomClicked=false;
		loginClicked=false;
		loaded=false;
		messageChat.clear();
		messagePrivateChat.clear();
		userConnected.clear();
		this.associatedRoomClient.clear();
		rooms.clear();
	}

	public void resetStructure()
	{
		towerAddInfo.clear();
		towersBuffer.clear();
		bulletsBuffer.clear();
		monstersBuffer.clear();
		messagePrivateChat.clear();
		loaded=false;
	}

    public void loaded()
    {loaded=true;}
    
    public boolean isLoaded()
    {return loaded;}
}





