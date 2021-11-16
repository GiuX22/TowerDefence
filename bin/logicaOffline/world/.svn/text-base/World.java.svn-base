package logicaOffline.world;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import logicaOffline.character.AbstractCharacter;
import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Tower.AbstractTower;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.common.StaticObject;


public interface World {
	
	public abstract StaticObject getCell(int x,int y);
	
	public abstract AbstractStaticObject getBase();
	
	public abstract AbstractStaticObject getGenerator();
	
	public abstract void settingWorld();
	
	public abstract void setScore(int i);
	
	public abstract int getLifePlayer();
	
	public abstract int getCountKillMonster();
	
	public abstract boolean isLevelOver();
	
	public abstract void setLevelOver(boolean level);
	//public abstract void setTowersList(ArrayList<AbstractTower> towers);
	
	public abstract AbstractStaticObject[] getPathMonster();
	
	public abstract ArrayList<AbstractStaticObject> getPathWorld();
	
	public abstract CopyOnWriteArrayList<AbstractBullet> getBulletList();
	
	public abstract CopyOnWriteArrayList<AbstractMonster> getMonsterList();
	
	public abstract CopyOnWriteArrayList<AbstractTower> getTowerList();
	
	public abstract void decreaseLifePlayer();
	
	public abstract void decreaseMoneyPlayer(double money);
	
	public abstract double getScore();
	
	public abstract void incrementScore(int scoreMonster);
	
	public abstract void reset();
	
	public abstract void resetLife();
}

