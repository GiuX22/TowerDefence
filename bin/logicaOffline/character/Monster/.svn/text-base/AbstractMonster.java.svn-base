package logicaOffline.character.Monster;

import logicaOffline.character.AbstractCharacter;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.common.HasLife;
import logicaOffline.common.HasScore;
import logicaOffline.world.World;

public abstract class AbstractMonster extends AbstractCharacter implements HasScore,HasLife {

	protected double movementSpeed;
	private int indexOfRoad=0;
	protected int health;
	protected double speed=0;
	protected int life;
	protected boolean blockDown;
	protected int defaultlife;
	private int contAnimation=1;
	private double timeTransition;
	private String direction;

	// GET METHOD
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public double getTimeTransition() {
		return timeTransition;
	}
	public void setTimeTransition(double timeTransition) {
		this.timeTransition = timeTransition;
	}
	public int getContAnimation() {
		return (int) (contAnimation/getTimeTransition());
	}
	public void setContAnimation() {
		if(contAnimation==(getTimeTransition()*5+(getTimeTransition()-1)))
			contAnimation=0;
		else
			contAnimation++;
			
	}
	
	public int getDefaultlife() {
		return defaultlife;
	}
	protected AbstractStaticObject [] path;
	int countBlockDown=0;

	
	public AbstractMonster(double x, double y, World world) {
		super(x, y, world);
		
	}
	
	//SET METHOD
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public void setBlockDown(boolean blockDown) {
		this.blockDown = blockDown;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	//GET METHOD
	
	public double getSpeed() {
		return speed;
	}
	@Override
	public int getLife() {
		return life;
	}
	public double getMovementSpeed() {
		return movementSpeed;
	}
	public int getHealth() {
		return health;
	}


	public boolean isBlockDown() {
		return blockDown;
	}
	public void update()
	{
		
		
		
		if(blockDown==true)
		{
			countBlockDown++;
		}
		
		// questa riga fa si che se il mostro ������������������������������������������������������ stato colpito da un proiettile BlockBullet il mostro verr������������������������������������������������������ bloccato per un solo
		// giro di update e poi riprender������������������������������������������������������ il suo ciclo di vita naturale.
		if(blockDown==true && countBlockDown>=10)
		{
			setBlockDown(false);
			countBlockDown=0;
		}
		
		double finalPositionX=0;
		double finalPositionY=0;
		boolean increaseX=false;
		boolean decreaseX=false;
		boolean increaseY=false;
		boolean decreaseY=false;

		boolean changeX=false;
		boolean changeY=false;
		

		finalPositionX=path[indexOfRoad].getX();

		finalPositionY=path[indexOfRoad].getY();



		if(this.getX() < finalPositionX )
		{
			changeX=true;
			increaseX=true;
		}
		if(this.getX() > finalPositionX )
		{
			changeX=true;
			decreaseX=true;
		}

		if(this.getY() < finalPositionY )
		{
			changeY=true;
			increaseY=true;
		}
		if(this.getY() > finalPositionY )
		{
			changeY=true;
			decreaseY=true;
		}

		if((increaseX==true || increaseY==true) && blockDown==false)
		{
			if(this.getX() < finalPositionX && changeX==true)
			{
				this.setX(this.getX()+speed);
				direction="destra";

			}
			if(this.getX() > finalPositionX)
			{
				this.setX(finalPositionX);
				//indexOfRoad++;
				changeX=false;
				direction="destra";

			}

			if(this.getY() < finalPositionY && changeY==true)
			{
				this.setY(this.getY() + speed);
				direction="sotto";

			}
			if(this.getY() > finalPositionY)
			{
				this.setY(finalPositionY);
				//indexOfRoad++;
				changeY=false;
				direction="sotto";

			}
			if(this.getX()==finalPositionX && this.getY()==finalPositionY)
				indexOfRoad++;
		}

		if((decreaseX==true || decreaseY==true) && blockDown==false)
		{
			if(this.getX() > finalPositionX && changeX==true)
			{
				this.setX(this.getX()-speed);
				direction="sinistra";


			}
			if(this.getX() < finalPositionX)
			{
				this.setX(finalPositionX);
				//indexOfRoad++;
				changeX=false;
				direction="sinistra";

			}

			if(this.getY() > finalPositionY && changeY==true)
			{
				this.setY(this.getY()-speed);
				direction="sopra";


			}
			if(this.getY() < finalPositionY)
			{
				this.setY(finalPositionY);
				//indexOfRoad++;
				changeY=false;
				direction="sopra";


			}
			if(this.getX()==finalPositionX && this.getY()==finalPositionY)
				indexOfRoad++;

		}
				// aggiunta funzionalit������������������������������������������������������ che nel caso in qui il mostro arriv������������������������������������������������������ sulla base quindi all'ultima cella di pathMonster ,la vita
				// del mostro viene settata a 0 e il giocatore perde una vita.
				if(this.getX() == path[path.length-1].getX() && this.getY()==path[path.length-1].getY())
				{
					this.life=0;
					if(this instanceof PerfidiousBoss || this instanceof FrostyBoss ||this instanceof BurnBoss)
					{
						world.resetLife();
					}else
					{
					world.decreaseLifePlayer();	}
				}
				//System.out.println("LIFE M: "+this.getLife());
	}
	
	public void incrementLifeMonster(int life)
	{
		this.life+=life;
		this.defaultlife=this.life;
	}
	

}
