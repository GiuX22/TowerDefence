package logicaOffline.Manager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import PlugIn.StringEncrypter.EncryptionException;
import PlugIn.UtilityQRCode;
import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Monster.BurnBoss;
import logicaOffline.character.Monster.FrostyBoss;
import logicaOffline.character.Monster.PerfidiousBoss;
import logicaOffline.character.Tower.AbstractTower;
import logicaOffline.character.Tower.BlockTower;
import logicaOffline.character.Tower.BurnTower;
import logicaOffline.character.Tower.FrostyTower;
import logicaOffline.character.Tower.NuclearTower;
import logicaOffline.character.Tower.PerfidiousTower;
import logicaOffline.character.Tower.SlowTower;
import logicaOffline.character.Tower.SpeedTower;
import logicaOffline.character.Tower.SuicideTower;
import logicaOffline.character.Tower.TowerFire;
import logicaOffline.character.Tower.TowerInvisible;
import logicaOffline.character.Tower.UniversalTower;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.strategy.Strategy;
import logicaOffline.strategy.StrategyEasy;
import logicaOffline.strategy.StrategyEndLess;
import logicaOffline.strategy.StrategyHard;
import logicaOffline.strategy.StrategyMedium;
import logicaOffline.utility.TowerInfo;
import logicaOffline.world.World;
import logicaOffline.world.WorldManager;


public class GameManager {

	
	public GameManager copy=this;
	private int monsterDead=0;
	private boolean bonusR;
	private boolean gameInOffline;
	private int scoreSotto200=0;
	private int bonusFire=0;
	private int bonusRange=0;
	private long GAMESPEED;
	private boolean gamePause;
	private boolean win;
	private Lock lock=new ReentrantLock();
	private List<AbstractMonster> monstersUtility;
	private List<AbstractTower> towersUtility; // V
	private List<AbstractBullet> bulletsUtility; 
	private List<TowerInfo> TowersAddInfo; // V
	private List<TowerInfo> TowersGeneralInfo; 
	private MonsterCreator creator;
	private Strategy strategy; // V
	protected boolean endGame; // V
	private int level; // V
	private int gamestate=0;
	private WorldManager worldManager;
	private World world; // solo nome
	private int idTower; 
	private String typeStrategy;
	private String pathWorld;
	private boolean bossInGame;
	
	public GameManager() {}


	
	
	//SET METHOD
	
	public void setScore(int a)
	{
		world.setScore(a);
	}
	
	public void setGAMESPEED(long gAMESPEED) {
		GAMESPEED = gAMESPEED;
	}
	
	public void setTypeStrategy(String typeStrategy) {
		this.typeStrategy = typeStrategy;
	}

	public void setWorld(String string) {
		this.pathWorld=string;
	}
	
	public void setGamePause(boolean gamePause) {
		this.gamePause = gamePause;
	}

