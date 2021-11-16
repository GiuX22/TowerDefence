package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;

public class BulletFrosty extends AbstractBullet{
	public BulletFrosty(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.07;
		damage=75;

	}

	public BulletFrosty(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.07;
		damage+=80+powerBullet;
	}

	@Override
	public void update() {
		int lifeMonster;
		if(life)
		{
			if(targetHit())
			{
				life=false;
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
