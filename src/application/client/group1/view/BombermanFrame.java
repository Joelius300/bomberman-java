package application.client.group1.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BombermanFrame extends JFrame{

	private static final long serialVersionUID = 2475391450401588528L;
	
	public BombermanFrame(JPanel panel) {
		setTitle("Bomberman");
		setSize(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(panel);
		setVisible(true);
	}
}
