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

    private int widthOfMap;
    private int heightOfMap;

    private final DungeonAdventure myMain;

    public GUIDisplay(final DungeonAdventure theMain) {
        myMain = theMain;
        //TODO NEED SOME WAY OF PASSING WIDTH AND HEIGHT HERE OF DUNGEON
        widthOfMap = 14;
        heightOfMap = 8;
    }

    public void display() {


        String greyTilePath = "GreyTile.jpg";
        //String realPath = getClass().getResource("/res/GreyTile.jpg").getPath();
        String realPath = "res" + File.separator + "GreyTile.jpg";
        //System.out.println(getClass().getResource("/res/GreyTile.jpg").getPath());
        //System.out.println("/res/GreyTile.jpg");
        File greyTileFile = new File(realPath);
        System.out.println(greyTileFile);
        BufferedImage greyTileImage;
        JLabel GreyTileLabel;
        try {
            greyTileImage = ImageIO.read(greyTileFile);

        } catch (Exception e) {
            System.out.println("something bad just happened " + e);
            return;
        }
        GreyTileLabel = new JLabel(new ImageIcon(greyTileImage));

        String nameOfDisplay = "Dungeon Adventure";
        JFrame frame = new JFrame(nameOfDisplay);

        // Panel for sorting the elements in the display
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Grid for creating the map
        JPanel grid = new JPanel();
        GridLayout gridLayout = new GridLayout(heightOfMap, widthOfMap);
        grid.setLayout(gridLayout);

        for (int i = 0; i < widthOfMap; i++) {
            for (int j = 0; j < heightOfMap; j++) {
                //grid.add(new JLabel("box " + i + ", " + j));
                //grid.add(GreyTileLabel);
                grid.add(new JLabel(new ImageIcon(greyTileImage)));

            }
        }

        //grid.add(new JLabel("Player"), 1, 1);

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
        frame.setSize(14*64, 8*64);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Turn it on
        frame.setVisible(true);


        // Choose a character and then initialize the dungeon then go to main game loop

        char chosen = 'W'; // TODO implement choosing an actual charater

        String userName = "L Bozo"; // TODO implement choosing a name

        myMain.initializeDungeon(chosen, userName);


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
