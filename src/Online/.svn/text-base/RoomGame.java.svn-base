package Online;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.text.TabableView;

import logicaOffline.Manager.GameManager;
import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Tower.AbstractTower;
import logicaOffline.utility.TowerInfo;


public class RoomGame {

	private Connection client1;
	private Connection client2;
	private GameManager gameManager;
	private String id;
	private boolean roomClosed;
	private int sizeTowerAdd=0;
	private boolean play;
	private boolean stopGame;
	private String world;
	private Lock lock;
	private boolean addTower;



	public RoomGame(String name,String world) {
		client1=null;
		client2=null;
		id=name;
		roomClosed=false;
		play=false;
		stopGame=false;
		addTower=false;
		this.world=world;
		lock=new ReentrantLock();

	}
	public boolean isRoomClosed() {
		return roomClosed;
	}

	public void setRoomClosed(boolean roomClosed) {
		this.roomClosed = roomClosed;
	}

	public Connection getClient1() {
		return client1;
	}

	public void deleteClient2()
	{
		client2=null;
	}

	// funzione setClient generale.
	public void setClient(Connection client)
	{
		if(client1==null)
		{
			client1=client;
		}
		else
			client2=client;
	}




	public void setClient1(Connection client1) {
		this.client1 = client1;
	}

	public Connection getClient2() {
		return client2;
	}

	public void setClient2(Connection client2) {
		this.client2 = client2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPause(boolean pause)
	{
		gameManager.setGamePause(pause);
	}

	public void play()
	{

		if(!play)
		{
			gameManager.startGame();
			play=true;
		}
	}

	public boolean getState()
	{
		return play;
	}
	public void stopGame()
	{
		stopGame=true;
		client1.state=false;
		client2.state=false;
	}
	public void setGameSpeed(long speed)
	{
		gameManager.setGAMESPEED(speed);
	}

	public void enableAddTower()
	{
		addTower=true;
	}
	public void startRoom()
	{


		Thread t=new Thread(new Runnable() {

			boolean gameOver=false;
			@Override
			public void run() {
				try {
					gameManager=new GameManager();
					gameManager.setStrategy("endLess");
					gameManager.setWorld(world+".txt");
					gameManager.initGame();
					for (int i = 0; i < gameManager.getTowersGeneralInfo().size(); i++) {
						TowerInfo tmp=gameManager.getTowersGeneralInfo().get(i);
						sendPackGame("TowersGeneral>"+tmp.getType()+":"+tmp.getRange()+":"+tmp.getCost()+":"+tmp.getDamage());
						System.out.println("roomGame invia "+"TowersGeneral>"+tmp.getType()+":"+tmp.getRange()+":"+tmp.getCost()+":"+tmp.getDamage());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				while( !stopGame && !roomClosed)
				{

					try {



						long gamespeed=gameManager.getGAMESPEED();
						int towerAdds=	gameManager.getTowersAddInfo().size();
						if(sizeTowerAdd!=towerAdds)
						{
							sendPackGame("StartTowerInfo>");
							for (TowerInfo towerInfo : gameManager.getTowersAddInfo()) {
								sendPackGame("TowersAddInfo>"+towerInfo.getInfo());
							}
							sizeTowerAdd=towerAdds;
						}
						sendPackGame("GameOver>"+gameManager.isGameOver());
						sendPackGame("GameSpeed>"+gamespeed);
						sendPackGame("Score>"+gameManager.getWorld().getScore());
						sendPackGame("Level>"+gameManager.getLevel());
						sendPackGame("LevelOver>"+gameManager.isLevelOver());
						sendPackGame("LifePlayer>"+gameManager.getLifePlayer());

						

						if(addTower){
					   sendPackGame("Tower>addTower");
						for (AbstractTower tower : gameManager.getTowersUtility())
						{
							sendPackGame("Tower>"+tower.getClass().getSimpleName()+":"+tower.getX()+":"+tower.getY());
						}
						addTower=false;
						}
						
						if(play){
							gameManager.update();

							// update di monster, tower e bullet.
							sendPackGame("StartUpdate>"+play);


							if(!gameManager.isGameOver()){

								//sendPackGame("Tower>endTower");

								
								for (AbstractMonster monster : gameManager.getMonstersUtility())
								{
									sendPackGame("Monster>"+monster.getClass().getSimpleName()+":"+monster.getX()+":"+monster.getY()+":"+monster.getLife()+":"+monster.getDefaultlife()+":"+monster.getDirection());
								}
								sendPackGame("Monster>endMonster");


								// inserire identificativo per i bullet (per non avere duplicati nella grafica dei client)

								for (AbstractBullet bullet : gameManager.getBulletsUtility())
								{
									sendPackGame("Bullet>"+bullet.getClass().getSimpleName()+":"+bullet.getX()+":"+bullet.getY());


								}
								sendPackGame("Bullet>endBullet");
							}

						}
						Thread.sleep(gamespeed);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if(stopGame)
					sendPackGame("StopGame>Stop");
			}

		});
		t.start();

		

	}

	public boolean canStartRoom(String client)
	{
		if(client1.state&&client2.state &&(client1.username.equals(client)||client2.username.equals(client))){
			return true;}
		return false;
	}

	public void sendPackGame(String out)
	{
		client1.write(out);
		client2.write(out);
	}
	public void sendPrivateMessage(String out,String user)
	{
		if(client2.username.equals(user))
		{client1.write("PrivateChat>"+user+": "+out);}
		else{
			client2.write("PrivateChat>"+user+": "+out);
		}
		
	
	}

	public void addTower(String typeTower,int x,int y)
	{lock.lock();
	gameManager.addTower(typeTower, x, y);
	lock.unlock();
	}
}

class Adder extends Thread
{
	private String type;
	private int x,y;
	private GameManager gameManager;

	public Adder(GameManager gm,String typeTower,int x,int y) {
		type=typeTower;
		this.x=x;
		this.y=y;
		gameManager=gm;
	}
	@Override
	public void run() {
		gameManager.addTower(type, x, y);
	}
}
