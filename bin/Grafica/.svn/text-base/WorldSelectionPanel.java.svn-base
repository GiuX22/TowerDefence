package Grafica;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logicaOffline.utility.AePlayWave;


@SuppressWarnings("serial")
public class WorldSelectionPanel extends JPanel implements KeyListener,MouseWheelListener,MouseListener{

	File folder ;
	ArrayList<File> listOfFiles;
	MainFrame frame;

	private int indexWorld1;
	private int indexWorld2;
	private int indexWorld3;


	private int ButtonWidth=150;
	private int ButtonHeight=80;

	public Boolean animationUp;
	public Boolean animationDown;
	private int animationWorldPixel;

	public WorldSelectionPanel(final MainFrame mainFrame) {
		folder=new File("./FILEWORLD/");
		listOfFiles = new ArrayList<File>();
		for (File element : folder.listFiles()) {
			if(element.getName().substring((int)(element.getName().length()-3), (int)element.getName().length()).equals("txt"))
			{listOfFiles.add(element);}
		}
		frame=mainFrame;
		this.setSize(frame.getWidth(),frame.getHeight());
		this.addKeyListener(this);
		this.addMouseWheelListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		indexWorld1=0;
		indexWorld2=1;
		indexWorld3=2;

		animationUp=false;
		animationDown=false;
		animationWorldPixel=0;
	}

	private String getFileName(int index)
	{
		String fileWithExt=listOfFiles.get(index).getName();
		String[] fileName=fileWithExt.split("\\.");


		return "WorldPreview/"+fileName[0];
	}
	private AlphaComposite makeComposite(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	private void upListElement()
	{if(indexWorld1==0){
		indexWorld2--;
		indexWorld3--;
		indexWorld1=listOfFiles.size()-1;
	}else
		if(indexWorld2==0){
			indexWorld1--;
			indexWorld3--;
			indexWorld2=listOfFiles.size()-1;
		}else
			if(indexWorld3==0){
				indexWorld2--;
				indexWorld1--;
				indexWorld3=listOfFiles.size()-1;
			}else
			{
				indexWorld2--;
				indexWorld1--;
				indexWorld3--;
			}}
	private void downListElement()
	{
		if(indexWorld1==listOfFiles.size()-1){
			indexWorld2++;
			indexWorld3++;
			indexWorld1=0;
		}else
			if(indexWorld2==listOfFiles.size()-1){
				indexWorld1++;
				indexWorld3++;
				indexWorld2=0;
			}else
				if(indexWorld3==listOfFiles.size()-1){
					indexWorld2++;
					indexWorld1++;
					indexWorld3=0;
				}else
				{
					indexWorld2++;
					indexWorld1++;
					indexWorld3++;
				}}

	private boolean isOnWorldSelected(MouseEvent me)
	{
		if(me.getX()>=(this.getWidth()/2)- (this.getWidth()/4) && me.getX()<=this.getWidth()- (this.getWidth()/4))
		{
			if(me.getY()>=this.getHeight()/3 && me.getY()<=2*(this.getHeight()/3)+50)
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode()==39)//right
		{
			new AePlayWave("sound/buttonEnteredMenu.wav").start();
			if(animationWorldPixel==0)
			{animationWorldPixel=50;
			animationDown=true;
			upListElement();
		
			Animation animation=new Animation(this);
			animation.start();}



		}
		if(ke.getKeyCode()==37)//left
		{
			new AePlayWave("sound/buttonEnteredMenu.wav").start();
			if(animationWorldPixel==0)
			{animationWorldPixel=50;
			animationUp=true;
			downListElement();
			
            Animation animation=new Animation( this);
			animation.start();}
		}

		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode()==10)//press Enter
		{
			frame.setWorldGame(listOfFiles.get(indexWorld2).getName());
			frame.switchTo(new StrategySelectionPanel(frame));
		}
	}


	@Override
	public void keyTyped(KeyEvent ke) {



	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent me) {
		if(me.getWheelRotation()<0)
		{
			if(animationWorldPixel==0)
			{animationWorldPixel=50;
			animationUp=true;
			new AePlayWave("sound/buttonEnteredMenu.wav").start();
			downListElement();
			Animation animation=new Animation( this);
			animation.start();}
		}
		if(me.getWheelRotation()>0)
		{

			if(animationWorldPixel==0)
			{animationWorldPixel=50;
			animationDown=true;
			new AePlayWave("sound/buttonEnteredMenu.wav").start();
			upListElement();
			Animation animation=new Animation( this);
			animation.start();}
		}
		this.repaint();
	}


