package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletNormal;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class TowerFire extends AbstractTower{


	public TowerFire(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=400;
		fireRange=2.0;
		timeToFire = 0;
        powerFire=10;// power di default del NormaBullet
	}

	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower && (timeToFire == 0) )
			{
				timeToFire = 15;
				AbstractBullet bullet=new BulletNormal(getX(), getY(), this);
				world.getBulletList().add(bullet);
				new AePlayWave("sound/normal.wav").start();
			
			}
			else {
				if(changePower && (timeToFire == 0))
				{
					timeToFire = 10;
					AbstractBullet bullet=new BulletNormal(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet);  
					powerFire=(int)bullet.getDamage();
					new AePlayWave("sound/normal.wav").start();
				}
			}


		}
		if ( timeToFire > 0){
			timeToFire--;
		}


	}
}

