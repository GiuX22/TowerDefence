package logicaOffline.character.Tower;


import java.util.concurrent.CopyOnWriteArrayList;

import logicaOffline.character.AbstractCharacter;
import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Monster.BurnBoss;
import logicaOffline.character.Monster.FrostyBoss;
import logicaOffline.character.Monster.MonsterInvisible;
import logicaOffline.character.Monster.PerfidiousBoss;
import logicaOffline.world.World;

public abstract class AbstractTower implements Tower{


	public double getFireRange() {
		return fireRange;
	}

	protected int cost;
	private double x;
	private double y;
	protected double fireRange;
	protected AbstractMonster monsterTarget;
	protected World world;
	private CopyOnWriteArrayList<AbstractMonster> monsters;
	protected double powerBullet;
	protected int id;
	protected int timeToFire;
	protected int powerFire;

	protected boolean changePower;
	protected boolean TargetChanged;


	public AbstractTower(int x,int y,World world)
	{
		this.x=x;
		this.y=y;
		this.world=world;
		monsters=world.getMonsterList();
		powerBullet=0.0;
		changePower=false;
		monsterTarget=null;
		TargetChanged=false;

	}

	public void setId(int id) {
		this.id = id;
	}


	public int getId() {
		return id;
	}
	public int getPowerFire() {
		return powerFire;
	}

	public AbstractMonster getMonsterTarget() {
		return monsterTarget;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x=x;


	}

	@Override
	public void setY(int y) {
		this.y=y;

	}

	public int getCost()
	{
		return cost;
	}



	@Override
	public void update() {
		chooseMonsterTarget();

	}

	public void chooseMonsterTarget()
	{
		if(!monsters.isEmpty())
		{	for (int j =0; j <=monsters.size()-1 ; j++) {

			if(isInTheRange(monsters.get(j)))
			{
				if(monsters.get(j) instanceof MonsterInvisible && this instanceof TowerInvisible)
				{
					monsterTarget=monsters.get(j);
					TargetChanged=true;
					break;
				}

				if(monsters.get(j) instanceof PerfidiousBoss && (this instanceof PerfidiousTower || this instanceof UniversalTower))
				{
					monsterTarget=monsters.get(j);
					TargetChanged=true;
					break;
				}

				if(monsters.get(j) instanceof FrostyBoss && (this instanceof FrostyTower || this instanceof UniversalTower))
				{
					monsterTarget=monsters.get(j);
					TargetChanged=true;
					break;
				}

				if(monsters.get(j) instanceof BurnBoss && (this instanceof BurnTower || this instanceof UniversalTower))
				{
					monsterTarget=monsters.get(j);
					TargetChanged=true;
					break;
				}

				if(!(monsters.get(j) instanceof MonsterInvisible) && !(monsters.get(j) instanceof PerfidiousBoss) && !(monsters.get(j) instanceof FrostyBoss) && !(monsters.get(j) instanceof BurnBoss))
				{
					monsterTarget=monsters.get(j);
					TargetChanged=true;
					break;

				}

				monsterTarget=monsters.get(j);

			}
			else
			{
				TargetChanged=false;
			}

		}
		}
		else TargetChanged=false;




	}

	public boolean isTargetChanged() {
		return TargetChanged;
	}

	public void setTargetChanged(boolean targetChanged) {
		TargetChanged = targetChanged;
	}

	public boolean isInTheRange(AbstractCharacter monster)
	{
		if(Math.abs(getX()-monster.getX())>fireRange || Math.abs(getY()-monster.getY())>fireRange)
		{return false;}
		return true;
	}

	public void upPowerBullet()
	{
		this.powerBullet+=20;
		changePower=true;
	}

	public void upTowerRange()
	{
		fireRange+=1;
	}

	public void incrementScore(int scoreMonster)
	{
		world.incrementScore(scoreMonster);
	}

}
