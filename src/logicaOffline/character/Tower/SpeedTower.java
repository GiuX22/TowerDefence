package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletSpeed;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class SpeedTower extends AbstractTower{
	public SpeedTower(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=600;
		fireRange=1.8;
		  timeToFire=0;
		  powerFire=15;
	
	}

	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower && (timeToFire == 0))
			{
				timeToFire = 10;
				AbstractBullet bullet=new BulletSpeed(getX(), getY(), this);
				world.getBulletList().add(bullet);
				new AePlayWave("sound/speed.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					timeToFire = 7;

				AbstractBullet bullet=new BulletSpeed(getX(), getY(), this,powerBullet);
				world.getBulletList().add(bullet);  
				powerFire=(int)bullet.getDamage();
				new AePlayWave("sound/speed.wav").start();
				}
			}	
		}
		if ( timeToFire > 0){
			timeToFire--;
		}
	}




}
