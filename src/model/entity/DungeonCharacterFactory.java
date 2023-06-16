package model.entity;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.util.Utility;
import model.entity.enemy.monster.Monster;
import model.entity.enemy.monster.MonsterData;

import java.util.ArrayList;
import java.util.List;

/**
 * A factory to build Monsters/Heroes from a SQLite database.
 *
 * @version 1.0.1
 * @author Patrick Hern
 * @author Kihsomray
 */
public class DungeonCharacterFactory {

    /** ArrayList of Monsters to randomly select from. **/
    private static final List<MonsterData> MONSTER_DATA = readFromSQLiteDB();

    /**
     * Used to make this as a utility class.
     */
    private DungeonCharacterFactory() {
        // Nothing.
    }

    /**
     * Opens the database connection to "DungeonCharacters.db", queries for
     * all monsters, adds them to an ArrayList, then closes.
     */
    private static List<MonsterData> readFromSQLiteDB() {

        // SQLite source.
        SQLiteDataSource dataSrc = null;

        // Open the data source, if you can.
        try {

            dataSrc = new SQLiteDataSource();
            dataSrc.setUrl("jdbc:sqlite:DungeonCharacters.db");

        } catch (final Exception theException) {

            theException.printStackTrace();
            System.exit(0);

        }

        // Query the Monsters table for all monsters.
        String queryMonstersTable = "SELECT * FROM Monsters ORDER BY Name";

        // List of data.
        final List<MonsterData> data = new ArrayList<>();

        // Open connection to the database and create a statement.
        try (
                Connection conn = dataSrc.getConnection();
                Statement stmt = conn.createStatement()
        ) {

            // The results from the query.
            ResultSet results = stmt.executeQuery(queryMonstersTable);

            // Read in the data, and add a new monster to the ArrayList
            while(results.next()) {

                data.add(
                        new MonsterData (
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
            results.close();

        } catch (final SQLException theException) {

            theException.printStackTrace();
            System.exit(0);

        }

        return data;

    }

    /**
     * Generates a random monster: Ogre, Gremlin, Skeleton.
     * Has a 1 in 3 chance of generating.
     *
     * @return Randomly generated monster.
     */
    public static Monster generateMonster() {

        // Randomly select a monster from the defined list.
        return new Monster(
                MONSTER_DATA.get(Utility.RANDOM.nextInt(MONSTER_DATA.size()))
        );

    }

}
