package logicaOffline.strategy;

import java.util.Random;

import logicaOffline.Manager.MonsterCreator;
import logicaOffline.character.Monster.*;
import logicaOffline.world.World;

public class StrategyHard implements Strategy{

private World world;
	
	public StrategyHard(World world) {
		this.world=world;
	}

	@Override
	public AbstractMonster generatorMonster(int level) {
	
		int rand=new Random().nextInt(3)+1;
		
		switch (level) {
		case 0:
               if(MonsterCreator.getCountCreateMonster()==(level+1)*50)
               {
            	   return new BurnBoss(world);
               }
               else
               {
            	  if(rand==1)
            	  {
            		  return new MonsterBaby(world);
            	  }
            	  if(rand==2)
            	  {
            		  return new MonsterBoring(world);
            	  }
            	  if(rand==3)
            	  {
            		  return new MonsterUsually(world);
            	  }
            	   
               }
           break;
			
		case 1:
			 if(MonsterCreator.getCountCreateMonster()==(level+1)*50)
             {
          	   return new FrostyBoss(world);
             }
             else
             {
            	 if(rand==1)
           	  {
           		  return new MonsterInvisible(world);
           	  }
           	  if(rand==2)
           	  {
           		  return new EatingMoneyMonster(world);
           	  }
           	  if(rand==3)
           	  {
           		  return new DevalueMonster(world);
           	  }
             }
			 break;
		
		case 2:
			 if(MonsterCreator.getCountCreateMonster()==(level+1)*50)
             {
          	   return new PerfidiousBoss(world);
             }
             else
             {
            	 if(rand==1)
              	  {
              		  return new AcceleratingMonster(world);
              	  }
              	  if(rand==2)
              	  {
              		  return new MonsterTank(world);
              	  }
              	  if(rand==3)
              	  {
              		  return new MonsterPerforator(world);
              	  }
             }
			 break;
		}
		
		return new MonsterNormal(world) ;
	
	}

	

}
