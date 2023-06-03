package view.gui;

import controller.DungeonAdventure;
import view.DungeonGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIDisplay implements DungeonGUI {

    private final int myWidth = 14;
    private final int myHeight = 8;

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
                System.out.println("Show the choose character screen.");
                chooseCharacter(frame);
                //displayMainScene(frame, chosen, userName);
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
        Map grid = new Map(myWidth, myHeight, myMain.getDungeon(), frame);

        panel.add(BorderLayout.CENTER, grid);

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

    private void chooseCharacter(JFrame theFrame) {
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new GridLayout(2, 3));


        JLabel warriorImg = new JLabel("Warrior");
        JLabel priestessImg = new JLabel("Priestess");
        JLabel thiefImg = new JLabel("Thief");

        JButton warriorChoice = new JButton("Warrior");
        JButton priestessChoice = new JButton("Priestess");
        JButton thiefChoice = new JButton("Thief");

        characterPanel.add(warriorImg); characterPanel.add(priestessImg); characterPanel.add(thiefImg);
        characterPanel.add(warriorChoice); characterPanel.add(priestessChoice); characterPanel.add(thiefChoice);

        theFrame.add(characterPanel);

        theFrame.setSize(500, 500);
        theFrame.setLocationRelativeTo(null);

        theFrame.revalidate();

        warriorChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theFrame.remove(characterPanel);
                System.out.println("Chosen warrior.");
                displayMainScene(theFrame, 'W', "Warrior");
            }
        });
        priestessChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theFrame.remove(characterPanel);
                System.out.println("Chosen Priestess.");
                displayMainScene(theFrame, 'P', "Priestess");
            }
        });
        thiefChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theFrame.remove(characterPanel);
                System.out.println("Chosen thief.");
                displayMainScene(theFrame, 'T', "Thief");
            }
        });

        return;
    }
}
