package Grafica;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import logicaOffline.common.AbstractStaticObject;
import logicaOffline.world.Base;
import logicaOffline.world.Generator;
import logicaOffline.world.Street;
import logicaOffline.world.World;


@SuppressWarnings("serial")
public class WorldPanel extends JPanel {
	
	ImageProvider imageProvider;
	Generator objectGenerator;
	Type objectType;
	
	public Type getObjectType() {
		return objectType;
	}


	public void setObjectType(Type objectType) {
		this.objectType = objectType;
	}


	AbstractStaticObject[] monsterPath;
	
	
	public WorldPanel(ImageProvider imageProvider,World world) {
		super();
		this.imageProvider=imageProvider;
		monsterPath=world.getPathMonster();
		objectGenerator = (Generator) world.getGenerator();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Image img = imageProvider.getImage("Grass.jpg");
		
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		
		Image street = imageProvider.getImage("Street.png");
		Image generator = imageProvider.getImage("Generator.png");
		Image base = imageProvider.getImage("Base.png");
		
		for (int i = 0; i < monsterPath.length; i++) 
		{
			
			
			if (monsterPath[i] instanceof Street) 
			{
				g.drawImage(street, (int) (monsterPath[i].getX()*48), (int) (monsterPath[i].getY()*48), 48, 48, null);
			}
			
			if (monsterPath[i] instanceof Base)
			{
				g.drawImage(base, (int) (monsterPath[i].getX()*48), (int) (monsterPath[i].getY()*48), 48, 48, null);
			}
		}
		
		g.drawImage(generator, (int) (objectGenerator.getX()*48), (int) (objectGenerator.getY()*48), 48, 48, null);
		
		


	}
	
	
}
