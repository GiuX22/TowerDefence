package logicaOffline.character.Bullet;

import logicaOffline.Manager.MonsterCreator;
import logicaOffline.character.Monster.BurnBoss;
import logicaOffline.character.Monster.FrostyBoss;
import logicaOffline.character.Monster.PerfidiousBoss;
import logicaOffline.character.Tower.AbstractTower;

public class BulletUniversal extends AbstractBullet{
	public BulletUniversal(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.08;
		damage=120;


	}

	public BulletUniversal(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.08;
		damage+=10+powerBullet;
	}

	@Override
	public void update() {
		int lifeMonster;
		if(this.life)
		{
			if(targetHit())
			{life=false;
			lifeMonster=tower.getMonsterTarget().getLife();
			lifeMonster-=this.damage;
			tower.getMonsterTarget().setLife(lifeMonster);
			if(lifeMonster <=0 && (tower.getMonsterTarget() instanceof BurnBoss || tower.getMonsterTarget() instanceof PerfidiousBoss || tower.getMonsterTarget() instanceof FrostyBoss))
			{
				MonsterCreator.setCountCreateMonster(0);
			}

			}
			else
			{
				moveBullet();
			}
		}
	}
}
