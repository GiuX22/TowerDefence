package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletFrosty;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class FrostyTower extends AbstractTower{
	public FrostyTower(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=3500;
		fireRange=3.0;
        timeToFire=0;
        powerFire=210;
	}

	@Override
	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower && (timeToFire == 0))
			{
				timeToFire=20;
				AbstractBullet bullet=new BulletFrosty(getX(), getY(), this);
				world.getBulletList().add(bullet);
				new AePlayWave("sound/iceBullet.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					
					timeToFire=10;

					AbstractBullet bullet=new BulletFrosty(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet);  
					powerFire=(int)bullet.getDamage();
					new AePlayWave("sound/iceBullet.wav").start();
				}

			}
		}
		if ( timeToFire > 0){
			timeToFire--;
		}


	}



}
