package Grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class MatrixPanel extends JPanel{
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public MatrixPanel(String file) {
		Toolkit toolkit = getToolkit();
		URL url = getClass().getResource(file);
		if (url != null)
			img = toolkit.createImage(url);
		setLayout(null);
		init();
	}
	
	
	
	public MatrixPanel(MainFrame mainframe,String file) { // Invoca il costruttore della super classe
		
//		Toolkit toolkit = getToolkit();
//		
//		URL url = getClass().getResource(file);
//		
//		if (url != null)
//			img = toolkit.createImage(url);
		
		
		this.setLayout(new GridBagLayout());
	    GridBagConstraints lim = new GridBagConstraints();
		Component c1 = new JButton("C0");
		
		lim.gridx = 1;
		lim.gridy = 0;
//		layout.setConstraints(c1, lim);
		add(c1);
		
		
		init();
	}

	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Insets i = getInsets();
		g.drawImage(img, i.left, i.top, getWidth(), getHeight(), this);
	}

	private void init() {
		//setSize(40, 62);
		setLayout(new BorderLayout());
		setFont(new Font("SansSerif", 1, 14));
	}

	private Image img = null;
	public int x ;
	public int y ;

}
