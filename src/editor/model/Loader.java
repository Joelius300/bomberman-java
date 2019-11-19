package editor.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Loader {
	char[] fields;
	int width, height;
	File path;

	public Loader() {
	}

	public void load() throws IOException {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.path = chooser.getSelectedFile();
		}
		String a;
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			a = br.readLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			a = "a";
		}
		String[] b = a.split(":");
		String[] c = b[0].split(";");
		width = Integer.parseInt(c[0]);
		height = Integer.parseInt(c[1]);
		String[] d = b[1].split(";"); 
		String e = "";
		for (int i = 0; i < d.length; i++) {
			e += d[i];
		}
		fields = e.toCharArray();
		
	}
	public char[] getFields() {
		return fields;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
