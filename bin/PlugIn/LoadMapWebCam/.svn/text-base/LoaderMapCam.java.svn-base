package PlugIn.LoadMapWebCam;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.imageio.ImageIO;

import Grafica.EditorPanel;
import Grafica.MainFrame;


public class LoaderMapCam {

	private CyclicBarrier barrier;
	private int[][] matrix=new int[15][25];
	private EditorPanel editor;

	public LoaderMapCam(EditorPanel e) {
		editor=e;
		barrier=new CyclicBarrier(2);
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 25; j++) {
				matrix[i][j]=0;
			}
		}
	}

	public void loadMap()
	{
		Thread loader=new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					CaptureImg gs = new CaptureImg(barrier);
					barrier.await();
					if(gs.isLoaded()){
						BufferedImage[][] splitImages=splitImage("Img/threshold.jpg");
						pixelToMatrix(splitImages);
						editor.setMatrix(matrix);
						editor.repaint();}
				} catch (InterruptedException | BrokenBarrierException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		loader.start();

	}

	private BufferedImage[][] splitImage(String path) throws IOException
	{

		File file = new File(path); // I have bear.jpg in my working directory  
		FileInputStream fis = new FileInputStream(file);  
		BufferedImage image = ImageIO.read(fis); //reading the image file  
		BufferedImage imageR; 
		imageR=resize(image, 648, 511);

		BufferedImage imageS=imageR.getSubimage(110,150, 450, 350);

		int rows = 15; //You should decide the values for rows and cols variables  
		int cols = 25;  
		//int chunks = rows * cols;  

		int chunkWidth = imageS.getWidth() / cols; // determines the chunk width and height  
		int chunkHeight = imageS.getHeight() / rows;   
		BufferedImage imgs[][] = new BufferedImage[rows][cols]; //Image array to hold image chunks  
		for (int x = 0; x < rows; x++) {  
			for (int y = 0; y < cols; y++) {  
				//Initialize the image array with image chunks  
				imgs[x][y] = new BufferedImage(chunkWidth, chunkHeight, imageS.getType());  

				// draws the image chunk  
				Graphics2D gr = imgs[x][y].createGraphics();  
				gr.drawImage(imageS, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
				gr.dispose();  
			}  
		}  


		return imgs;
	}


	private static BufferedImage resize(BufferedImage img, int newW, int newH) {  
		int w = img.getWidth();  
		int h = img.getHeight();  
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
		Graphics2D g = dimg.createGraphics();  
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
		g.dispose();  
		return dimg;  
	}  


	private void pixelToMatrix(BufferedImage[][] images)
	{
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 25; j++) {
				double numWhitePixel=counterWhitePixel(images[i][j]);
				if(numWhitePixel>=33)
				{
					matrix[i][j]=1;
				}
			}
		}
	}

	public double counterWhitePixel(BufferedImage image)
	{
		BufferedImage mapa=image;

		final int xmin = mapa.getMinX();
		final int ymin = mapa.getMinY();

		double pixelWhite=0;

		final int ymax = ymin + mapa.getHeight();
		final int xmax = xmin + mapa.getWidth();
		final double totalPixel=xmax*ymax;


		for (int i = xmin;i<xmax;i++)
		{
			for (int j = ymin;j<ymax;j++)
			{

				int pixel = mapa.getRGB(i, j);

				if ((pixel & 0x00FFFFFF) != 0)
				{
					pixelWhite++;
				}
			}
		}

		return (pixelWhite/totalPixel)*100;

	}



}

