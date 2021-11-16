package logicaOffline.character.Monster;

import java.io.IOException;
import java.util.ArrayList;

import logicaOffline.common.AbstractStaticObject;
import logicaOffline.common.StaticObject;
import logicaOffline.world.Base;
import logicaOffline.world.Street;
import logicaOffline.world.World;
import logicaOffline.world.WorldImpl;
import logicaOffline.world.WorldManager;

public class MonsterBoring extends AbstractMonster {

	

	
	private int score;
	private boolean isVisible;

	public MonsterBoring( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=270;
		score=225;
		speed= 0.08;
		isVisible=true;
		defaultlife=270;
		setTimeTransition(2);
		path=world.getPathMonster();
	}
	
	//SET METHOD 
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}

	//GET METHOD
	
	public double getSpeed() {
		return speed;
	}
	public int getScore() {
		return score;
	}
	@Override
	public int getLife() {
		return life;
	}

	public void update()
	{
		super.update();
	}
	
}
