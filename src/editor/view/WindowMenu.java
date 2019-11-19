package editor.view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;

import editor.control.MenuPointCreateListener;
import editor.control.MenuPointExitListener;
import editor.control.MenuPointOpenListener;
import editor.control.MenuPointSaveListener;

public class WindowMenu extends JPanel {

	JButton openMap;
	JButton newMap;
	JButton saveMap;
	JButton exitProgram;
	JPanel sizeHolder;
	JLabel xSizeLabel;
	JLabel ySizeLabel;
	JTextField xSizeNewMap;
	JTextField ySizeNewMap;
	Window w;
	WindowMap wm;

	public WindowMenu(Window w, WindowMap wm) {
		this.w = w;
		this.wm = wm;
		prepareMenu();
	}

	private void prepareMenu() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		openMap = new JButton("Open Map");
		openMap.addActionListener(new MenuPointOpenListener(w,wm));
		newMap = new JButton("Create Map");
		newMap.addActionListener(new MenuPointCreateListener(w, this, wm));
		saveMap = new JButton("Save Map");
		saveMap.addActionListener(new MenuPointSaveListener(wm, w, this));
		saveMap.setEnabled(false);
		exitProgram = new JButton("Exit Program");
		exitProgram.addActionListener(new MenuPointExitListener());

		this.add(openMap);
		this.add(newMap);
		this.add(getSizeDesign());
		this.add(saveMap);
		this.add(exitProgram);
	}

	private JPanel getSizeDesign() {

		sizeHolder = new JPanel();
		sizeHolder.setLayout(new GridLayout(2, 2));

		xSizeLabel = new JLabel("   X Size (at least 6)");
		ySizeLabel = new JLabel("   Y Size (at least 6)");
		xSizeNewMap = new JTextField();
		ySizeNewMap = new JTextField();

		sizeHolder.add(xSizeLabel);
		sizeHolder.add(xSizeNewMap);
		sizeHolder.add(ySizeLabel);
		sizeHolder.add(ySizeNewMap);

		return sizeHolder;
	}
	
	public void freezeButtonsForCreate() {
		openMap.setEnabled(false);;
		newMap.setEnabled(false);
		saveMap.setEnabled(true);
		exitProgram.setEnabled(true);
		xSizeNewMap.setEnabled(false);
		ySizeNewMap.setEnabled(false);
	}
	
	public void setToStandard() {
		openMap.setEnabled(true);;
		newMap.setEnabled(true);
		saveMap.setEnabled(false);
		exitProgram.setEnabled(true);
		xSizeNewMap.setEnabled(true);
		ySizeNewMap.setEnabled(true);
	}
	
	public void freezeButtonsForOpen() {
		openMap.setEnabled(false);;
		newMap.setEnabled(false);
		saveMap.setEnabled(false);
		exitProgram.setEnabled(true);
		xSizeNewMap.setEnabled(false);
		ySizeNewMap.setEnabled(false);
	}
	public void setToDirty() {
		openMap.setEnabled(false);;
		newMap.setEnabled(false);
		saveMap.setEnabled(true);
		exitProgram.setEnabled(true);
		xSizeNewMap.setEnabled(false);
		ySizeNewMap.setEnabled(false);
	}

	public int getXSize() {
		return Integer.parseInt(xSizeNewMap.getText());
	}
	
	public int getYSize() {
		return Integer.parseInt(ySizeNewMap.getText());
	}
}
