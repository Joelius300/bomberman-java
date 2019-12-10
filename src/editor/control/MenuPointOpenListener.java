package editor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import editor.model.Loader;
import editor.view.Window;
import editor.view.WindowMap;

public class MenuPointOpenListener implements ActionListener {

	private Window w;
	private WindowMap wm;

	public MenuPointOpenListener(Window w, WindowMap wm) {
		this.w = w;
		this.wm = wm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Loader l = new Loader();
		try {
			l.load();
			char[] array = l.getFields();
			if(l.getFields()!= null) {
				System.out.println(array);
				System.out.println(l.getWidth());
				System.out.println(l.getHeight());
				wm.loadMap(array, l.getWidth(), l.getHeight());
				wm.reloadMap();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
