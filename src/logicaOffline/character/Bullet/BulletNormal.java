package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;
public class BulletNormal extends AbstractBullet{


	public BulletNormal(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.08; // 0.6 era
		damage=14;


	}

	public BulletNormal(double x, double y, AbstractTower Tower,double powerBullet) {
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
			{this.life=false;
			lifeMonster=tower.getMonsterTarget().getLife();
			lifeMonster-=this.damage;
			tower.getMonsterTarget().setLife(lifeMonster);
			tower.setTargetChanged(false);
			}
			else
				if(tower.isTargetChanged())
				{
					moveBullet();
				}
				else
				{
					this.life=false; 
				}
		}
	}

}