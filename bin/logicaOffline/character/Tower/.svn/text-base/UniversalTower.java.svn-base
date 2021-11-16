package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletUniversal;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class UniversalTower extends AbstractTower{
	public UniversalTower(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=10000;
		fireRange=3.0;
		timeToFire=0;
		powerFire=50;
		
	}

	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower && (timeToFire == 0))
			{
				timeToFire = 10;
				AbstractBullet bullet=new BulletUniversal(getX(), getY(), this);
				world.getBulletList().add(bullet);
				new AePlayWave("sound/universal.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					timeToFire = 10;
					AbstractBullet bullet=new BulletUniversal(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet);  
					powerFire=(int)bullet.getDamage();
					new AePlayWave("sound/universal.wav").start();
				}
			}
		}
		if ( timeToFire > 0){
			timeToFire--;
		}

	}




}
