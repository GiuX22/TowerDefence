package logicaOffline.strategy;

import java.util.Random;

import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Monster.AcceleratingMonster;
import logicaOffline.character.Monster.BurnBoss;
import logicaOffline.character.Monster.DevalueMonster;
import logicaOffline.character.Monster.EatingMoneyMonster;
import logicaOffline.character.Monster.FrostyBoss;
import logicaOffline.character.Monster.MonsterBaby;
import logicaOffline.character.Monster.MonsterBoring;
import logicaOffline.character.Monster.MonsterInvisible;
import logicaOffline.character.Monster.MonsterNormal;
import logicaOffline.character.Monster.MonsterPerforator;
import logicaOffline.character.Monster.MonsterSpeedy;
import logicaOffline.character.Monster.MonsterTank;
import logicaOffline.character.Monster.MonsterUsually;
import logicaOffline.character.Monster.PerfidiousBoss;
import logicaOffline.world.World;

public class StrategyEasy implements Strategy {

	private World world;
	
	public StrategyEasy(World world) {
		this.world=world;
	}
	
	@Override
	public AbstractMonster generatorMonster(int level) {

		switch (level) {
		case 0:
             return new MonsterNormal(world);
         
		case 1:
            return new  MonsterPerforator(world);
		case 2:
             
			return new MonsterSpeedy(world);
           
		}
		
		return new MonsterNormal(world) ;
	}	
			
	
}
