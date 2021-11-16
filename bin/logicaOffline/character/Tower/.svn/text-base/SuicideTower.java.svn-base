package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletSuicide;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class SuicideTower extends AbstractTower{
	public SuicideTower(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=4500;
		fireRange=3.5;
		timeToFire=0;
		powerFire=500;

	}

	@Override
	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower && (timeToFire == 0))
			{
				timeToFire = 15;
				AbstractBullet bullet=new BulletSuicide(getX(), getY(), this);
				world.getBulletList().add(bullet);
				new AePlayWave("sound/suicide.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					timeToFire = 12;

					AbstractBullet bullet=new BulletSuicide(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet);  
					powerFire=(int)bullet.getDamage();
					new AePlayWave("sound/suicide.wav").start();
				}

			}
		}
		if ( timeToFire > 0){
			timeToFire--;
		}

	}


}
