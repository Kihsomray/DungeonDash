package view.gui;

import controller.DungeonAdventure;
import view.DungeonGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIDisplay implements DungeonGUI {

    private int myWidth = 14;
    private int myHeight = 8;

    private final DungeonAdventure myMain;

    public GUIDisplay(final DungeonAdventure theMain) {
        myMain = theMain;
        //TODO NEED SOME WAY OF PASSING WIDTH AND HEIGHT HERE OF DUNGEON
    }

    public void display() {
        // This is the first thing in the program

        String nameOfDisplay = "Dungeon Adventure";
        JFrame frame = new JFrame(nameOfDisplay);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel startView = new JPanel(new BorderLayout());
        frame.add(startView);

        JLabel startLabel = new JLabel("\nStart the Dungeon adventure\n");
        JButton startButton = new JButton("Start");



        startView.add(startButton, BorderLayout.CENTER); startView.add(startLabel, BorderLayout.NORTH);

        frame.setVisible(true);
        frame.setSize(200, 400);


        // First the player needs to see the start screen, then the character select screen, and the username select

        char chosen = 'W'; // TODO implement choosing an actual character

        String userName = "L Bozo"; // TODO implement choosing a name


        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(startView);
                System.out.println("Start the Game.");
                displayMainScene(frame, chosen, userName);
            }
        });
        // Choose a character and then initialize the dungeon then go to main game loop

        /*
        // Initialize the dungeon before doing anything else
        myMain.initializeDungeon(chosen, userName);

        // This is for the main game scene

        // Panel for sorting the elements in the display
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Grid for creating the map
        Map grid = new Map(myWidth, myHeight, myMain.getDungeon().getMaze());

        panel.add(BorderLayout.CENTER, grid);





        // Create a label and a button
        JLabel label = new JLabel("JFrame By Example");
        JButton button = new JButton();
        button.setText("Button");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Testing the button.");
            }
        });

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

         */
    }

    private void displayMainScene(JFrame frame, char chosen, String userName) {
        // Initialize the dungeon before doing anything else
        myMain.initializeDungeon(chosen, userName);

        // This is for the main game scene

        // Panel for sorting the elements in the display
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Grid for creating the map
        Map grid = new Map(myWidth, myHeight, myMain.getDungeon().getMaze());

        panel.add(BorderLayout.CENTER, grid);

        // Add it to the panel and later to the frame
        //panel.add(BorderLayout.PAGE_END, label);
        //panel.add(button);
        frame.add(panel);

        // Set some defaults for the frame
        frame.setSize(myWidth*64, myHeight*64);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