	public void setBonusR(boolean bonusR) {
		this.bonusR = bonusR;
	}
	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
		
	}
	
	public void setGameInOffline(boolean a)
	{
		gameInOffline=a;
	}
	
	//GET METHOD


	public Strategy getStrategy() {
		return strategy;
	}
	
	public int getIdTower() {
		return idTower;
	}

	
	public long getGAMESPEED() {
		return GAMESPEED;
	}

	public  int getLevel() {
		return level;
	}
	public void changeLevel()
	{++level;}
	
	public World getWorld() {
		return world;
	}

	public int getGamestate() {
		return gamestate;
	}
	
	public boolean isBonusR() {
		return bonusR;
	}
	

	public double getScore()
	{
		return world.getScore();
	}

	

	public String getPathWorld()
	{return pathWorld;}
	
	


	public boolean isWin() {
		return win;
	}
	public boolean isEndGame() {
		return endGame;
	}

	public void changeWorld(String stringa) throws IOException
	{
		world=worldManager.getNextWorld(stringa);
	}


	public void initGame() throws IOException{
		idTower=1;
		endGame=false;
		level=0;
		win=false;
		gamePause=false;
		bossInGame=false;
		GAMESPEED=40;
		worldManager=new WorldManager();
		world=worldManager.getNextWorld(pathWorld);
		monstersUtility=new CopyOnWriteArrayList<AbstractMonster>();
		towersUtility=new CopyOnWriteArrayList<AbstractTower>();
		bulletsUtility=new CopyOnWriteArrayList<AbstractBullet>();
		TowersAddInfo=new ArrayList<TowerInfo>();
		TowersGeneralInfo=new ArrayList<TowerInfo>();
		monstersUtility=world.getMonsterList();
		towersUtility=world.getTowerList();
		bulletsUtility=world.getBulletList();
		setStrategy(typeStrategy);
		creator=new MonsterCreator(this.strategy, this);
		initTowersGeneralInfo();
	}

	public List<TowerInfo> getTowersAddInfo() {
		return TowersAddInfo;
	}

	public List<TowerInfo> getTowersGeneralInfo() {
		return TowersGeneralInfo;
	}

	public void initTowersGeneralInfo()
	{
		TowersGeneralInfo.add(new TowerInfo("BlockTower", 2.0,700,5));
		TowersGeneralInfo.add(new TowerInfo("TowerFire", 2.0,400,10));
		TowersGeneralInfo.add(new TowerInfo("BurnTower", 4.0,5200,300));
		TowersGeneralInfo.add(new TowerInfo("FrostyTower", 3.0,3500,210));
		TowersGeneralInfo.add(new TowerInfo("NuclearTower", 2.5,700,50));
		TowersGeneralInfo.add(new TowerInfo("PerfidiousTower", 3.0,5400,150));
		TowersGeneralInfo.add(new TowerInfo("SlowTower", 1.0,600,7));
		TowersGeneralInfo.add(new TowerInfo("SpeedTower", 1.8,600,15));
		TowersGeneralInfo.add(new TowerInfo("SuicideTower", 3.5,4500,500));
		TowersGeneralInfo.add(new TowerInfo("TowerInvisible", 2.0,900,50));
		TowersGeneralInfo.add(new TowerInfo("UniversalTower", 3.0,10000,50));

	}

	public CopyOnWriteArrayList<AbstractMonster> getMonstersUtility() {

		return  (CopyOnWriteArrayList<AbstractMonster>) monstersUtility;
	}

	public CopyOnWriteArrayList<AbstractTower> getTowersUtility() {

		return (CopyOnWriteArrayList<AbstractTower>) towersUtility;
	}

	public CopyOnWriteArrayList<AbstractBullet> getBulletsUtility() {

		return (CopyOnWriteArrayList<AbstractBullet>) bulletsUtility;
	}

	public AbstractStaticObject[] getStreet()
	{
		return world.getPathMonster();
	}

	public int getLifePlayer()
	{
		return world.getLifePlayer();
	}
	public boolean isGameOver()
	{
		if(endGame){return true;}

		if(world.getLifePlayer()==0){
			return true;}
		if(level>=3 && world.getLifePlayer()>0 && monstersUtility.size()<=0 && bulletsUtility.size()<=0){
			win=true;
			endGame=true;
           return true;}

		if(strategy instanceof StrategyEndLess){
		    return false;
		}else{
			if(level>=3 && world.getLifePlayer()>0 && monstersUtility.size()<=0 && bulletsUtility.size()<=0){
				win=true;
				endGame=true;
				return true;}
		}


		return false;
	}

	public boolean isLevelOver()
	{
		if(world.isLevelOver())
		{level++;
		world.setLevelOver(false);
		return true;}

		return false;
	}



	public void startGame()
	{
		creator.start();
	}

	public void reset()
	{
		world.reset();
		bossInGame=false;
		scoreSotto200=0;
		bonusFire=0;
		bonusRange=0;
		monstersUtility.clear();
		towersUtility.clear();
		bulletsUtility.clear();
	}

	public boolean isGamePause() {
		return gamePause;
	}

	public void update()
	{
		lock.lock();
		try {


			if(world.getScore()<200 && world.getLifePlayer()==3 && gameInOffline==true)
			{
				if(scoreSotto200==0)
				{
					new Thread(new Runnable() {

						@Override
						public void run() {
					UtilityQRCode utility;
					try {
						utility = UtilityQRCode.getIstance(copy);
						utility.generateQRCode("increasesMoney");
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bonusR=true;
						}
					}).start();
					
				}
				scoreSotto200++;
			}

			if(monsterDead>60 && world.getLifePlayer()>1 && gameInOffline==true)
			{
				if(bonusFire==0)
				{
					new Thread(new Runnable() {

						@Override
						public void run() {
					UtilityQRCode utility;
					try {
						utility = UtilityQRCode.getIstance(copy);
						utility.generateQRCode("increasesFire");
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bonusR=true;
						}
					}).start();
				}
				bonusFire++;

			}

			if(monsterDead>110 && world.getScore()>10000 && world.getLifePlayer()>=2 && gameInOffline==true)
			{
				if(bonusRange==0)
				{
					new Thread(new Runnable() {

						@Override
						public void run() {
					UtilityQRCode utility;
					try {
						utility = UtilityQRCode.getIstance(copy);
						utility.generateQRCode("increasesRange");
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bonusR=true;
						}
					}).start();

				}
				bonusRange++;

			}



			if(!gamePause && !isGameOver())
			{for (int i = 0; i < monstersUtility.size(); i++) {
				monstersUtility.get(i).update();
			}

			for (int i = 0; i < towersUtility.size(); i++) {
				towersUtility.get(i).update();
			}
			for (int i = 0; i < bulletsUtility.size(); i++) {
				bulletsUtility.get(i).update();
			}


			cleanWorld();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			lock.unlock();
		}


	}


	public  boolean addMonster(AbstractMonster monster)
	{
		lock.lock();
		try {
			if(monster instanceof BurnBoss || monster instanceof FrostyBoss || monster instanceof PerfidiousBoss )
			{bossInGame=true;}				
			monstersUtility.add(monster);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{lock.unlock();
		}

		return true;
	}

	public void removeMonster(int index)
	{
		lock.lock();
		try {
			monstersUtility.remove(index);
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			lock.unlock();
		}

	}


	public TowerInfo getTowersAddInfo(int idTower) {
		for (TowerInfo towerInfo : TowersAddInfo) {
			if(towerInfo.getId()==idTower)return towerInfo;
		}
		return null;
	}
	public TowerInfo getTowersAddInfo(String tower) {
		for (TowerInfo towerInfo : TowersAddInfo) {
			if(towerInfo.getType().equals(tower))return towerInfo;
		}
		return null;
	}
	public TowerInfo getTowersGeneralInfo(String tower) {
		for (TowerInfo towerInfo : TowersGeneralInfo) {
			if(towerInfo.getType().equals(tower))return towerInfo;
		}
		return null;
	}

	public int getIdTowerSelected(int x, int y)
	{
		for (int i = 0; i < TowersAddInfo.size(); i++) {
			if(TowersAddInfo.get(i).getX()==x && TowersAddInfo.get(i).getY()==y){return TowersAddInfo.get(i).getId();}
		}
		return 0;
	}

	public void removeTower(int id)
	{

		AbstractTower towerRemove=null;
		TowerInfo towerInfo=null;
		for (int i = 0; i < towersUtility.size(); i++) {
			int idTemp=towersUtility.get(i).getId();
			if(idTemp==id)
			{
				towerRemove=towersUtility.get(i);
				towerInfo= TowersAddInfo.get(i);
			}
		}
		towersUtility.remove(towerRemove);
		TowersAddInfo.remove(towerInfo);


	}

	public void addTower(String typeTower,int x,int y)
	{
		if(canAddTower(x, y,typeTower))
		{idTower++;
		AbstractTower tower;
		switch (typeTower) {
		case "TowerFire":
			tower= new TowerFire(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"TowerFire",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "TowerInvisible":
			tower= new TowerInvisible(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"TowerInvisible",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;

		case "BlockTower":
			tower= new BlockTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"BlockTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "BurnTower":
			tower= new BurnTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"BurnTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "FrostyTower":
			tower= new FrostyTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"FrostyTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "NuclearTower":
			tower= new NuclearTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"NuclearTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "PerfidiousTower":
			tower= new PerfidiousTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"PerfidiousTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "SlowTower":
			tower= new SlowTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"SlowTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "SpeedTower":
			tower= new SpeedTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"SpeedTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "SuicideTower":
			tower= new SuicideTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"SuicideTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;
		case "UniversalTower":
			tower= new UniversalTower(x, y, world);
			tower.setId(idTower);
			towersUtility.add(tower);
			world.decreaseMoneyPlayer(tower.getCost());
			TowersAddInfo.add(new TowerInfo(x,y,"UniversalTower",tower.getFireRange(),idTower,tower.getCost(),tower.getPowerFire()));
			break;

		}
		}

	}

	public boolean canAddTower(int x ,int y,String tower)
	{
		if(world.getGenerator().getX()==x && world.getGenerator().getY()==y){return false;}

		for (int i = 0; i < towersUtility.size(); i++) {
			if(towersUtility.get(i).getX()==x && towersUtility.get(i).getY()==y)
			{
				return false;
			}
		}


		for (int i = 0; i < world.getPathWorld().size(); i++) {
			if(world.getPathWorld().get(i).getX()==x && world.getPathWorld().get(i).getY()==y )
			{
				return false;
			}
		}
		if(getScore()- this.getTowersGeneralInfo(tower).getCost()<0){return false;}
		return true;

	}

	public  void upgradeTower(int idTower,String typeUpgrade)
	{
		switch (typeUpgrade) {
		case "fire":
			for (int i = 0; i <towersUtility.size(); i++) {
				if( towersUtility.get(i).getId() == idTower)
				{
					if(getScore()- TowersAddInfo.get(i).getUpdateFireCost()>=0)
					{TowersAddInfo.get(i).setUpdateFireTower(true);
					TowersAddInfo.get(i).setDamage(20);
					towersUtility.get(i).upPowerBullet();
					this.world.decreaseMoneyPlayer(TowersAddInfo.get(i).getUpdateFireCost());}
				}
			}
			break;
		case "fireBonus":
			for (int i = 0; i <towersUtility.size(); i++) {
				if( towersUtility.get(i).getId() == idTower)
				{
					TowersAddInfo.get(i).setUpdateFireTower(true);
					TowersAddInfo.get(i).setDamage(20);
					towersUtility.get(i).upPowerBullet();
				}
			}
			break;

		case "rangeBonus":
			for (int i = 0; i < towersUtility.size(); i++) {
				if( towersUtility.get(i).getId() == idTower)
				{
					TowersAddInfo.get(i).setUpdateRangeTower(true);
					TowersAddInfo.get(i).setRange(1);
					towersUtility.get(i).upTowerRange();						
				}
			}

			break;

		case "range":
			for (int i = 0; i < towersUtility.size(); i++) {
				if( towersUtility.get(i).getId() == idTower)
				{
					System.out.println("entro in range");
					if(getScore()- TowersAddInfo.get(i).getUpdateRangeCost()>=0)
					{TowersAddInfo.get(i).setUpdateRangeTower(true);
					TowersAddInfo.get(i).setRange(1);
					towersUtility.get(i).upTowerRange();
					this.world.decreaseMoneyPlayer(TowersAddInfo.get(i).getUpdateRangeCost());
					}
				}
			}

			break;
		}
	}



	public void cleanWorld()
	{
		for (int i = 0; i < monstersUtility.size(); i++) {

			if(monstersUtility.get(i).getLife()<=0)
			{
				monsterDead++;
				if(monstersUtility.get(i) instanceof BurnBoss || monstersUtility.get(i) instanceof FrostyBoss
						|| monstersUtility.get(i) instanceof PerfidiousBoss )
				{
					bossInGame=false;
					changeLevel();
				}
				world.incrementScore(monstersUtility.get(i).getScore());
				removeMonster(i);
			}
		}

		for (int i = 0; i < bulletsUtility.size(); i++) {
			if(!bulletsUtility.get(i).isLife())
			{
				bulletsUtility.remove(i);
			}
		}

	}


	public boolean isBossInGame() {
		return bossInGame;
	}

	public void setStrategy(String typeStrategy)
	{
		if(this.typeStrategy==null)
		{
			this.typeStrategy=typeStrategy;
		}
		switch (typeStrategy) {
		case "easy":
			this.strategy=new StrategyEasy(world);
			break;
		case "medium":
			this.strategy=new StrategyMedium(world);
			break;
		case "hard":
			this.strategy=new StrategyHard(world);
			break;
		case "endLess":
			this.strategy=new StrategyEndLess(world);
		}


	}

	
	public boolean isGameInOffline()
	{
		return gameInOffline;
	}


}
