package view.gui;

import controller.DungeonAdventure;
import view.DungeonGUI;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

        startView.setBackground(new Color(0, 0, 0));

        String titlePath = "res" + File.separator + "DungeonAdventureTitle.png";
        String beginPath = "res" + File.separator + "BeginAdventure.png";
        String loadPath = "res" + File.separator + "LoadAdventure.png";

        JLabel startLabel;
        JButton startButton;
        JButton loadButton;
        try {
            startLabel = new JLabel(new ImageIcon(ImageIO.read(new File(titlePath))));
            startButton = new JButton(new ImageIcon(ImageIO.read(new File(beginPath))));
            loadButton = new JButton(new ImageIcon(ImageIO.read(new File(loadPath))));
        } catch (Exception e) {System.out.println("Issue with creating the title screen image"); return;}


        startView.add(startButton, BorderLayout.CENTER); startView.add(startLabel, BorderLayout.NORTH);
        startView.add(loadButton, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setSize(1000, 400);
        frame.pack();

        frame.setLocationRelativeTo(null);

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

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout());

        InventoryGUI invGUI = new InventoryGUI(myMain.getDungeon());
        grid.setInvGUI(invGUI);

        eastPanel.add(BorderLayout.NORTH, invGUI);

        JPanel hpSp = new JPanel();
        hpSp.setLayout(new BorderLayout());

        try {
            hpSp.add(BorderLayout.EAST, new JLabel(new ImageIcon(ImageIO.read(new File("res" + File.separator + "PlayerRes" + File.separator + "BasicHealth.png")))));
        } catch (Exception e) {System.out.println("Issue creating the basic health bar" + e); return;}

        try {
            hpSp.add(BorderLayout.WEST, new JLabel(new ImageIcon(ImageIO.read(new File("res" + File.separator + "PlayerRes" + File.separator + "BasicSP.png")))));
        } catch (Exception e) {System.out.println("Issue creating the basic SP bar" + e); return;}

        eastPanel.add(BorderLayout.SOUTH, hpSp);
        panel.add(BorderLayout.EAST, eastPanel);

        // Set some defaults for the frame
        frame.setSize(myWidth*64 + 32*4, myHeight*64);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Turn it on
        frame.setVisible(true);
        System.out.println("Done!");
    }

    private void chooseCharacter(JFrame theFrame) {
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new GridLayout(2, 3));

        String warriorPath = "res" + File.separator + "Warrior.png";
        String priestessPath = "res" + File.separator + "Priestess.png";
        String thiefPath = "res" + File.separator + "Thief.png";

        JLabel warriorImg;
        JLabel priestessImg;
        JLabel thiefImg;

        try {
            warriorImg = new JLabel(new ImageIcon(ImageIO.read(new File(warriorPath))));
            priestessImg = new JLabel(new ImageIcon(ImageIO.read(new File(priestessPath))));
            thiefImg = new JLabel(new ImageIcon(ImageIO.read(new File(thiefPath))));
        } catch (Exception e) {
            System.out.println("There was an issue loading the images in the choose character screen"); return;
        }


        JButton warriorChoice = new JButton("Warrior");
        JButton priestessChoice = new JButton("Priestess");
        JButton thiefChoice = new JButton("Thief");

        characterPanel.add(warriorImg); characterPanel.add(priestessImg); characterPanel.add(thiefImg);
        characterPanel.add(warriorChoice); characterPanel.add(priestessChoice); characterPanel.add(thiefChoice);

        theFrame.add(characterPanel);

        theFrame.setSize(500, 300);
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
