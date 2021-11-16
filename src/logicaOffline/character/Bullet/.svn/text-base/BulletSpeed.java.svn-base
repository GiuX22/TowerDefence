package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;

public class BulletSpeed extends AbstractBullet{
	public BulletSpeed(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.1;
		damage=13;
	}

	public BulletSpeed(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.1;
		damage+=13+powerBullet;
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
			}
			else
			{
				moveBullet();
			}
		}
	}

}
