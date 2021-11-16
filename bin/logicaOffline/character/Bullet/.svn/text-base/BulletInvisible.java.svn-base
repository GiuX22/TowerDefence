package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;

public class BulletInvisible extends AbstractBullet{

	public BulletInvisible(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.08;
		damage=8;


	}

	public BulletInvisible(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.08;
		damage+=9+powerBullet;
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
