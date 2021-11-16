package Online;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Grafica.ImageProvider;
import Grafica.MainFrame;

public class WaitingPanel extends JPanel{
	private ImageIcon wallpaperImage;
	private JLabel loadingLabel;
	private JButton backButton;
	ImageProvider imageProvider=ImageProvider.getInstance(MainFrame.getIstanceMainframe());
	
	public WaitingPanel()
	{
		this.setLayout(new BorderLayout());
		wallpaperImage = new ImageIcon("Img/loadingS.gif");
		this.loadingLabel = new JLabel(wallpaperImage);
		this.setOpaque(true);
		this.setVisible(true);
		this.add(BorderLayout.CENTER,loadingLabel);
		backButton=new JButton((new ImageIcon(imageProvider.getImage("backButton.png").getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH))));
		backButton.setBorder(null);
		JPanel p = new JPanel();
		JPanel tmp=new JPanel();
		tmp.setBackground(Color.black);
		tmp.setPreferredSize(new Dimension(270,245));
		this.add(BorderLayout.NORTH,tmp);
		p.setPreferredSize(new Dimension(270,245));
		p.setBackground(Color.black);
		p.setLayout(null);
		p.add(backButton);
		backButton.setBounds(100, 65, 120, 100);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		p.setVisible(true);
		this.add(BorderLayout.SOUTH,p);
		this.setBackground(Color.black);
		setOpaque(true);
		this.setVisible(true);		
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getIstanceMainframe().switchTo(new PrincipalOnLinePanel(MainFrame.getIstanceMainframe()));
			}
		});
	}
}
