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

public class MonsterTank extends AbstractMonster {	
	
	private int score;
	private boolean isVisible;
	

	public MonsterTank( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=600;
		score=410;
		speed= 0.06;
		isVisible=true;
		defaultlife=600;
		setTimeTransition(2);
		path=world.getPathMonster();
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}


	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}
	@Override
	public int getLife() {
		// TODO Auto-generated method stub
		return life;
	}

	public void update()
	{
		super.update();

	}
	
}
