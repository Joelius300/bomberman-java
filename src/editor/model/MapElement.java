package editor.model;

import javax.swing.JButton;

public class MapElement extends JButton{

	private int index;
	private char typeOfBlock;
	
	public MapElement(char typeOfBlock) {
		this.typeOfBlock = typeOfBlock;
	}

	public char getTypeOfBlock() {
		return typeOfBlock;
	}

	public void setTypeOfBlock(char typeOfBlock) {
		this.typeOfBlock = typeOfBlock;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
