package logicaOffline.strategy;

import java.util.Random;

import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Monster.AcceleratingMonster;
import logicaOffline.character.Monster.DevalueMonster;
import logicaOffline.character.Monster.EatingMoneyMonster;
import logicaOffline.character.Monster.MonsterBaby;
import logicaOffline.character.Monster.MonsterBoring;
import logicaOffline.character.Monster.MonsterNormal;
import logicaOffline.character.Monster.MonsterPerforator;
import logicaOffline.character.Monster.MonsterSpeedy;
import logicaOffline.character.Monster.MonsterTank;
import logicaOffline.world.World;

public class StrategyMedium implements Strategy{

	private World world;

	public StrategyMedium(World world) {
		this.world=world;
	}

	@Override
	public AbstractMonster generatorMonster(int level) {

		int rand=new Random().nextInt(10);
		switch (level) {
		case 0:
			if(rand %2==0){return new MonsterSpeedy(world);}
			else
			{return new EatingMoneyMonster( world);}

		case 1:
             
			if(rand %2==0){return new MonsterPerforator(world);}
			else
			{return new DevalueMonster( world);}
            
			
		case 2:

			if(rand %2==0){return new AcceleratingMonster(world);}
			else
			{return new MonsterTank( world);}
            
			
		}
		return new MonsterNormal(world) ;
	}



}