	@Override
	public void mouseClicked(MouseEvent me) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent me) {

		

	}

	@Override
	public void mouseReleased(MouseEvent me) {
		

		if(me.getX()>= this.getWidth()-(ButtonWidth+20) && me.getX()<=this.getWidth()-20)
		{
			if(me.getY()>=this.getHeight()-(ButtonWidth+20)&&me.getY()<=this.getHeight()-20)
			{
				new AePlayWave("sound/magicClick.wav").start();
				frame.switchTo(new MenuPanel(frame));
			}
		}
		if(isOnWorldSelected(me))
		{
			new AePlayWave("sound/magicClick.wav").start();
			frame.setWorldGame(listOfFiles.get(indexWorld2).getName());
			frame.switchTo(new StrategySelectionPanel(frame));
		}
	}

	public void animationUp(Graphics g)
	{
		Graphics2D g2d=(Graphics2D)g;
		Composite originalComposite = g2d.getComposite();
		g2d.setComposite(makeComposite((float) 0.7));
		g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld1)+".png"), (this.getWidth()/8)-50-animationWorldPixel, this.getHeight()/3-35,this.getWidth()/3,this.getHeight()/3-20, null);
		g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld3)+".png"), (this.getWidth()/2)+100-animationWorldPixel,this.getHeight()/3-35,this.getWidth()/3,this.getHeight()/3-20, null);
		g2d.setComposite(originalComposite);
		g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld2)+".png"), (this.getWidth()/2)- (this.getWidth()/4)+animationWorldPixel, this.getHeight()/3,this.getWidth()/2,this.getHeight()/3+50, null);
		
		
		animationWorldPixel-=2;
		if(animationWorldPixel==0)
		{
			animationUp=false;
		}
	}
	public void animationDown(Graphics g)
	{
		Graphics2D g2d=(Graphics2D)g;
		Composite originalComposite = g2d.getComposite();
		g2d.setComposite(makeComposite((float) 0.7));
		g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld1)+".png"), (this.getWidth()/8)-50+animationWorldPixel, this.getHeight()/3-35,this.getWidth()/3,this.getHeight()/3-20, null);
		g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld3)+".png"), (this.getWidth()/2)+100+animationWorldPixel,this.getHeight()/3-35,this.getWidth()/3,this.getHeight()/3-20, null);
		g2d.setComposite(originalComposite);
		g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld2)+".png"), (this.getWidth()/2)- (this.getWidth()/4)-animationWorldPixel, this.getHeight()/3,this.getWidth()/2,this.getHeight()/3+50, null);

		
		animationWorldPixel-=2;
		if(animationWorldPixel==0)
		{
			animationDown=false;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage(frame.getImageProvider().getImage("strategySelectionPanel.png"), 0, 0, getWidth(), getHeight(), null);
		g.drawImage(frame.getImageProvider().getImage("selectionWorldTitle.png"), (int)getWidth()/14,15,(int) getWidth() -200,200, null);

		if(!animationUp && !animationDown){
			Graphics2D g2d=(Graphics2D)g;
			Composite originalComposite = g2d.getComposite();
			g2d.setComposite(makeComposite((float) 0.7));
			g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld1)+".png"), (this.getWidth()/8)-50, this.getHeight()/3-35,this.getWidth()/3,this.getHeight()/3-20, null);
			g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld3)+".png"), (this.getWidth()/2)+100,this.getHeight()/3-35,this.getWidth()/3,this.getHeight()/3-20, null);
			g2d.setComposite(originalComposite);
			g.drawImage(frame.getImageProvider().getImage(getFileName(indexWorld2)+".png"), (this.getWidth()/2)- (this.getWidth()/4), this.getHeight()/3,this.getWidth()/2,this.getHeight()/3+50, null);
		}

		
		else
		{
			if(animationUp)animationUp(g);
			if(animationDown) animationDown(g);
		}

		g.drawImage(frame.getImageProvider().getImage("buttonBack.png"), this.getWidth()-(ButtonWidth+20), this.getHeight()-(ButtonHeight+20), ButtonWidth,ButtonHeight, null);
	}

	
	

}

class Animation extends Thread
{

	private WorldSelectionPanel panel;
	private int frame;
	public Animation(WorldSelectionPanel panel) {
		this.panel=panel;
		
	}
	public void run() {

		while(panel.animationUp || panel.animationDown )
		{
			panel.repaint();
			
		}
	};
}
