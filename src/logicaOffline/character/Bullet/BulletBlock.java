package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;

public class BulletBlock extends AbstractBullet{
	final boolean slowDown=true;

	public BulletBlock(double x,double y,AbstractTower tower) {
		super(x, y,tower);
		speed=0.08;
		damage=4;

	}

	public BulletBlock(double x, double y, AbstractTower Tower,double powerBullet) {
		super(x, y, Tower);
		speed=0.08;
		damage+=5+powerBullet;
	}

	@Override
	public void update() {
		int lifeMonster;
		if(this.life)
		{
			if(targetHit())
			{life=false;
			lifeMonster=tower.getMonsterTarget().getLife();
			// questa riga serve al proiettile bloccante per bloccare il mostro e quindi va a modificare il campo blockDown
			//in AbstractMoster e quindi il mostro si bloccher�� perch�� la funzione update non gli permette di entrare
			tower.getMonsterTarget().setBlockDown(true);
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
