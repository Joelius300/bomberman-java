package editor.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Saver {
	char[] fields;
	int width, height;
	File path;

	private static Saver instance;
	
	public static Saver getInstance () {
	    if (Saver.instance == null) {
	    	Saver.instance = new Saver();
	    }
	    return Saver.instance;
	  }
	
	public void init(char[] array, int width, int height) {
		this.fields = array;
		this.width = width;
		this.height = height;
	}
	
	public Saver() {
			
	}

	public void save() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.path = chooser.getSelectedFile();
		}
		try{
	    	BufferedWriter fStream = new BufferedWriter(new FileWriter(this.path));
	    	Writer writer = new OutputStreamWriter(new FileOutputStream(this.path), StandardCharsets.UTF_8);
	    	writer.write(width + ";");
	    	writer.write(height + ":");
	    	for (int i = 0; i < this.fields.length; i++) {
		    	writer.write(fields[i] + ";");
	    	}
	    	writer.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
