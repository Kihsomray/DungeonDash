package model.sprite;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
 * @author Patrick Hern
 * @author Kihsomray
 */
public class DungeonCharacterFactory {
    private static ArrayList<Monster> myCharactersData;

    public DungeonCharacterFactory() {

        myCharactersData = new ArrayList<>();
        readFromSQLiteDB();

    }

    private void readFromSQLiteDB() {

        SQLiteDataSource dataSrc = null;

        // Open the data source, if you can.
        try {

            dataSrc = new SQLiteDataSource();
            dataSrc.setUrl("jdbc:sqlite:characters.db");

        }
        catch (Exception theException) {

            theException.printStackTrace();
            System.exit(0);

        }

        // Query the Monsters table for all monsters.
        String queryMonstersTable = "SELECT * FROM Monsters ORDER BY Name";

        // Open connection to the database and create a statement.
        try (
                Connection conn = dataSrc.getConnection();
                Statement stmt = conn.createStatement()
        ) {

            // The results from the query.
            ResultSet results = stmt.executeQuery(queryMonstersTable);

            // Read in the data, and add a new monster to the ArrayList
            while( results.next() ) {

                myCharactersData.add(
                        new Monster (
                                results.getString("Name"        ),
                                results.getInt(   "HP"          ),
                                results.getInt(   "MinDamage"   ),
                                results.getInt(   "MaxDamage"   ),
                                results.getInt(   "AttackSpeed" ),
                                results.getDouble("HitChance"   ),
                                results.getInt(   "MinHeal"     ),
                                results.getInt(   "MaxHeal"     ),
                                results.getDouble("HealChance"  )
                        ) {
                });

            }
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

        // If the database is found, then use the Database's values.
        if (random < 0.333333) return myCharactersData.get(0);
        else if (random < 0.666667) return myCharactersData.get(1);
        else return myCharactersData.get(2);

//        // If the database is not found, use the MockObjects.
//        if (random < 0.333333) return new Ogre();
//        else if (random < 0.666667) return new Gremlin();
//        else return new Skeleton();

    }



}
