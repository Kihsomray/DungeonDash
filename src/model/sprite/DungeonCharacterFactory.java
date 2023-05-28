package model.sprite;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.Utility;
import model.sprite.enemy.monster.Gremlin;
import model.sprite.enemy.monster.Monster;
import model.sprite.enemy.monster.Ogre;
import model.sprite.enemy.monster.Skeleton;

import java.util.ArrayList;

/**
 * A factory to build Monsters/Heroes from a SQLite database.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
 */
public class DungeonCharacterFactory {
    private static final ArrayList<DungeonCharacter> myCharactersData = new ArrayList<>();

    public DungeonCharacterFactory() {
        readFromSQLiteDB();
    }

    private void readFromSQLiteDB() {
        try {
            SQLiteDataSource dataSrc = new SQLiteDataSource();
            dataSrc.setUrl("jdbc:sqlite:characters.mtp");
        }
        catch (SQLException theException) {
            theException.printStackTrace();
            System.exit(0);
        }

    }

    /**
     * Generates a random monster: Ogre, Gremlin, Skeleton.
     *
     * Roughly has a 1 in 3 chance of generating.
     *
     * @return Randomly generated monster.
     */
    public static Monster generateMonster() {

        final double random = Utility.RANDOM.nextDouble();
        if (random < 0.333333) return new Ogre();
        else if (random < 0.666667) return new Gremlin();
        else return new Skeleton();

    }



}
