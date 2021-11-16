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

public class StrategyEndLess implements Strategy{

	private World world;
	private boolean bossGenerated;

	public StrategyEndLess(World world) {
		this.world=world;
		bossGenerated=false;
	}
	@Override
	public AbstractMonster generatorMonster(int level) {

		
		AbstractMonster monster;

		if(level<=2){
			
			int random2=new Random().nextInt(3)+1;
			switch (random2) {
			case 1:
				monster=new MonsterNormal(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 2:
				monster=new MonsterTank(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 3:
				monster=new MonsterUsually(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			}
		}
		else{
			int random;
			if(level%3==0 && !bossGenerated)
			{
				random=new Random().nextInt(3)+1;
				bossGenerated=true;
				switch (random) {
				case 1:
					return new BurnBoss(world);
				case 2:
					return new PerfidiousBoss(world);
				case 3:
					return new FrostyBoss(world);
				}
				
			}
			else{
			if(level%3!=0)
			{bossGenerated=false;}
		    random=new Random().nextInt(11)+1;
			switch (random) {
			case 1:
				monster=new MonsterNormal(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 2:
				monster=new MonsterTank(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 3:
				monster=new MonsterUsually(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 4:
				monster=new MonsterSpeedy(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 5:
				monster=new MonsterBoring(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 6:
				monster=new MonsterBaby(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 7:
				monster=new MonsterInvisible(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 8:
				monster=new MonsterPerforator(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 9:
				monster=new AcceleratingMonster(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 10:
				monster=new DevalueMonster(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			case 11:
				monster=new EatingMoneyMonster(world);
				monster.incrementLifeMonster((level+1)*25);
				return monster;
			}

			}
		}

	return new MonsterNormal(world);
}

}
