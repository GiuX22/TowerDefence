package logicaOffline.character.Monster;

import logicaOffline.world.World;

public class MonsterInvisible extends AbstractMonster {

	
 
	private int score;
	private boolean isVisible;
	
	

	public MonsterInvisible( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=120;
		score=220;
		speed= 0.06;
		isVisible=true;
		defaultlife=120;
		setTimeTransition(2);

		path=world.getPathMonster();
	}
	
	//SET METHOD 
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	//GET METHOD

	@Override
	public double getSpeed() {
		return speed;
	}
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
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
