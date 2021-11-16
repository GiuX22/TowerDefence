package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletNormal;
import logicaOffline.character.Bullet.BulletSlow;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class SlowTower extends AbstractTower{
	
	
	public SlowTower(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=600;
		fireRange=1.0;
        timeToFire=0;
        powerFire=7;
	}

	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower && (timeToFire == 0))
			{
				timeToFire = 10;
				AbstractBullet bullet=new BulletSlow(getX(), getY(), this);
				world.getBulletList().add(bullet);
				new AePlayWave("sound/slow.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					AbstractBullet bullet=new BulletNormal(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet); 
					powerFire=(int)bullet.getDamage();
					new AePlayWave("sound/slow.wav").start();

				}

			}
		}
		if ( timeToFire > 0){
			timeToFire--;
		}


	}

	

}
