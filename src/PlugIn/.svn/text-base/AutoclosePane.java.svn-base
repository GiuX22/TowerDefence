package PlugIn;
import java.awt.Color;
import java.awt.Component;  
import java.awt.Container;
import java.awt.HeadlessException;  

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JDialog;  
import javax.swing.JOptionPane;  
import javax.swing.JRootPane;
import javax.swing.UIManager;

public class AutoclosePane  
{  
private final long timeout;  
public AutoclosePane(long timeout)  
{  
   this.timeout = timeout;  
}  
public int confirmOrTimeout(String message, String title) throws HeadlessException  
{  
	UIManager uimanager=new UIManager();
	uimanager.put("OptionPane.background", new Color(153, 102, 153));
	uimanager.put("Panel.background", new Color(153, 102, 153));   
   JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE,   
      JOptionPane.PLAIN_MESSAGE); 

   pane.setForeground(Color.black);


   return showWithTimeout(pane, null, title);  
}  

private int showWithTimeout(JOptionPane pane, Component parent, String title)  
{  
   final JDialog dialog = pane.createDialog(parent, title);  
 
 
   Thread timeoutThread = new Thread()  
   {  
      public void run()  
      {  
         try { sleep(timeout); }  
         catch (InterruptedException ex) { /* Fall through*/ }  // Ensure we close the dialog  
         javax.swing.SwingUtilities.invokeLater(new Runnable() //   from the event dispatch  
            { public void run() { dialog.hide(); } });            //   thread  
      }  
   };  
   timeoutThread.start();  
   dialog.show();  
   Object selection = pane.getValue();                      // We get to this point when  
   int result = JOptionPane.CLOSED_OPTION;                   // (1) The user makes a selection  
   if (selection != null && selection instanceof Integer)        // or (2) the timeout thread closes  
      result = ((Integer)selection).intValue();             // the dialog.  
   return result;  
}  
//----------------------------------------------------------------------------------------------  
}  