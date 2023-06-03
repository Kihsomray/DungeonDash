package view.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Some key was typed");
        // DONT WORRY ABOUT THIS METHOD
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Some key was pressed");
        char charPressed = e.getKeyChar();

        if (charPressed == 'w') {
            System.out.println("Test key pressed " + charPressed);
        } else if (charPressed == 'a') {
            System.out.println("Test key pressed " + charPressed);
        } else if (charPressed == 's') {
            System.out.println("Test key pressed " + charPressed);
        } else if (charPressed == 'd') {
            System.out.println("Test key pressed " + charPressed);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Some key was released");
        // DONT WORRY ABOUT THIS METHOD
        return;
    }
}
