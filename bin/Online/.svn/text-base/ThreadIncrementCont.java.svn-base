package Online;

public class ThreadIncrementCont extends Thread {
GamePanelOnLine copia;	
private boolean active=true;

public void setActive(boolean b)
{
	active=b;
}
	public ThreadIncrementCont(GamePanelOnLine o)
	{
		copia=o;
	}
	public void run() {
		while(active)
		{
			
			try {
				copia.setContAnimation();
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
