package logicaOffline.character.Monster;

import logicaOffline.world.World;

public class FrostyBoss extends AbstractMonster{
	
	// IL BOSS DI MEDIA POTENZA.
	
	private int score;
	private boolean isVisible;
	private int ContRechargeMonster;
	
	public FrostyBoss( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=3000;
		score=1300;
		speed= 0.05;
		isVisible=true;
		defaultlife=3000;
		setTimeTransition(2);
		path=world.getPathMonster();
	}
	
	@Override
	public int getScore() {
		return score;
	}


	@Override
	public void update() {
		ContRechargeMonster++;
		super.update();
		if(ContRechargeMonster>=50)
		{
			life=life+50;
			this.speed+=0.01;
			ContRechargeMonster=0;

		}
	}


}
