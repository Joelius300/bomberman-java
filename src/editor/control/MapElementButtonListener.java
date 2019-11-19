package editor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editor.model.InfoBoxButton;
import editor.model.MapElement;
import editor.view.Window;
import editor.view.WindowMap;
import editor.view.WindowSideInfoBox;

public class MapElementButtonListener implements ActionListener {

	WindowMap wm;
	Window w;
	
	public MapElementButtonListener(Window w, WindowMap wm) {
		this.w = w;
		this.wm = wm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof MapElement) {
			MapElement me = (MapElement) e.getSource();
			System.out.println("Updated Block with Index: " + me.getIndex() + " from " + me.getTypeOfBlock() + " to " + w.getChosenTypeOfBlockToSet());
			wm.updateElement(me.getIndex(), w.getChosenTypeOfBlockToSet());
		}
	}

}
