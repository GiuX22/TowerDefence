
package logicaOffline.utility;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenshotMaker {

	private BufferedImage img;
	private Robot robot;
	private Toolkit toolkit;
	private Dimension size;
	private static Rectangle rec;
	
	
	public ScreenshotMaker()  {
		
		try {
			robot = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}
		
		toolkit = Toolkit.getDefaultToolkit();
		size = toolkit.getScreenSize();

		
	
	}


	
	public void createScreenshot()
	{
		img = robot.createScreenCapture(rec);
	}
	
	
	public void saveScreenshot(String worldName)
	{
		try {
			ImageIO.write(img, "png", new File("./Img/WorldPreview/"+worldName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void setRectangle(Rectangle bounds) {
		rec = bounds;
		
	}
	
	

}

