package editor.model;

import javax.swing.JButton;

public class InfoBoxButton extends JButton {
	
	private char typeOfButton;

	public InfoBoxButton(char typeOfButton) {
		this.typeOfButton = typeOfButton;
	}

	public char getTypeOfButton() {
		return typeOfButton;
	}

	public void setTypeOfButton(char typeOfButton) {
		this.typeOfButton = typeOfButton;
	}

}
