package application.client.group1.observer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginPressedListener implements MouseListener {

    public JTextField playerNameTextField;
    public JButton loginButton;

    public LoginPressedListener(JTextField playerNameTextField, JButton loginButton) {
        this.playerNameTextField = playerNameTextField;
        this.loginButton = loginButton;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
