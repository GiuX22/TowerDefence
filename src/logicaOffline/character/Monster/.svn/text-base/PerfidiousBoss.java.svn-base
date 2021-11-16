package logicaOffline.character.Monster;

import java.util.concurrent.CountDownLatch;

import logicaOffline.strategy.Strategy;
import logicaOffline.world.World;

public class PerfidiousBoss extends AbstractMonster {

	private int score;
	private boolean isVisible;
	private int ContRechargeMonster;

	
	public PerfidiousBoss( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=3000;
		score=1520;
		speed= 0.04;
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
		if(ContRechargeMonster>=65)
		{
			if(life+50<=defaultlife)life+=50;
			ContRechargeMonster=0;
		}
	}

	

}
