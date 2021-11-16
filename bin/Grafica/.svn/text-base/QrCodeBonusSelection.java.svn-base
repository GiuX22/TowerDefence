package Grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logicaOffline.Manager.GameManager;
import Online.PlayerOnline;
import PlugIn.UtilityQRCode;
import PlugIn.StringEncrypter.EncryptionException;

public class QrCodeBonusSelection  extends JPanel{
    private ImageIcon wallpaperImage;
    private JLabel loadingLabel;
    private int width;
    private int height;
    @SuppressWarnings("rawtypes")
    private final DefaultListModel model = new DefaultListModel();
    private JPanel panel;
    private JFrame frame;
    private boolean loaded;
    public JScrollPane scroll;
    GameManager manager;




    public QrCodeBonusSelection(GameManager m) {
        loaded=false;
        manager=m;

        width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        setOpaque(false);
        this.setLayout(null);
        wallpaperImage = new ImageIcon("Img/loading.gif");
        this.loadingLabel = new JLabel(wallpaperImage);
        loadingLabel.setBounds(((int)width/2)-100, ((int)height/2)-100,150, 150);
        try {
            drawPanel();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.add(loadingLabel);
        panel.setVisible(false);
        loadingLabel.setVisible(true);
        initFrame();
        downloadRanking();




panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            if(loaded)
            {
                MainFrame.getIstanceMainframe().setEnabled(true);
                frame.dispose();
            }

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
                // TODO Auto-generated method stub

            }
        });

}


    public void drawPanel() throws IOException
    {
        final Image showcaseWall = ImageIO.read(new File("Img/QRBASE.png"));
        panel=new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(showcaseWall.getScaledInstance((int)(width/3), height-300,java.awt.Image.SCALE_SMOOTH ), 0, 0, (int)(width/3), height-500,this);
            }
        };

        JList list=new JList<String>(model);
        scroll=new JScrollPane(list);
        list.setOpaque(false);
        list.setBackground(new Color(0,0,0,0));
        list.setFont(new Font("serif", Font.PLAIN, 24));
        list.setForeground(Color.white);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
        panel.add(scroll);
        panel.setLayout(null);
        this.add(panel);
        panel.setBounds(450,200,500,500);
        scroll.setBounds(150,30,220,200);

        list.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JList list=(JList)e.getSource();
                int index=list.locationToIndex(e.getPoint());
                String nameRoom =(String) list.getSelectedValue();
                nameRoom+=".png";

                if(nameRoom!=null)
                {
                    File file = new File("Bonus/"+nameRoom);
                    try {
                        UtilityQRCode utility=UtilityQRCode.getIstance(manager);
                        utility.readfromImage(ImageIO.read(new File(file.getAbsolutePath())));
                        MainFrame.getIstanceMainframe().setEnabled(true);
                        frame.dispose();

                    } catch (IOException | EncryptionException e1) {
                    }
                    file.delete();
                }



            }
        });
    }


public void load()
{
    File file=new File("./Bonus");
    File[] list=file.listFiles();
    for (File record : list) {
    	if(!record.getName().equals(".svn"))
    	{
    		String[] t=record.getName().split(".png");
        model.addElement(t[0]);
    	}
    }
    loaded=true;
}

private void downloadRanking()
{
    Thread t=new Thread(new Runnable() {

        @Override
        public void run() {
            try{
                Thread.sleep(1000);
                while(loaded==false){
                    Thread.sleep(400);
                    load();

                }
                loadingLabel.setVisible(false);
                panel.setVisible(true);
                repaint();
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    });
    t.start();
}



public void initFrame()
{
    frame=new JFrame();
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setSize(width, height); //-40 per non scendere sotto la barra di windows
    frame.setUndecorated(true);
    // Apply a transparent color to the background
    // This is ALL important, without this, it won't work!
    frame.setBackground(new Color(0, 255, 0, 0));
    frame.add(this);
    frame.setVisible(true);
    setLayout(null);
}

}