package PlugIn.LoadMapWebCam;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.CyclicBarrier;
import javax.swing.JFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;


public class CaptureImg extends JFrame implements Runnable {

	private CyclicBarrier barrier;
	private boolean exitFromCam=false;
	private IplImage image;
	private PanelCam panel;
	private static final long serialVersionUID = 1L;
	private boolean loaded;
	BufferedImage img = null;
	
	//color range of red like color
	static CvScalar min = cvScalar(0,0,0, 0);//BGR-A
	static CvScalar max;
	private int Rmax=50,Gmax=50,Bmax=50;
	private IplImage imgThreshold;


	public CaptureImg(CyclicBarrier b){
		//canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		barrier=b;
		loaded=false;
		panel=new PanelCam();
        this.setVisible(true);
        //this.setSize(648, 511);
        this.setBounds(300, 100, 646,509);
        this.setResizable(false);
        this.add(panel);
        
        this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent we) {
				exitFromCam=true;
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {

				exitFromCam=true;
				loaded=true;
				cvSaveImage("Img/threshold.jpg", imgThreshold);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent ke) {


			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == KeyEvent.VK_ENTER)
				{
					exitFromCam=true;
					loaded=true;
					cvSaveImage("Img/threshold.jpg", imgThreshold);
				}

			}
		});

		this.addMouseWheelListener(new MouseWheelListener() {

			
			@Override
			public void mouseWheelMoved(MouseWheelEvent me) {
				if(me.getWheelRotation()<0)
				{
					if(Rmax>=10)
					{
						Rmax-=5;
						Gmax=Bmax=Rmax;
					}
				}
				else
				{
					if(Rmax<=250)
					{
						Rmax+=5;
						Gmax=Bmax=Rmax;
					}
				}

			}
		});
       Thread t=new Thread(this);
       t.start();
	}
	
	@Override
	public void run() {
		FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera

		try {
			grabber.start();
			IplImage img;
			while (!exitFromCam) {
				img = grabber.grab();
				if (img != null) {

					max= cvScalar(Rmax, Gmax,Bmax, 0);

					IplImage gry = cvCreateImage(cvGetSize(img), img.depth(), 1);
					cvCvtColor(img, gry, CV_BGR2GRAY);
					imgThreshold = cvCreateImage(cvGetSize(img),8, 1);
					//  apply thresholding
					cvInRangeS(gry, min, max, imgThreshold );
					// smooth filter- median
					cvSmooth(imgThreshold ,imgThreshold, CV_MEDIAN, 13);

					IplImage imageShow=imgThreshold;
					panel.setImage(imageShow.getBufferedImage());
					panel.repaintPanel();
					
				}
			}
			barrier.await();
			this.setVisible(false);
			this.dispose();
			grabber.stop();
		} catch (Exception e) {
		}
	}
   
	public boolean isLoaded()
	{return loaded;}
}
