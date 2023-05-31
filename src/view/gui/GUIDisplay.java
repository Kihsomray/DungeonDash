package view.gui;

import controller.DungeonAdventure;
import view.DungeonGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GUIDisplay implements DungeonGUI {

    private int myWidth = 14;
    private int myHeight = 8;

    private final DungeonAdventure myMain;

    public GUIDisplay(final DungeonAdventure theMain) {
        myMain = theMain;
        //TODO NEED SOME WAY OF PASSING WIDTH AND HEIGHT HERE OF DUNGEON
    }

    public void display() {
        String nameOfDisplay = "Dungeon Adventure";
        JFrame frame = new JFrame(nameOfDisplay);


        // Choose a character and then initialize the dungeon then go to main game loop

        char chosen = 'W'; // TODO implement choosing an actual character

        String userName = "L Bozo"; // TODO implement choosing a name


        myMain.initializeDungeon(chosen, userName);


        // Panel for sorting the elements in the display
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Grid for creating the map
        Map grid = new Map(myWidth, myHeight, myMain.getDungeon().getMaze());



        // Create a label and a button
        JLabel label = new JLabel("JFrame By Example");
        JButton button = new JButton();
        button.setText("Button");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Testing the button.");
            }
        });

        panel.add(BorderLayout.CENTER, grid);

        // Add it to the panel and later to the frame
        //panel.add(BorderLayout.PAGE_END, label);
        //panel.add(button);
        frame.add(panel);

        // Set some defaults for the frame
        frame.setSize(myWidth*64, myHeight*64);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.pack();
        // Turn it on
        frame.setVisible(true);




        // MAIN GAME LOOP, Keep this running till game ends
        while (true) {
            // Display the current state of the game

            // Get players input, whether they want to move north south east or west. Or if they want to use pot

            break;
        }
        System.out.println("Done!");
    }

    private void chooseCharacter() {

    }
}
