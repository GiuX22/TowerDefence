package logicaOffline.character.Tower;

import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Bullet.BulletNuclear;
import logicaOffline.utility.AePlayWave;
import logicaOffline.world.World;

public class NuclearTower extends AbstractTower{
	public NuclearTower(int x, int y,World world) {
		super(x,y,world);
		this.setX(x);
		this.setY(y);
		cost=700;
		fireRange=2.5;
		powerFire=50;

		// TODO Auto-generated constructor stub
	}

	public void update()
	{
		super.update();
		if(TargetChanged)
		{
			if(!changePower  && (timeToFire == 0))
			{
				timeToFire=15;
				AbstractBullet bullet=new BulletNuclear(getX(), getY(), this);
				world.getBulletList().add(bullet);
				 new AePlayWave("sound/nuclear.wav").start();
			}
			else
			{
				if(changePower && (timeToFire == 0))
				{
					
					timeToFire=10;

					AbstractBullet bullet=new BulletNuclear(getX(), getY(), this,powerBullet);
					world.getBulletList().add(bullet); 
					powerFire=(int)bullet.getDamage();
					 new AePlayWave("sound/nuclear.wav").start();
				}

			}
		}

		if ( timeToFire > 0){
			timeToFire--;
		}


	}


}
