package view.gui;

import model.dungeon.tile.Cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map extends JPanel {

    private int myWidth;
    private int myHeight;

    /** This is where the map is stored. In this 2d array, there are JPanels which contain the art for each tile. */
    private JComponent[][] myPanels;

    private final static int PIXELS_IN_SPRITE = 64; // Number of pixels each art asset is

    public Map(int theWidth, int theHeight, Cell[][] theMaze) {
        super();

        myWidth = theWidth;
        myHeight = theHeight;

        this.setLayout(new GridLayout(myHeight, myWidth));


        // This the tileMap that will have every sprite
        myPanels = new JComponent[myHeight][myWidth];

        createTheTileMap(theMaze);

        AddCharacter(0, 0);

//        String sampleMonsterPath = "res" + File.separator + "SampleMonster.png";
//
//        for (int i = 1; i < 10; i++) {
//            addEntity(i, 5, sampleMonsterPath);
//        }
    }

    private void createTheTileMap(Cell[][] theMaze) {




        String tilePath = "res" + File.separator + "DarkTile.png";
        File tileFile = new File(tilePath);
        String leftWallPath = "res" + File.separator + "DarkTileLeftWall.png";
        File leftWallFile = new File(leftWallPath);

        BufferedImage greyTileImage; BufferedImage leftWallImage;
        try {
            greyTileImage = ImageIO.read(tileFile);
            leftWallImage =  ImageIO.read(leftWallFile);
        } catch (Exception e) {
            System.out.println("Had an issue loading the tile " + e);
            return;
        }


        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                myPanels[i][j] = new JPanel();
                myPanels[i][j].setLayout(new OverlayLayout(myPanels[i][j]));



//                if (j == 0) {
//                    myPanels[i][j].add(BorderLayout.CENTER,new JLabel(new ImageIcon(leftWallImage)));
//                } else {
//                    myPanels[i][j].add(BorderLayout.CENTER,new JLabel(new ImageIcon(greyTileImage)));
//                }


                myPanels[i][j].setPreferredSize(new Dimension(64,64));

                this.add(myPanels[i][j]);


            }
        }

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                addTile(i, j, theMaze[i][j].getArtPath());
            }
        }
    }

    private void AddCharacter(int theX, int theY) {
        String playerPath = "res" + File.separator + "Warrior.png";
        try {
            JLabel player = new JLabel(new ImageIcon(ImageIO.read(new File(playerPath))));
            myPanels[theY][theX].add(player,0);
        } catch (Exception e) {
            System.out.println("Something Bad just happened" + e);
        }
    }

    private void addDoor(int theX, int theY) {
        String doorPath = "res" + File.separator + "Door.png";
        JLabel door = null;
        try {
            door = new JLabel(new ImageIcon(ImageIO.read(new File(doorPath))));
        } catch (IOException e) {
            System.out.println("There was an issue loading the door " + e);
            return;
        }
        myPanels[theY][theX].removeAll();
        myPanels[theY][theX].add(door,0);
    }

    private void addMonster(int theX, int theY) {
        String sampleMonsterPath = "res" + File.separator + "SampleMonster.png";
        JLabel monster = null;
        try {
            monster = new JLabel(new ImageIcon(ImageIO.read(new File(sampleMonsterPath))));
        } catch (IOException e) {
            System.out.println("There was an issue loading the Monster " + e);
            return;
        }
        myPanels[theY][theX].add(monster,0);
    }

    private void addEntity(int theX, int theY, String thePath) {
        JLabel entityLabel = null;
        try {
            entityLabel = new JLabel(new ImageIcon(ImageIO.read(new File(thePath))));
        } catch (IOException e) {
            System.out.println("There was an issue loading the Entity with path " + thePath + "\n with error "  + e);
            return;
        }
        myPanels[theY][theX].add(entityLabel,0);
    }

    private void addTile(int theX, int theY, String thePath) {
        JLabel tileLabel = null;
        try {
            tileLabel = new JLabel(new ImageIcon(ImageIO.read(new File(thePath))));
        } catch (IOException e) {
            System.out.println("There was an issue loading the Entity with path " + thePath + "\n with error "  + e);
            return;
        }
        if (myPanels[theY][theX].getComponents().length != 0){
            myPanels[theY][theX].removeAll();
        }
        myPanels[theY][theX].add(tileLabel,0);
    }
}
