package view.gui;


import model.dungeon.Dungeon;
import model.dungeon.tile.Cell;
import model.dungeon.tile.passable.Passable;
import model.dungeon.tile.passable.Room;
import model.inventory.item.Item;
import model.sprite.enemy.Enemy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Map extends JPanel {

    private final int myWidth;
    private final int myHeight;

    /** This is where the map is stored. In this 2d array, there are JPanels which contain the art for each tile. */
    private final JComponent[][] myPanels;

    private final static int PIXELS_IN_SPRITE = 64; // Number of pixels each art asset is

    public Map(int theWidth, int theHeight, Dungeon theDungeon) {
        super();

        myWidth = theWidth;
        myHeight = theHeight;

        this.setLayout(new GridLayout(myHeight, myWidth));


        // This the tileMap that will have every sprite
        myPanels = new JComponent[myHeight][myWidth];

        createTheTileMap(theDungeon.getMaze());

        //AddCharacter(0, 0);
        addEntities(theDungeon.getMaze());

        addPlayer(theDungeon);
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
}
