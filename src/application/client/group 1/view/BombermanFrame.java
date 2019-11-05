package application.client.gruppe1.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.client.gruppe1.controller.KeyPressedListener;

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
