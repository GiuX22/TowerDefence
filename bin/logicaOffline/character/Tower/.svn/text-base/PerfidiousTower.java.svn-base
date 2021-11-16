package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletPerfidious;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class PerfidiousTower extends AbstractTower{
	public PerfidiousTower(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=5400;
		fireRange=3.0;
		timeToFire=0;
		powerFire=150;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower && (timeToFire == 0))
			{
				timeToFire = 9;
				AbstractBullet bullet=new BulletPerfidious(getX(), getY(), this);
				world.getBulletList().add(bullet);
				 new AePlayWave("sound/perfidious.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					timeToFire = 5;

					AbstractBullet bullet=new BulletPerfidious(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet);  
					powerFire=(int)bullet.getDamage();
					new AePlayWave("sound/perfidious.wav").start();
				}

			}
		}
		if ( timeToFire > 0){
			timeToFire--;
		}



	}



}
