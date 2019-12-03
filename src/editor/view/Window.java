package editor.view;

import java.awt.*;

import javax.swing.*;

public class Window {

	JFrame jf;
	JPanel jp;
	JButton jb;
	Boolean dataDirty = false;
	WindowMap wm;
	WindowMenu wme;
	// Wall = w, Destroyable Wall = d, Floor = f, Spawnpoint = s
	char chosenTypeOfBlockToSet;

	public Window() {
		chosenTypeOfBlockToSet = 'f';
		prepareGUI();
	}

	private void prepareGUI() {

		jf = new JFrame("Bomberman Map Editor");
		jf.setSize(1000, 800);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jp = new JPanel();
		jp.setLayout(new BorderLayout());

		// JPanel jp2 = new JPanel();;
		wm = new WindowMap(this);
		wme = new WindowMenu(this, wm);
		// BoxLayout bl = new BoxLayout(jp2, BoxLayout.X_AXIS);

		jp.add(wme, BorderLayout.NORTH);
		jp.add(new WindowSideInfoBox(this), BorderLayout.EAST);

		// wm.loadMap(new char[] { 'f', 'd', 's', 'w', 'd', 'd', 'f', 's', 'f', 'w',
		// 'f', 's', 'f', 'w', 'f', 's' }, 4, 4);

		jp.add(wm, BorderLayout.CENTER);

		jf.add(jp);
		// BorderLayout.NORTH, new BoxLayout(jp, BoxLayout.X_AXIS)

		jf.setVisible(true);
	}

	public void setChosenTypeOfBlockToSet(char chosenTypeOfBlockToSet) {
		this.chosenTypeOfBlockToSet = chosenTypeOfBlockToSet;
	}

	public char getChosenTypeOfBlockToSet() {
		return chosenTypeOfBlockToSet;
	}

	public void setToDirty() {
		dataDirty = true;
		wme.setToDirty();
	}

}
