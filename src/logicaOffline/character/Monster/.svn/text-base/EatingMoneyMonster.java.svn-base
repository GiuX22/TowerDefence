package logicaOffline.character.Monster;

import logicaOffline.world.World;

public class EatingMoneyMonster extends AbstractMonster{
	
	
	private int score;
	private boolean isVisible;


	public EatingMoneyMonster( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=600;
		score=510;
		speed= 0.1;
		isVisible=true;
		defaultlife=600;
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
		super.world.decreaseMoneyPlayer(0.1);
		super.update();
	}

}
