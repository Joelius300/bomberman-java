package application.client.group1.observer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPressedListener implements KeyListener {

    public JTextField playerNameTextField;

    public KeyPressedListener(JTextField playerNameTextField) {
        this.playerNameTextField = playerNameTextField;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
