package logicaOffline.utility;

public class TowerInfo {


	private int x;
	private int y;
	private double range;
	private int cost;
	private int damage;
	private String type;
	private boolean updateFireTower;
	private int updateFireCost;
	private int updateRangeCost;


	private boolean updateRangeTower;


	private int id;

	public TowerInfo(int x,int y,String type,double range,int id,int cost,int damage) {
		this.x=x;
		this.y=y;
		this.range=range;
		this.id=id;
		this.cost=cost;
		this.type=type;
		this.damage=damage;
		updateFireCost=cost*3;
		updateRangeCost=cost*5;
		updateFireTower=false;
	    updateRangeTower=false;
	}

	public TowerInfo(String type,double range,int cost,int damage) {

		this.range=range;
		this.cost=cost;
		this.type=type;
		this.damage=damage;
	}
	public String getType()
	{return type;}

	public int getX() {
		return x;
	}
	public double getRange() {
		return range;
	}

	public int getCost() {
		return cost;
	}

	public int getDamage() {
		return damage;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public int getUpdateFireCost() {
		return updateFireCost;
	}

	public int getUpdateRangeCost() {
		return updateRangeCost;
	}

	public void setId(int id) {
		this.id = id;
	}
	public boolean isUpdateFireTower() {
		return updateFireTower;
	}

	public void setUpdateFireTower(boolean updateFireTower) {
		this.updateFireTower = updateFireTower;
	}

	public void setRange(double range) {
		this.range += range;
	}

	public void setDamage(int damage) {
		this.damage += damage;
	}

	public boolean isUpdateRangeTower() {
		return updateRangeTower;
	}

	public void setUpdateRangeTower(boolean updateRangeTower) {
		this.updateRangeTower = updateRangeTower;
	}


	public String getInfo()
	{
		return getType()+":"+x+":"+y+":"+id+":"+getDamage()+":"+getRange()+":"+getCost();
	}
}
