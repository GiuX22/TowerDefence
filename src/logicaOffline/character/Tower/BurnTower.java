package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletBurn;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class BurnTower extends AbstractTower{
	public BurnTower(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=5200;
		fireRange=4.0;
        timeToFire=0;
        powerFire=300;
	}

	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower  && (timeToFire == 0))
			{
				timeToFire = 15;
				AbstractBullet bullet=new BulletBurn(getX(), getY(), this);
				world.getBulletList().add(bullet);
				 new AePlayWave("sound/burn.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					timeToFire = 10;
					AbstractBullet bullet=new BulletBurn(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet);  
					powerFire=(int)bullet.getDamage();
					 new AePlayWave("sound/burn.wav").start();
				}

			}
		}
		if ( timeToFire > 0){
			timeToFire--;
		}


	}


}
