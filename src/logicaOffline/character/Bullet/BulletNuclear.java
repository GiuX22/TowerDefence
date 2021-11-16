package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;

public class BulletNuclear extends AbstractBullet{
	public BulletNuclear(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.08;
		damage=20;

	}

	public BulletNuclear(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.08;
		damage+=50+powerBullet;
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
