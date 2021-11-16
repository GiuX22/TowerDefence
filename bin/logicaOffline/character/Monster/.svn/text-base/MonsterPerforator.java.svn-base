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

public class MonsterPerforator extends AbstractMonster {

	private int score;
	private boolean isVisible;

	public MonsterPerforator( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=450;
		score=250;
		speed= 0.1;
		isVisible=true;
		defaultlife=450;
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
