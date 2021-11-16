package logicaOffline.utility;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.world.Base;
import logicaOffline.world.Generator;
import logicaOffline.world.Street;

public class TestPath {

	private int [][] worldMatrix = new int[15][25];
	private final int minStreet = 15;
	private boolean base;
	private boolean generator;
	private boolean streets;

	private boolean thereAreTightCurves;
	private boolean thereAreSolitaryPieces;

	private boolean tightCurveFound;
	private List<AbstractStaticObject> worldList; 
	private List< LinkedList<AbstractStaticObject> > totalPaths; 

	Graph worldGraph;
	LinkedList<AbstractStaticObject> visited;

	private Base baseObject;
	private int genetatorIndex;

	private int countPaths;

	private List<AbstractStaticObject> tightCurves;
	private List<AbstractStaticObject> blindAlleys;
	private List<AbstractStaticObject> piecesSolitary;



	public TestPath()
	{

		worldList = new ArrayList<AbstractStaticObject>();
		worldGraph = new Graph();
		visited = new LinkedList<AbstractStaticObject>();
		totalPaths = new ArrayList< LinkedList<AbstractStaticObject> >();
		countPaths = 0;
		tightCurves = new ArrayList<AbstractStaticObject>();
		blindAlleys = new ArrayList<AbstractStaticObject>();
		piecesSolitary = new ArrayList<AbstractStaticObject>();
		thereAreTightCurves = false;
		thereAreSolitaryPieces = false;
		tightCurveFound = false;

	}


	public void initTest(int [][] matrix)
	{

		worldMatrix = matrix;
		worldList.clear();
		worldGraph = new Graph();
		visited.clear();
		totalPaths.clear();
		countPaths = 0;
		tightCurves.clear();
		blindAlleys.clear();
		piecesSolitary.clear();

		thereAreTightCurves = false;
		thereAreSolitaryPieces = false;

		if(thereAreAllPieces())
		{
			for (int i = 0; i < worldList.size(); i++) {
				if(worldList.get(i) instanceof Generator)
					genetatorIndex = i;
				findNearPieces(worldList.get(i));
			}

			totalPaths.clear();
			visited.add(worldList.get(genetatorIndex));





			if(tightCurves.isEmpty())
			{
				generateAllPath(worldGraph,visited);
				checkProblems();
			}
			else
			{
				thereAreTightCurves = true;
			}






		}

	}


	private void checkProblems() {
		// TODO Auto-generated method stub

		for (int i = 0; i < worldMatrix.length; i++) 
		{
			for (int j = 0; j < worldMatrix[i].length; j++) 
			{


				if ( worldMatrix[i][j] == 1 && !totalPaths.isEmpty())
				{
					if (!isPresent(i,j))
					{
						piecesSolitary.add(new Street(i, j));
					}
				}



			}
		}



		if (!piecesSolitary.isEmpty())
		{
			thereAreSolitaryPieces = true;
		}

		for (int i = 0; i < totalPaths.size(); i++) {
			if (totalPaths.get(i).size() < 15)
			{
				streets = false;
			}
		}

	}

	private boolean isPresent(int j, int i) {


		for(int x = 0 ; x < totalPaths.size() ; x++)
		{
			for (int y = 0; y < totalPaths.get(x).size(); y++) {
				if(totalPaths.get(x).get(y).getX() == i && totalPaths.get(x).get(y).getY() == j)
				{
					return true;
				}
			}
		}

		return false;
	}


	private void generateAllPath(Graph graph,LinkedList<AbstractStaticObject> visited) 
	{

		LinkedList<AbstractStaticObject> nodes = graph.adjacentNodes(visited.getLast());

		for (AbstractStaticObject node : nodes)
		{

			if(visited.contains(node))
			{
				continue;
			}	

			if(node.getX() == baseObject.getX() && node.getY() == baseObject.getY())
			{
				visited.add(node);

				totalPaths.add(new LinkedList<AbstractStaticObject>());
				totalPaths.get(countPaths).addAll(visited);

				countPaths++;


				visited.removeLast();
				break;
			}
		}

		for (AbstractStaticObject node : nodes)
		{
			if (visited.contains(node) || (node.getX() == baseObject.getX() && node.getY() == baseObject.getY())) {
				continue;
			}

			visited.addLast(node);
			generateAllPath(graph, visited);
			visited.removeLast();

		}



		checkCorrectPath();



	}



