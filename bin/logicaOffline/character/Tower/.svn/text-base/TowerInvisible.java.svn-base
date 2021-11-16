package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletInvisible;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class TowerInvisible extends AbstractTower{
	public TowerInvisible(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=900;
		fireRange=2.0;
		timeToFire=0;
		powerFire=50;
	}

	@Override
	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower && (timeToFire == 0))
			{
				timeToFire = 10;
				AbstractBullet bullet=new BulletInvisible(getX(), getY(), this);
				world.getBulletList().add(bullet);
				new AePlayWave("sound/invisible.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					timeToFire = 7;

					AbstractBullet bullet=new BulletInvisible(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet);  
					powerFire=(int)bullet.getDamage();
					new AePlayWave("sound/invisible.wav").start();
				}

			}
		}
		if ( timeToFire > 0){
			timeToFire--;
		}

	}

}
