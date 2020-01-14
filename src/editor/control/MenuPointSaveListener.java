package editor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import editor.model.MapElement;
import editor.model.Saver;
import editor.view.Window;
import editor.view.WindowMap;
import editor.view.WindowMenu;

public class MenuPointSaveListener  implements ActionListener {

	WindowMap wm;
	Window w;
	WindowMenu wme;
	
	
	public MenuPointSaveListener(WindowMap wm, Window w, WindowMenu wme) {
		this.wm = wm;
		this.w = w;
		this.wme = wme;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton) {
			Saver s = Saver.getInstance();
			s.init(getValuesFromMap(wm.getMap()), wm.getMapWidth(), wm.getMapHeight());
			s.save();
			wme.setToStandard();
		}
	}
	
	private char[] getValuesFromMap(MapElement[] meList) {
		
		char[] charArray = new char[meList.length];
		
		for(int i = 0; i < meList.length; i++) {
			charArray[i] = meList[i].getTypeOfBlock();
		}
		
		return charArray;
	}

}
