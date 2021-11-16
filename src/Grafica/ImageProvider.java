package Grafica;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Monster.MonsterNormal;

public class ImageProvider {

	private static final boolean False = false;
	private Image programIcon;
	private Toolkit toolkit;
	private String path;
	private MediaTracker mediaTracker;
	private int imageId;
	
	private Map<String, Image> images;
	private Map<String, Long> worldPreviewInfo;
	
	private static ImageProvider imageProvider;
	
	public static ImageProvider getInstance(JFrame frame)
	{
		if(imageProvider == null)
			imageProvider = new ImageProvider(frame);
		
		return imageProvider;
	}
	
	public ImageProvider(JFrame frame) {
		
		images = new HashMap<String,Image>();
		worldPreviewInfo=new HashMap<String,Long>();
		toolkit = Toolkit.getDefaultToolkit();
		imageId=0;
		path = "./Img/";
		
		mediaTracker = new MediaTracker(frame);
		
		programIcon = toolkit.getImage(path + "ProgramIcon.png");
		programIcon = programIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH );
		mediaTracker.addImage(programIcon, 0);
		
		try {
			
			mediaTracker.waitForAll();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public Image getProgramIcon() {
		return programIcon;
	}
	
	
	
	public Image getImage (String imageName)
	{
		if( images.containsKey(imageName) && !imageName.contains("WorldPreview/"))
		{
			return images.get(imageName);
		}
		
		if(imageName.contains("WorldPreview/")){
			if(!isWorldPreviewModified(imageName)){
				return images.get(imageName);
			}
			else{
				
					BufferedImage newImage=null;
					try {
						newImage = ImageIO.read(new File("./Img/"+imageName));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					images.put(imageName,newImage);
					return newImage;
				
			}
		}
		
		Image image = toolkit.getImage(path + imageName);

		mediaTracker.addImage(image, 0);
		
		try {
			
			this.mediaTracker.waitForAll();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		images.put(imageName, image);

		
		
		return image;
	}
	
	private boolean isWorldPreviewModified(String imageName) {
		
		File file=new File("./Img/"+imageName);
		long lastModified=file.lastModified();
		
		if(worldPreviewInfo.containsKey(imageName)){
			if(worldPreviewInfo.get(imageName)==lastModified){
				return false;}
			else{
				worldPreviewInfo.put(imageName,lastModified);
				return true;
			}
		}else{
			System.out.println("carico");
			worldPreviewInfo.put(imageName,lastModified);
		}
		return true;
	}

	public Image getImageMonster(AbstractMonster monster)
	{
		if( images.containsKey(monster.getClass().getSimpleName()+monster.getDirection()+monster.getContAnimation()+".png"))
		{
			return images.get(monster.getClass().getSimpleName()+monster.getDirection()+monster.getContAnimation()+".png");
		}
		
		Image image = toolkit.getImage(path +"Monsters/"+ monster.getClass().getSimpleName()+monster.getDirection()+monster.getContAnimation()+".png");
		mediaTracker.addImage(image, 0);
		
		try {
			
			this.mediaTracker.waitForAll();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		images.put(monster.getClass().getSimpleName()+monster.getDirection()+monster.getContAnimation()+".png", image);
		
		return image;
	}

	
}
