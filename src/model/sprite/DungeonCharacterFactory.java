package model.sprite;

import model.sprite.enemy.monster.MockGremlin;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Utility;
import model.sprite.enemy.monster.Monster;
import model.sprite.enemy.monster.MonsterData;

import java.util.ArrayList;

/**
 * A factory to build Monsters/Heroes from a SQLite database.
 *
 * @version 1.0.0
 * @author Patrick Hern
 * @author Kihsomray
 */
public class DungeonCharacterFactory {
    /** ArrayList of Monsters to randomly select from. **/
    private static final ArrayList<MonsterData> MONSTER_DATA = new ArrayList<>();

    /**
     * Constructor that initializes the ArrayList of Monster and
     * reads input from the database.
     */
    public DungeonCharacterFactory() {
        readFromSQLiteDB();
    }

    /**
     * Opens the database connection to "DungeonCharacters.db", queries for
     * all monsters, adds them to an ArrayList, then closes.
     */
    private void readFromSQLiteDB() {

        SQLiteDataSource dataSrc = null;

        // Open the data source, if you can.
        try {

            dataSrc = new SQLiteDataSource();
            dataSrc.setUrl("jdbc:sqlite:DungeonCharacters.db");

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
            while(results.next()) {

                MONSTER_DATA.add(
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
        }
        catch (SQLException theException) {

            theException.printStackTrace();
            System.exit(0);

        }

    }

    /**
     * Generates a random monster: Ogre, Gremlin, Skeleton.
     * Roughly has a 1 in SIZE chance of generating.
     *
     * @return Randomly generated monster.
     */
    public static Monster generateMonster() {

        // For modularity, a random int is chosen to for each monster.
        final int random = Utility.RANDOM.nextInt(MONSTER_DATA.size());

        MonsterData newMonster = MONSTER_DATA.get(random);

        System.out.println("Creating a: " + newMonster.getName());

        return new Monster (
                newMonster.getName(),
                newMonster.getHP(),
                newMonster.getMinDamage(),
                newMonster.getMaxDamage(),
                newMonster.getAttackSpeed(),
                newMonster.getHitChance(),
                newMonster.getMinHeal(),
                newMonster.getMaxHeal(),
                newMonster.getHealChance()
        );

    }

}
