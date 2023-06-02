package model.dungeon.generator;

import model.dungeon.Dungeon;
import model.dungeon.cell.Cell;
import model.dungeon.cell.passable.Door;
import model.entity.hero.Hero;

import java.io.*;

public class SaveLoadGenerator implements DungeonGenerator {
    private Dungeon myDungeonState;
    public SaveLoadGenerator(Dungeon theDungeonState) {
        // Load the dungeon in
        myDungeonState = null;
    }

    public void saveDungeonState(Dungeon theDungeon) {
        String dungeonSave = "/dungeonData.ser";

        try {
            // Save the Dungeon in a file.
            FileOutputStream fileStream = new FileOutputStream(dungeonSave);

            ObjectOutputStream outStream = new ObjectOutputStream((fileStream));

            // Method to serialize object.
            outStream.writeObject(theDungeon);

            outStream.close();
            fileStream.close();

            System.out.println("Game has been saved.");

        }
        catch (IOException theIOException) {
            System.out.println("IOException, could not save dungeon:\n" + theIOException);
        }
    }

    public Dungeon loadDungeonState() {
        Dungeon dungeonToLoad = null;

        try {
            // Read in the object from a file.
            FileInputStream fileStream = new FileInputStream("/dungeonData.ser");

            ObjectInputStream inStream = new ObjectInputStream((fileStream));

            // Deserialize the Dungeon.
            dungeonToLoad = (Dungeon) inStream.readObject();
            System.out.println("Pass");

            inStream.close();
            fileStream.close();
        }
        catch (IOException theIOException) {
            System.out.println("IOException, could not load dungeon: \n" + theIOException);
        }
        catch (ClassNotFoundException theCNFException) {
            System.out.println("ClassNotFoundException, could not find class.");
        }

        return dungeonToLoad;
    }

    /**
     * Generates a dungeon based on implementation.
     *
     * @return Generated 2D array of cells.
     */
    @Override
    public Cell[][] generate() {

        return myDungeonState.getMaze();

    }

    /**
     * Gets the number of rooms in the generated dungeon.
     *
     * @return Number of rooms in dungeon.
     */
    @Override
    public int getRoomCount() {
        return myDungeonState.getRoomCount();
    }

    /**
     * Gets the entrance of the dungeon.
     *
     * @return Entrance of the dungeon.
     */
    @Override
    public Door getEntrance() {
        return new Door(0, 0, true);
    }

    /**
     * Gets the exit of the dungeon.
     *
     * @return Exit of the dungeon.
     */
    @Override
    public Door getExit() {
        return new Door(0, 0, false);
    }

    /**
     * Gets the height of the dungeon.
     *
     * @return Height of dungeon.
     */
    @Override
    public int getHeight() {
        return myDungeonState.getMyHeight() + 1;
    }

    /**
     * Gets the width of the dungeon.
     *
     * @return Width of dungeon.
     */
    @Override
    public int getWidth() {
        return myDungeonState.getMyWidth() + 1;
    }

    /**
     * Gets the hero spawned in the dungeon.
     *
     * @return Hero spawned in the dungeon.
     */
    @Override
    public Hero getHero() {
        return myDungeonState.getHero();
    }

}
