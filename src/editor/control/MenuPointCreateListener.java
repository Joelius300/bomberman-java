package editor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import editor.model.InfoBoxButton;
import editor.view.Window;
import editor.view.WindowMap;
import editor.view.WindowMenu;
import editor.view.WindowSideInfoBox;

public class MenuPointCreateListener implements ActionListener {

	Window w;
	WindowMenu wme;
	WindowMap wm;
	
	public MenuPointCreateListener(Window w, WindowMenu wme, WindowMap wm) {
		this.w = w;
		this.wme = wme;
		this.wm = wm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			System.out.println(wme.getXSize());
			System.out.println(wme.getYSize());
			if(wme.getXSize() >= 6 && wme.getYSize() >= 6) {
				wme.freezeButtonsForCreate();
				wm.loadMap(createCharArrayWithFloor(wme.getXSize(), wme.getYSize()), wme.getXSize(), wme.getYSize());
				wm.reloadMap();
				System.out.println("innit");
			}
		}
	}
	
	private char[] createCharArrayWithFloor(int w, int h) {
		int x = w*h;
		char[] emptyField = new char[x];
		
		for(int i = 0; i < emptyField.length; i++) {
			emptyField[i] = 'f';
		}
		
		System.out.println(emptyField);
		
		return emptyField;
	}

}
