package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;

public class BulletSuicide extends AbstractBullet{
	public BulletSuicide(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.05;
		damage=20;
	}
	
	public BulletSuicide(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.05;
		damage+=20+powerBullet;
	}

	@Override
	public void update() {
		int lifeMonster;
		if(this.life)
		{
			if(targetHit())
			{
				life=false;
				lifeMonster=tower.getMonsterTarget().getLife();
				lifeMonster=0;
				tower.getMonsterTarget().setLife(lifeMonster);
			}
			else
			{
				moveBullet();
			}
		}
	}
}
