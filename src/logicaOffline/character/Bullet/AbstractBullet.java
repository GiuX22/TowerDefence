package logicaOffline.character.Bullet;

import logicaOffline.character.Tower.AbstractTower;
import logicaOffline.common.AbstractStaticObject;

public abstract class AbstractBullet extends AbstractStaticObject{

	protected double speed;
	protected double damage;
	protected boolean life;
	protected AbstractTower tower;
	private double x_addition;
	private double y_addition;





	public AbstractBullet(double x,double y,AbstractTower Tower) {
		super(x,y);
		this.tower=Tower;
		life=true;
	}

	//SET METHOD

	public void setPower(double powerBullet)
	{
		damage=powerBullet;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	//GET METHOD

	public double getDamage() {
		return damage;
	}


	public double getSpeed() {
		return speed;
	}


	public boolean isLife() {
		return life;
	}

	protected void moveBullet()
	{
		x_addition=0.0;
		y_addition=0.0;
		double nextX=0.0;
		double nextY=0.0;

		if(tower.getMonsterTarget().getX()>getX())
		{
			x_addition=speed;
		}
		if(tower.getMonsterTarget().getX()<getX())
		{
			x_addition=-speed;
		}

		if(tower.getMonsterTarget().getX()==getX())
		{
			if(tower.getMonsterTarget().getY()>getY())
			{
				y_addition=speed;
			}
			else
			{
				y_addition=-speed;
			}

		}

		if(getX()!=tower.getMonsterTarget().getX()  )
		{
			nextX=getX()+ x_addition;
			nextY=((nextX-getX())/(tower.getMonsterTarget().getX()-getX()))*(tower.getMonsterTarget().getY()-getY())+getY();
			this.setX(nextX);
			this.setY(nextY);
		}else
		{if(getY()!=tower.getMonsterTarget().getY())
		{
			nextY=getY()+ (y_addition*2);
			nextX=getX();
			this.setX(nextX);
			this.setY(nextY);
		}}

	}

	protected boolean targetHit()
	{
		if(Math.abs(tower.getMonsterTarget().getX()-getX())<=0.20 && tower.getMonsterTarget().getX()!=getX() ){return true;}
		if(Math.abs(tower.getMonsterTarget().getY()-getY())<=0.20 && tower.getMonsterTarget().getY()!=getY()){return true;}

		return false;
	}


	public abstract void update();
}
