package editor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editor.model.InfoBoxButton;
import editor.view.WindowSideInfoBox;

public class InfoBoxButtonListener implements ActionListener {

	WindowSideInfoBox wsib;
	
	public InfoBoxButtonListener(WindowSideInfoBox wsib) {
		this.wsib = wsib;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof InfoBoxButton) {
			InfoBoxButton ibb = (InfoBoxButton) e.getSource();
			wsib.changeSelectedBlockType(ibb.getTypeOfButton());
		}
	}

}
