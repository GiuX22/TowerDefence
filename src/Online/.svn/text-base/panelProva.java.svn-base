package Online;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class panelProva extends JPanel{
	
	private DefaultListModel<String> defaultlistMessage;
	private JList<String> listMessage;
	private JScrollPane scrollPaneMessage;

	public panelProva() {
		this.setLayout(null);
		defaultlistMessage=new DefaultListModel<String>();
		listMessage=new JList<String>(defaultlistMessage);
		listMessage.setFont(new Font(getFont().getName(),getFont().getStyle(),15));
		listMessage.setForeground(Color.blue);
		listMessage.setOpaque(false);
		listMessage.setBackground(new Color(171, 205, 239));
		scrollPaneMessage = new JScrollPane();
		scrollPaneMessage.setBorder(null);
		scrollPaneMessage.setBounds(200, 300, 200, 50);
		scrollPaneMessage.add(listMessage);
		scrollPaneMessage.getViewport().setOpaque(false);
		scrollPaneMessage.setOpaque(true);
		scrollPaneMessage.setViewportView(listMessage);
		scrollPaneMessage.setBackground(new Color(171, 205, 239));
		add(scrollPaneMessage);

		final JFormattedTextField formattedMessage = new JFormattedTextField();
		formattedMessage.setBounds(200, 200, 200, 20);
		formattedMessage.setBackground(new Color(171, 205, 239));
		formattedMessage.setEnabled(true);
		formattedMessage.setText("inserisci un messaggio...");
		add(formattedMessage);
		formattedMessage.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				formattedMessage.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		formattedMessage.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10)
				{
					String message=formattedMessage.getText();

					try {

						PlayerOnline.getIstance().sendPrivateMessage(message);
					} catch ( NullPointerException e1) {
						JOptionPane.showMessageDialog(null, "Server not reachable ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);

					}


					//calculatePrivateMessage();
					formattedMessage.setText("");
				}
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		panelProva panel=new panelProva();
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(600, 600);
	}
}
