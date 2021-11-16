package logicaOffline.strategy;

import logicaOffline.character.Monster.AbstractMonster;

public interface Strategy {


	public abstract AbstractMonster generatorMonster(int level);

}
