package application.client.gruppe1.view;
import java.awt.BorderLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import application.client.gruppe1.controller.KeyPressedListener;
import application.client.gruppe1.controller.LoginPressedListener;
public class BombermanPanel extends JPanel {

	private static final long serialVersionUID = 123701334789462850L;
	
	private JPanel headerPanel = new JPanel();
	private JTextField playerNameTextField = new JTextField();
	private JButton loginButton = new JButton("Anmelden");
	
	private Spielfeld centerPanel = new Spielfeld();
	
	private JTextArea messageTextArea = new JTextArea();
	
	public BombermanPanel() {
	    setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
	    addKeyListener(new KeyPressedListener(playerNameTextField));		
		setLayout(new BorderLayout());
		headerPanel.setLayout(new BorderLayout());
		headerPanel.add(playerNameTextField, BorderLayout.CENTER);
		headerPanel.add(loginButton, BorderLayout.EAST);
		headerPanel.setBorder(new EmptyBorder(new Insets(25, 20, 25, 20)));
		add(headerPanel, BorderLayout.NORTH);
		
		centerPanel.setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
		add(centerPanel, BorderLayout.CENTER);
		
		messageTextArea.setRows(15);
		messageTextArea.setEditable(false);
		messageTextArea.setBorder(new EmptyBorder(new Insets(25, 20, 25, 20)));
		messageTextArea.setText("someone joined");
		add(messageTextArea, BorderLayout.SOUTH);
		
		loginButton.addMouseListener(new LoginPressedListener(playerNameTextField, loginButton));
	}

	public void displayMessage(String message) {
		messageTextArea.append(message);
	}

}
