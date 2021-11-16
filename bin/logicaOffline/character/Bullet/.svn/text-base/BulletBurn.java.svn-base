package logicaOffline.character.Bullet;

import logicaOffline.Manager.MonsterCreator;
import logicaOffline.character.Monster.BurnBoss;
import logicaOffline.character.Tower.AbstractTower;

public class BulletBurn extends AbstractBullet {
	public BulletBurn(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.08;
		damage=60;	
	}

	public BulletBurn(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.08;
		damage+=60+powerBullet;
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
			if(lifeMonster <=0 && (tower.getMonsterTarget() instanceof BurnBoss))
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
