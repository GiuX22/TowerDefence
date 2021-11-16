
package logicaOffline.world;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import logicaOffline.Manager.GameManager;
import logicaOffline.character.AbstractCharacter;
import logicaOffline.character.Bullet.AbstractBullet;
import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Tower.AbstractTower;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.utility.TestPath;



public class WorldImpl implements World
{

	private List<AbstractMonster> monsters;
	private List<AbstractTower> towers;
	private List<AbstractBullet> bullets;
	private Base base;
	private Generator generator;
	private final AbstractStaticObject[][] worldMatrix;
	private List<AbstractStaticObject> worldListSort; 

	private int lifePlayer;
	private double score;
	private int countKillMonsters;
	private boolean isLevelOver;

    private TestPath testPath;



	public WorldImpl(final AbstractStaticObject[][] world) {

		worldMatrix=world;
		worldListSort=new ArrayList<AbstractStaticObject>();
		monsters= new CopyOnWriteArrayList<AbstractMonster>();
		towers= new CopyOnWriteArrayList<AbstractTower>();
		bullets=new CopyOnWriteArrayList<AbstractBullet>();

		lifePlayer=3;
		score=2000;
		countKillMonsters=0;
		isLevelOver=false;

		testPath=new TestPath();
		testPath.initTest(getMatrixInt());
		findPath();


	}

	
	@Override
	public void setLevelOver(boolean level) {
		this.isLevelOver = level;
	}

	@Override
	public boolean isLevelOver() {
		return isLevelOver;
	}

	public void findPath()
	{


		for (int i=0 ; i<worldMatrix.length;i++)
		{
			for(int j=0 ; j<worldMatrix[i].length;j++)
			{
				if(worldMatrix[i][j] instanceof Base)
				{
					worldListSort.add(worldMatrix[i][j]);
					this.base=(Base)worldMatrix[i][j];
				}

				if(worldMatrix[i][j] instanceof Generator)
				{
					worldListSort.add(worldMatrix[i][j]);
					this.generator=(Generator)worldMatrix[i][j];
				}

				if(worldMatrix[i][j] instanceof Street)
				{
					worldListSort.add(worldMatrix[i][j]);
				}



			}
		}




	}
	public int[][] getMatrixInt()
	{
		int[][] matrix=new int[15][25];

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 25; j++) {
				
				if(worldMatrix[i][j] instanceof Street)
				{
					matrix[i][j]=1;
				}
				if(worldMatrix[i][j] instanceof Generator)
				{
					matrix[i][j]=2;
				}
				if(worldMatrix[i][j] instanceof Base)
				{
					matrix[i][j]=3;
				}
			}
		}
		return matrix;
	}

	public ArrayList<AbstractStaticObject> getPathWorld(){
		return (ArrayList<AbstractStaticObject>) worldListSort;
	}

	@Override
	public AbstractStaticObject getCell(int x, int y) {
		return worldMatrix[x][y];
	}

	public AbstractStaticObject getBase() {
		return base;
	}

	public AbstractStaticObject getGenerator() {
		return generator;
	}


	public void settingWorld()
	{

		for (AbstractStaticObject[] staticObjects : worldMatrix) {
			for (AbstractStaticObject staticObject : staticObjects) {

				if(staticObject instanceof Generator){this.generator=(Generator) staticObject;}
				if(staticObject instanceof Base){this.base=(Base) staticObject;
				}
			}	
		}
	}

	@Override
	public AbstractStaticObject[] getPathMonster() {
		
		return testPath.getRandomPath();
	}
	@Override
	public CopyOnWriteArrayList<AbstractBullet> getBulletList() {
		return (CopyOnWriteArrayList<AbstractBullet>) bullets;
	}
	@Override
	public CopyOnWriteArrayList<AbstractMonster> getMonsterList() {
		return (CopyOnWriteArrayList<AbstractMonster>) monsters;
	}

	@Override
	public CopyOnWriteArrayList<AbstractTower> getTowerList() {
		return (CopyOnWriteArrayList<AbstractTower>) towers;
	}


	public void setScore(int point)
	{
		score+=point;
	}
    public void reset()
    {
    	lifePlayer=3;
		score=2000;
		countKillMonsters=0;
		isLevelOver=false;
    }
	public int getLifePlayer()
	{return lifePlayer;}
 
	public int getCountKillMonster()
	{return countKillMonsters;}

	public void decreaseLifePlayer()
	{
		if(lifePlayer>0)
			lifePlayer--;
	}
	
	public void decreaseMoneyPlayer(double money)
	{
		if(score >= money)
		score=score-money;
	}


	@Override
	public double getScore() {
		return score;
	}


	@Override
	public void incrementScore(int scoreMonster) {
		this.score+=scoreMonster;
		
	}


	@Override
	public void resetLife() {
		this.lifePlayer=0;
		
	}
}
