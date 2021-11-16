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

public class MonsterBaby extends AbstractMonster {

	
	// Ricordare di inserire un'immagine con le tette.

	
	private int score;
	private boolean isVisible;

	public MonsterBaby( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=270;
		score=220;
		speed= 0.09;
		isVisible=true;
		defaultlife=270;
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
