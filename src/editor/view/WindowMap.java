package editor.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import editor.control.MapElementButtonListener;
import editor.model.MapElement;

public class WindowMap extends JPanel {

	Window w;
	MapElement[] map;
	int mapWidth;
	int mapHeight;
	Boolean dirty = false;

	public WindowMap(Window w) {
		this.w = w;
	}

	public void loadMap(char[] mElements, int width, int height) {
		System.out.println("called");
		mapWidth = width;
		mapHeight = height;
		this.setLayout(new GridLayout(height, width));

		map = new MapElement[mElements.length];
		
		MapElementButtonListener mebl = new MapElementButtonListener(w, this);

		for (int i = 0; i < mElements.length; i++) {
			MapElement me = new MapElement(mElements[i]);
			switch (me.getTypeOfBlock()) {
			case 'f':
				me.setBackground(Color.GREEN);
				break;
			case 'w':
				me.setBackground(Color.LIGHT_GRAY);
				break;
			case 'd':
				me.setBackground(Color.ORANGE);
				break;
			case 's':
				me.setBackground(Color.RED);
				break;
			}
			me.setIndex(i);
			me.addActionListener(mebl);
			map[i] = me;
			this.add(map[i]);
		}
	}
	
	public void reloadMap() {
		this.removeAll();
		this.revalidate();
		for(int i = 0; i < map.length; i++) {
			this.add(map[i]);
		}
	}

	public void updateElement(int index, char newType) {
		map[index].setTypeOfBlock(newType);
		switch (newType) {
		case 'f':
			map[index].setBackground(Color.GREEN);
			System.out.println("COLOR");
			break;
		case 'w':
			map[index].setBackground(Color.LIGHT_GRAY);
			System.out.println("COLOR");
			break;
		case 'd':
			map[index].setBackground(Color.ORANGE);
			System.out.println("COLOR");
			break;
		case 's':
			map[index].setBackground(Color.RED);
			System.out.println("COLOR");
			break;
		}
		dirty = true;
		w.setToDirty();
		reloadMap();
	}

	public MapElement[] getMap() {
		return map;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

}