	private void checkCorrectPath() {
		int toRemove = -1;
		for (int i = 0; i < totalPaths.size(); i++)
		{
			if (totalPaths.get(i).size() == 2 )
			{
				if(totalPaths.get(i).get(1) instanceof Base )
				{
					toRemove = i;
				}
			}
		}
		
		if (toRemove != -1 )
		{
			totalPaths.remove(toRemove);
		}

	}


	//MI DA LA LISTA DEI PEZZI/O VICINO/I A QUELLO CHE GLI PASSO
	private void findNearPieces(AbstractStaticObject object) 
	{
		for (int i = 0; i < worldList.size(); i++) 
		{

			if (object.getX()+1 == worldList.get(i).getX() && object.getY() == worldList.get(i).getY() )
			{
				worldGraph.addTwoWayVertex(worldList.get(i), object);

			}
			if (object.getX()-1 == worldList.get(i).getX() && object.getY() == worldList.get(i).getY() )
			{
				worldGraph.addTwoWayVertex(worldList.get(i), object);

			}
			if (object.getX() == worldList.get(i).getX() && object.getY()+1 == worldList.get(i).getY() )
			{
				worldGraph.addTwoWayVertex(worldList.get(i), object);

			}
			if (object.getX() == worldList.get(i).getX() && object.getY()-1 == worldList.get(i).getY() )
			{
				worldGraph.addTwoWayVertex(worldList.get(i), object);

			}

		}


	}


	boolean thereAreAllPieces ()
	{

		generator = false;
		base = false;
		streets = false;


		int streetPieces = 0;
		for (int i = 0; i < worldMatrix.length; i++) 
		{
			for (int j = 0; j < worldMatrix[i].length; j++) 
			{
				if( worldMatrix[i][j] == 1 )
				{
					worldList.add(new Street(j,i));
					streetPieces++;
				}
				if( worldMatrix[i][j] == 3 )
				{
					worldList.add(new Base(j,i));
					base = true;
					baseObject = new Base(j,i);
				}
				if( worldMatrix[i][j] == 2 )
				{
					worldList.add(new Generator(j,i));
					generator = true;

				}			


				if(i < (worldMatrix.length -1) && j < (worldMatrix[i].length -1) )
				{
					if( worldMatrix[i][j] == 1 && worldMatrix[i+1][j] == 1 && worldMatrix[i][j+1] == 1 && worldMatrix[i+1][j+1] == 1 )
					{

						tightCurveFound = true;

						tightCurves.add(new Street(i, j));
						tightCurves.add(new Street(i+1, j));
						tightCurves.add(new Street(i, j+1));
						tightCurves.add(new Street(i+1, j+1));

					}
				}




			}
		}

		if(streetPieces >= minStreet)
			streets = true;

		if ( streets && base && generator)
			return true;

		return false;

	}


	public AbstractStaticObject[] getRandomPath()
	{
		int tmp=new Random().nextInt(totalPaths.size());
		AbstractStaticObject[] path =new AbstractStaticObject[totalPaths.get(tmp).size()-1];
		for (int i =1; i  <totalPaths.get(tmp).size(); i++) {
			path[i-1]=totalPaths.get(tmp).get(i);

		}
		return path;

	}




	//Funzione Di ulitità
	public int getPicesNumber()
	{
		return worldList.size();
	}


	public List<AbstractStaticObject> getTightCurves() {
		return tightCurves;
	}

	public List<AbstractStaticObject> getPiecesSolitary() {
		return piecesSolitary;
	}

	public int checkPath ()
	{

		//Passi Base per mancanza pezzi! 
		if(!streets && !generator && !base)
			return 4;

		if(!streets && !generator)
			return 5;

		if(!streets && !base)
			return 6;

		if(!generator && !base)
			return 7;

		if(!streets)
			return 1;

		if(!generator)
			return 2;

		if(!base)
			return 3;

		//Passsi successivi per le varie verifiche sul persorso


		if(thereAreTightCurves)
		{
			return 9;
		}

		if(totalPaths.isEmpty())
		{
			return 11;
		}


		if( thereAreTightCurves && thereAreSolitaryPieces)
		{
			return 10;
		}


		if(thereAreSolitaryPieces)
		{
			return 8;
		}




		return 0;
	}

}
