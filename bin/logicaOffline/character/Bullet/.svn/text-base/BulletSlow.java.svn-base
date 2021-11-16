package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;

public class BulletSlow extends AbstractBullet{
	public BulletSlow(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.08;
		damage=7;
	}

	public BulletSlow(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.08;
		damage+=7+powerBullet;
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
				//questa riga rallenter������ il mostro
				if((tower.getMonsterTarget().getSpeed()-0.01) >0.02)
					tower.getMonsterTarget().setSpeed((tower.getMonsterTarget().getSpeed())-0.01);
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
