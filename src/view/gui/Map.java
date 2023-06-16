package view.gui;


import model.dungeon.Dungeon;
import model.dungeon.cell.Cell;
import model.dungeon.cell.passable.Passable;
import model.dungeon.cell.passable.Room;
import model.entity.enemy.Enemy;
import model.entity.hero.Hero;
import model.inventory.item.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Map extends JPanel {

    private final int myWidth;
    private final int myHeight;

    /** This is where the map is stored. In this 2d array, there are JPanels which contain the art for each tile. */
    private final JComponent[][] myPanels;

    private final static int PIXELS_IN_SPRITE = 64; // Number of pixels each art asset is

    private Dungeon myDungeon;

    private JFrame myFrame;

    private InventoryGUI myInvGUI;

    public Map(int theWidth, int theHeight, Dungeon theDungeon, JFrame theFrame) {
        super();

        myWidth = theWidth;
        myHeight = theHeight;

        myDungeon = theDungeon;
        myFrame = theFrame;

        this.setLayout(new GridLayout(myHeight, myWidth));


        // This the tileMap that will have every sprite
        myPanels = new JComponent[myHeight][myWidth];

        createTheTileMap(theDungeon.getMaze());

        //AddCharacter(0, 0);
        addEntities(theDungeon.getMaze());

        addPlayer(theDungeon);

//        generateClouds();

        addKeyBinds();
    }

    private void createTheTileMap(Cell[][] theMaze) {
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                myPanels[i][j] = new JPanel();
                myPanels[i][j].setLayout(new OverlayLayout(myPanels[i][j]));

                myPanels[i][j].setPreferredSize(new Dimension(PIXELS_IN_SPRITE,PIXELS_IN_SPRITE));

                this.add(myPanels[i][j]);
            }
        }

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                addTile(i, j, theMaze[i][j].getArtPath());
            }
        }
    }

    private void addEntities(Cell[][] theMaze) {
        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                if (!(theMaze[i][j] instanceof Room)) {
                    continue;
                }
                Set<Item> items = ((Room) theMaze[i][j]).getInventory().getInventory();
                for (Item item : items) {
                    addEntity(i, j, item.getArtPath());
                }
                Set<Enemy> enemies = ((Room) theMaze[i][j]).getEnemies();
                for (Enemy en : enemies) {
                    addEntity(i, j, en.getArtPath());
                }
            }
        }
    }

    private void addPlayer(Dungeon theDungeon) {
        Passable heroRoom = theDungeon.getHero().getCurrentPassable();
        addEntity(heroRoom.getX(), heroRoom.getY(), theDungeon.getHero().getArtPath());
    }

    private void addEntity(int theX, int theY, String thePath) {
        JLabel entityLabel;
        try {
            entityLabel = new JLabel(new ImageIcon(ImageIO.read(new File(thePath))));
        } catch (IOException e) {
            System.out.println("There was an issue loading the Entity with path " + thePath + "\n with error "  + e);
            return;
        }
        myPanels[theY][theX].add(entityLabel,0);
    }

    private void addTile(int theX, int theY, String thePath) {
        JLabel tileLabel;
        try {
            tileLabel = new JLabel(new ImageIcon(ImageIO.read(new File(thePath))));
        } catch (IOException e) {
            System.out.println("There was an issue loading the Entity with path " + thePath + "\n with error "  + e);
            return;
        }
        // Remove all the previous art since if the tile is being changed then nothing else should be visible,
        // nothing else could possibly be on top of a tile
        myPanels[theY][theX].removeAll();
        myPanels[theY][theX].add(tileLabel,0);
    }

    private void addKeyBinds() {
        addKeyBind("W");
        addKeyBind("S");
        addKeyBind("A");
        addKeyBind("D");

        System.out.println("added key binds");
//        Action rightAction = new AbstractAction(){
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("an action was performed");
//            }
//        };
//
//        InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
//        ActionMap actionMap = this.getActionMap();
//
//        inputMap.put(KeyStroke.getKeyStroke("W"), "rightAction");
//        actionMap.put("rightAction", rightAction);
    }

    private void addKeyBind(String key) {
        Action keyAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                moveHero(key);
            }
        };

        InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(key), key + "keyAction");
        actionMap.put(key + "keyAction", keyAction);
    }

    private void moveHero(String theDir) {

        int prevX = myDungeon.getHero().getCurrentPassable().getX();
        int prevY = myDungeon.getHero().getCurrentPassable().getY();

        addTile(prevX, prevY, myDungeon.getMaze()[prevX][prevY].getArtPath());

        if (theDir.equals("W")) {
            myDungeon.getHero().moveSouth();
        } else if (theDir.equals("A")) {
            myDungeon.getHero().moveWest();
        } else if (theDir.equals("S")) {
            myDungeon.getHero().moveNorth();
        } else if (theDir.equals("D")) {
            myDungeon.getHero().moveEast();
        }

        int curX = myDungeon.getHero().getCurrentPassable().getX();
        int curY = myDungeon.getHero().getCurrentPassable().getY();

        addTile(curX, curY, myDungeon.getMaze()[curX][curY].getArtPath());
        addEntity(curX, curY, myDungeon.getHero().getArtPath());

        myInvGUI.updateInvGUI();

        myFrame.revalidate();
    }

    private void generateClouds() {
        Hero hero = myDungeon.getHero();
        String cloudPath = "res" + File.separator + "Cloud.png";


        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                Cell cell = myDungeon.getMaze()[j][i];
                if (cell instanceof Passable && !myDungeon.getHero().hasDiscovered((Passable) cell)) {
                    addTile(j, i, cloudPath);
                }
            }
        }
    }

    public void setInvGUI(InventoryGUI theInvGUI) {
        myInvGUI = theInvGUI;
    }
}
