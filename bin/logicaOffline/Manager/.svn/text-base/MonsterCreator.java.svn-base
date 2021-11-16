package logicaOffline.Manager;

import java.util.Random;

import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.strategy.Strategy;
import logicaOffline.strategy.StrategyEndLess;

public class MonsterCreator extends Thread {

	private Strategy strategy;
	private GameManager managerGame;
	private static int CountCreateMonster;


	public MonsterCreator(Strategy strategy,GameManager managerGame) {
		this.strategy=strategy;
		this.managerGame=managerGame;
		CountCreateMonster=0;
	}

	public static int getCountCreateMonster() {
		return CountCreateMonster;
	}

	public static void setCountCreateMonster(int value) {
		CountCreateMonster=value;
	}


	@Override
	public void run() {

		try {

			while(!managerGame.isGameOver())
			{
				if(!managerGame.isGamePause() && !managerGame.isBossInGame())
				{
					if(strategy instanceof StrategyEndLess)
					{
						if(CountCreateMonster <= 50)
						{
							AbstractMonster monster=strategy.generatorMonster(managerGame.getLevel());
							managerGame.addMonster(monster);


							CountCreateMonster++;}
						else
						{
							managerGame.changeLevel();
							CountCreateMonster=0;
						}

						sleep((managerGame.getGAMESPEED()*20* (new Random().nextInt(6)+3))/(managerGame.getLevel()+1));
					}
					else
					{
						if(CountCreateMonster <= ((managerGame.getLevel()+1)*50))
						{
							AbstractMonster monster=strategy.generatorMonster(managerGame.getLevel());
							managerGame.addMonster(monster);


							CountCreateMonster++;}
						else
						{
							managerGame.changeLevel();
							if(CountCreateMonster> 150){managerGame.setEndGame(true);}
							CountCreateMonster=0;
						}

						sleep((managerGame.getGAMESPEED()*10* (new Random().nextInt(6)+3))/(managerGame.getLevel()+1));
					}

				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



	}
}
