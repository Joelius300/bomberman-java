package editor.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;

import editor.control.InfoBoxButtonListener;
import editor.model.InfoBoxButton;

public class WindowSideInfoBox extends JPanel {

	Window w;
	InfoBoxButton[] buttons = new InfoBoxButton[] { new InfoBoxButton('f'), new InfoBoxButton('w'),
			new InfoBoxButton('d'), new InfoBoxButton('s') };

	public WindowSideInfoBox(Window w) {
		this.w = w;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("Diese Farben entsprechen der Art der Box"));

		InfoBoxButtonListener ibbl = new InfoBoxButtonListener(this);

		for (int i = 0; i < buttons.length; i++) {
			switch (buttons[i].getTypeOfButton()) {
			case 'f':
				buttons[i].setText("Floor");
				buttons[i].setBackground(Color.GREEN);
				break;
			case 'w':
				buttons[i].setText("Wall");
				buttons[i].setBackground(Color.LIGHT_GRAY);
				break;
			case 'd':
				buttons[i].setText("Destroyable Wall");
				buttons[i].setBackground(Color.ORANGE);
				break;
			case 's':
				buttons[i].setText("Spawnpoint");
				buttons[i].setBackground(Color.RED);
				break;
			}
			System.out.println(buttons[i].getMaximumSize());
			buttons[i].addActionListener(ibbl);
			this.add(buttons[i]);
		}
	}

	public void changeSelectedBlockType(char newBlock) {
		System.out.println(newBlock);
		w.setChosenTypeOfBlockToSet(newBlock);
		for(int i = 0; i < buttons.length; i++) {
			if(buttons[i].getTypeOfButton() == newBlock) {
				buttons[i].setBorder(new LineBorder(Color.BLACK, 5));
			} else {
				buttons[i].setBorder(UIManager.getBorder("Button.border"));
			}
		}

	}

}
