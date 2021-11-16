package logicaOffline.character.Monster;

import logicaOffline.world.World;

public class AcceleratingMonster extends AbstractMonster{

	private int score;
	private boolean isVisible;
	private int CountNumberUpdate;

	public AcceleratingMonster( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=250;
		score=310;
		speed= 0.08;
		defaultlife=250;
		path=world.getPathMonster();
		setTimeTransition(1);
		isVisible=true;
		CountNumberUpdate=0;
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
		CountNumberUpdate++;
		super.update();
		if(CountNumberUpdate>=10)
		{
			speed+=0.03;
			CountNumberUpdate=0;
		}
	}
}
