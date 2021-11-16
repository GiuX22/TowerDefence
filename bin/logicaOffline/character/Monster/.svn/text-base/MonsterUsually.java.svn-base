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

public class MonsterUsually extends AbstractMonster {

	private int score;
	private boolean isVisible;

	public MonsterUsually( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=450;
		score=230;
		speed= 0.09;
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
	
	

//	public static void main(String[] args) throws IOException, InterruptedException {
//		
//		  	WorldManager manager=new WorldManager();
//			
//			AbstractStaticObject[][] object=manager.loadWorld("2");
//		    WorldImpl world=new WorldImpl(object);
//		       
//		    
//		    AbstractStaticObject [] cammino=world.getPathMonster();
//		    MonsterSpeedy monster=new MonsterSpeedy(0,2,world);
//		    while(monster.getX() != cammino[cammino.length-1].getX() || monster.getY() != cammino[cammino.length-1].getY())
//		    {
//		    	monster.update();
//		    	//Thread.sleep(1000);
//		    }
//
//}
	
}
