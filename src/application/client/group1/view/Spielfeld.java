package application.client.gruppe1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Spielfeld extends JPanel{

	private static final long serialVersionUID = 387591187317705952L;
	
	private JButton button = new JButton();
	
	public Spielfeld() {
		
		setLayout(new BorderLayout());

		button.setBackground(new Color(150, 150, 150));
		add(button, BorderLayout.CENTER);
	}
}
