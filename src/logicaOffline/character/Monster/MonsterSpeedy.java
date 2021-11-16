package logicaOffline.character.Monster;

import logicaOffline.world.World;

public class MonsterSpeedy extends AbstractMonster {

	private int score;
	private boolean isVisible;
	

	public MonsterSpeedy( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=80;
		score=310;
		speed= 0.08;
		defaultlife=80;
		setTimeTransition(2);
		path=world.getPathMonster();
		isVisible=true;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public double getSpeed() {
		return speed;
	}


	@Override
	public int getScore() {
		return score;
	}
	@Override
	public int getLife() {
		return life;
	}

	@Override
	public void update()
	{
		super.update();
	}
	
}
