package logicaOffline.world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import logicaOffline.character.AbstractCharacter;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.common.StaticObject;

public class WorldManager {




	public WorldManager() {

	}

	public World getNextWorld(String world) throws IOException
	{
		return new WorldImpl(loadWorld(world));
	}



	private AbstractStaticObject getObject(final int parseInt, final int row, final int col)
	{
		switch (parseInt)
		{
		case 0:
			return new Empty(col, row);
		case 1:
			return new Street(col, row);
		case 2:
			return new Generator(col, row);
		case 3:
			return new Base(col, row);
		default:
			throw new RuntimeException("type not supported: " + parseInt + " in position: " + row + ":" + col);
		}
	}

	public AbstractStaticObject[][] loadWorld(String world) 
	{
		final AbstractStaticObject[][] defaultWorld = new AbstractStaticObject[25][25];
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("FILEWORLD/"+world));

			String buffer;
			int row = 0;
			while ((buffer = br.readLine()) != null)
			{
				for (int col = 0; col < buffer.length(); col++)
				{
					defaultWorld[row][col] = getObject(Integer.parseInt(buffer.substring(col, col + 1)), row, col);
				}
				row++;
			}

			br.close();



		}catch (Exception e) {
			throw new RuntimeException("File not found");
		}
		setDirectionStreet(defaultWorld);
		return defaultWorld;
	}

	private boolean isFull(AbstractStaticObject object)
	{
		return (object instanceof Street || object instanceof Base || object instanceof Generator);
	}
	private boolean isEmpty(AbstractStaticObject object)
	{return object instanceof Empty;}

	private void setDirectionStreet(AbstractStaticObject[][] matrix)
	{
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 25; j++) {

				if(matrix[i][j] instanceof Street  )
				{
					if(i!=0 && i!=14 && j!=0 && j!=24){
						if(isEmpty(matrix[i+1][j]) &&  isEmpty(matrix[i-1][j]) &&  isEmpty(matrix[i][j+1] ) &&  isEmpty(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("C");
						}
						if(isFull(matrix[i+1][j]) &&  isFull(matrix[i-1][j]) && isFull(matrix[i][j+1] ) &&  isFull(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("C");
						}
						if(isFull(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isEmpty(matrix[i][j+1] ) && isEmpty(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("V");
						}
						if(isEmpty(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isFull(matrix[i][j+1] ) &&  isFull(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("O");
						}
						if( isEmpty(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isEmpty(matrix[i][j+1] ) && isFull(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("LU");
						}
						if(isFull(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isEmpty(matrix[i][j+1] ) && isFull(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("LD");
						}
						if(isFull(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isFull(matrix[i][j+1] ) && isEmpty(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("RD");
						}
						if(isEmpty(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isFull(matrix[i][j+1] ) && isEmpty(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("RU");
						}
						if(isEmpty(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isFull(matrix[i][j+1] ) &&  isFull(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("TU");
						}
						if(isFull(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isFull(matrix[i][j+1] ) && isFull(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("TD");
						}
						if(isFull(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isEmpty(matrix[i][j+1] ) && isFull(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("TL");
						}
						if(isFull(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isFull(matrix[i][j+1] ) && isEmpty(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("TR");
						}
						if(isEmpty(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isFull(matrix[i][j-1])&& isEmpty(matrix[i][j+1]))
						{
							((Street)matrix[i][j]).setType("CR");
						}
						if(isEmpty(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isFull(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("CL");
						}
						if(isFull(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isEmpty(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("CU");
						}
						if(isEmpty(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isEmpty(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
						{
							((Street)matrix[i][j]).setType("CD");
						}
						
					}
					else // cornice
					{
						if(i==0 && j!=0 && j!=24)// firts row
						{
							if(isEmpty(matrix[i+1][j])  &&  isEmpty(matrix[i][j-1] ) &&  isEmpty(matrix[i][j+1] ) )
							{
								((Street)matrix[i][j]).setType("C");
							}
							if(isEmpty(matrix[i+1][j])  &&  isFull(matrix[i][j-1] ) &&  isFull(matrix[i][j+1] ) )
							{
								((Street)matrix[i][j]).setType("O");
							}
							if(isFull(matrix[i+1][j])  &&  isFull(matrix[i][j-1] ) &&  isFull(matrix[i][j+1] ) )
							{
								((Street)matrix[i][j]).setType("TD");
							}
							if(isFull(matrix[i+1][j])  && isEmpty(matrix[i][j+1] ) && isFull(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("LD");
							}
							if(isFull(matrix[i+1][j])  && isFull(matrix[i][j+1] ) && isEmpty(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("RD");
							}
							if(isEmpty(matrix[i+1][j])  && isFull(matrix[i][j-1])&& isEmpty(matrix[i][j+1]))
							{
								((Street)matrix[i][j]).setType("CR");
							}
							if(isEmpty(matrix[i+1][j])  && isFull(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("CL");
							}
							if(isFull(matrix[i+1][j])  && isEmpty(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("CU");
							}
							if(isEmpty(matrix[i+1][j])  && isEmpty(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("CD");
							}
							

						}
						if(i==0 && j==0) // cell[0][0]
						{
							if(isEmpty(matrix[i+1][j])  &&  isEmpty(matrix[i][j+1] ) )
							{
								((Street)matrix[i][j]).setType("C");
							}
							if(isFull(matrix[i+1][j])  && isFull(matrix[i][j+1] ))
							{
								((Street)matrix[i][j]).setType("RD");
							}
						}
						if(i==0 && j==24)// cell[0][24]
						{
							if(isEmpty(matrix[i+1][j])  &&  isEmpty(matrix[i][j-1] ) )
							{
								((Street)matrix[i][j]).setType("C");
							}
							if(isFull(matrix[i+1][j])  && isFull(matrix[i][j-1] ))
							{
								((Street)matrix[i][j]).setType("LD");
							}
						}

						if(i==14)// last row
						{

							if(j==0)//cell[14][0]
							{
								if(isEmpty(matrix[i-1][j]) &&  isEmpty(matrix[i][j+1] ))
								{
									((Street)matrix[i][j]).setType("C");
								}
								if(isFull(matrix[i-1][j]) &&  isFull(matrix[i][j+1] ))
								{
									((Street)matrix[i][j]).setType("RU");
								}
							}
							else if(j==24)//cell[14][24]
							{
								if(isEmpty(matrix[i-1][j]) &&  isEmpty(matrix[i][j-1] ))
								{
									((Street)matrix[i][j]).setType("C");
								}
								if(isFull(matrix[i-1][j]) &&  isFull(matrix[i][j-1] ))
								{
									((Street)matrix[i][j]).setType("LU");
								}
							}
							else
							{
								if(isEmpty(matrix[i-1][j]) &&  isEmpty(matrix[i][j-1])  &&  isEmpty(matrix[i][j+1]))
								{
									((Street)matrix[i][j]).setType("C");
								}
								if(isFull(matrix[i][j-1]) && isFull(matrix[i][j+1] ))
								{
									((Street)matrix[i][j]).setType("O");
								}
								if(  isFull(matrix[i-1][j]) && isEmpty(matrix[i][j+1] ) && isFull(matrix[i][j-1]))
								{
									((Street)matrix[i][j]).setType("LU");
								}
								if( isFull(matrix[i-1][j]) && isFull(matrix[i][j+1] ) && isEmpty(matrix[i][j-1]))
								{
									((Street)matrix[i][j]).setType("RU");
								}
								if(isFull(matrix[i-1][j]) && isFull(matrix[i][j+1] ) &&  isFull(matrix[i][j-1]))
								{
									((Street)matrix[i][j]).setType("TU");
								}
								if( isEmpty(matrix[i-1][j]) && isFull(matrix[i][j-1])&& isEmpty(matrix[i][j+1]))
								{
									((Street)matrix[i][j]).setType("CR");
								}
								if( isEmpty(matrix[i-1][j]) && isFull(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
								{
									((Street)matrix[i][j]).setType("CL");
								}
								if( isEmpty(matrix[i-1][j]) && isEmpty(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
								{
									((Street)matrix[i][j]).setType("CU");
								}
								if( isFull(matrix[i-1][j]) && isEmpty(matrix[i][j+1])&& isEmpty(matrix[i][j-1]))
								{
									((Street)matrix[i][j]).setType("CD");
								}
							}
						}

						if(j==0 && i!=0 && i!=14)//first column
						{
							if(isEmpty(matrix[i-1][j]) &&  isEmpty(matrix[i+1][j])  &&  isEmpty(matrix[i][j+1]))
							{
								((Street)matrix[i][j]).setType("C");
							}
							if(isFull(matrix[i-1][j])&& isFull(matrix[i+1][j]) && isEmpty(matrix[i][j+1] ))
							{
								((Street)matrix[i][j]).setType("V");
							}
							if(isFull(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isFull(matrix[i][j+1] ) )
							{
								((Street)matrix[i][j]).setType("RD");
							}
							if(isEmpty(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isFull(matrix[i][j+1] ) )
							{
								((Street)matrix[i][j]).setType("RU");
							}
							if(isFull(matrix[i-1][j])&& isFull(matrix[i+1][j])&& isFull(matrix[i][j+1] ))
							{
								((Street)matrix[i][j]).setType("TR");
							}
							if(isEmpty(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isEmpty(matrix[i][j+1]))
							{
								((Street)matrix[i][j]).setType("CR");
							}
							if(isEmpty(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isFull(matrix[i][j+1]))
							{
								((Street)matrix[i][j]).setType("CL");
							}
							if(isFull(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isEmpty(matrix[i][j+1]))
							{
								((Street)matrix[i][j]).setType("CU");
							}
							if(isEmpty(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isEmpty(matrix[i][j+1]))
							{
								((Street)matrix[i][j]).setType("CD");
							}
						}

						if(j==24 && i!=0 && i!=14)//last column
						{
							if(isEmpty(matrix[i-1][j]) &&  isEmpty(matrix[i+1][j])  &&  isEmpty(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("C");
							}
							if(isFull(matrix[i-1][j]) && isFull(matrix[i+1][j]) && isEmpty(matrix[i][j-1] ))
							{
								((Street)matrix[i][j]).setType("V");
							}
							if( isEmpty(matrix[i+1][j]) && isFull(matrix[i-1][j])  && isFull(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("LU");
							}
							if(isFull(matrix[i+1][j]) && isEmpty(matrix[i-1][j])  && isFull(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("LD");
							}
							if(isFull(matrix[i-1][j]) && isFull(matrix[i+1][j]) && isFull(matrix[i][j-1] ))
							{
								((Street)matrix[i][j]).setType("TL");
							}
							if(isEmpty(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isFull(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("CR");
							}
							if(isEmpty(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isEmpty(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("CL");
							}
							if(isFull(matrix[i+1][j]) && isEmpty(matrix[i-1][j]) && isEmpty(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("CU");
							}
							if(isEmpty(matrix[i+1][j]) && isFull(matrix[i-1][j]) && isEmpty(matrix[i][j-1]))
							{
								((Street)matrix[i][j]).setType("CD");
							}
						}

					}
				}
			}
		}
	}

}




