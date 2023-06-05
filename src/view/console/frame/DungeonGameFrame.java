package view.console.frame;

import model.dungeon.Dungeon;
import model.entity.battle.Battle;
import model.entity.hero.Hero;
import model.inventory.item.Item;
import model.util.Utility;
import view.console.pattern.Color;
import view.console.pattern.ProgressBar;
import view.console.util.ConsoleDisplayUtility;

import java.util.Locale;
import java.util.Scanner;

public class DungeonGameFrame extends ConsoleFrame {

    private static final String[] ASK_TOM = {
            "USE AGILE METHODS!",
            "UTILIZE OOP PRINCIPLES!",
            "YOU'RE GOING TO MAKE ME CRY",
            "WE'VE TALKED ABOUT THIS ALL QTR LONG!",
            ""
    };

    private final Dungeon myDungeon;
    private final Hero myHero;

    public DungeonGameFrame(final Dungeon theDungeon) {

        myDungeon = theDungeon;
        myHero = theDungeon.getHero();

    }

    public void display() {

        // While the game is in progress.
        while (myDungeon.isGamePlaying()) {

            // Print the dungeon.
            System.out.println(generate());

            // Get the user input.
            char input = new Scanner(System.in)
                    .nextLine()
                    .toUpperCase(Locale.ROOT)
                    .charAt(0);

            // If there is no hero battle.
            if (myHero.getBattle() == null) {

                Hero.MovementResult movementResult;

                // Check input.
                switch (input) {

                    case 'W':
                        movementResult = myHero.moveNorth();
                        break;

                    case 'A':
                        movementResult = myHero.moveWest();
                        break;

                    case 'S':
                        movementResult = myHero.moveSouth();
                        break;

                    case 'D':
                        movementResult = myHero.moveEast();
                        break;

                    case '1', '2', '3', '4', '5', '6', '7', '8':
                        myHero.useInventoryItem(input - 48);
                        continue;

                    case 'N':
                        // Save dungeon's current state.
                        Utility.saveDungeonState(myDungeon);
                        continue;

                }

                // If there is a battle.
            } else {

                Battle.Result result;
                switch (input) {

                    case 'A':
                        result = myHero.getBattle().heroAttackMonster(Battle.Option.ATTACK);
                        break;

                    case 'S':
                        result = myHero.getBattle().heroAttackMonster(Battle.Option.ABILITY);
                        break;

                    case 'H':
                        result = myHero.getBattle().heroAttackMonster(Battle.Option.HEAL);
                        break;

                    case 'T':
                        System.out.println();
                        break;



                }

                // "Wait" for the monster to attack.
                try {

                    // Wait between 0.5 and 3.0 seconds.
                    Thread.sleep(Utility.RANDOM.nextLong(
                            ConsoleDisplayUtility.MIN_ENEMY_RESPONSE_WAIT_MS,
                            ConsoleDisplayUtility.MAX_ENEMY_RESPONSE_WAIT_MS)
                    );

                } catch (final InterruptedException ie) {

                    // Some wack error.
                    // TODO put some string here.
                }

                myHero.getBattle().monsterAttackHero();


            }

        }
    }

    private String generate() {

        return generateHeroInfo() + generateInventoryInfo();

    }

    private String generateHeroInfo() {

        // String builder.
        final StringBuilder sb = new StringBuilder();

        // Top border of hero info.
        appendSimpleHorizontal(sb);

        // Type of hero displayed.
        appendTextVertical(sb,
                "HERO-" + myHero.getClass().getSimpleName(),
                false
        );

        // Set color to green for next append.
        PATTERN.setTextColor(Color.GREEN);

        // Name of hero
        appendTextVertical(sb, myHero.getName(), true);

        // Middle border of hero info.
        appendSimpleHorizontal(sb);

        // Set color back to white.
        PATTERN.setTextColor(Color.WHITE);

        // Empty with vertical borders.
        appendTextVertical(sb, " ", false);

        // Current hero health bar.
        final String healthBar =
                myHero.getBattle() == null ?
                        ProgressBar.generate(
                                myHero.getHP(),
                                myHero.getMaxHP(),
                                18
                        ) :
                        ProgressBar.generate(
                                myHero.getHP(),
                                myHero.getHP() +
                                        myHero.getLastDamage(),
                                myHero.getMaxHP(),
                                18
                        );

        // Hero HP information.
        sb
                .append(
                PATTERN.generateVerticalBorder(1,
                        LEFT_MENU_WIDTH,
                        GENERIC_SEPARATOR_3 + Color.WHITE + "HP:   [" +
                                healthBar + Color.WHITE + "]",
                        false,
                        false)
                )
                .append('\n');

        // Current hero ability bar.
        final String abilityBar = ProgressBar.generate(
                myHero.getBattle() != null ?
                        (myHero.getBattle().hasAbility() ? 100 : 0) :
                        100, 100, 18);

        // Hero ability information.
        sb
                .append(
                        PATTERN.generateVerticalBorder(1,
                                LEFT_MENU_WIDTH,
                                Color.WHITE + "ABILITY: [" +
                                        abilityBar + Color.WHITE + "]",
                                false,
                                false)
                )
                .append('\n');

        // Top of coordinate box.
        sb
                .append(
                        PATTERN.generateVerticalBorder(
                                1,
                                LEFT_MENU_WIDTH,
                                " ".repeat(6) + "_".repeat(17) + " ".repeat(6),
                                false,
                                false)
                )
                .append('\n');

        // Coordinates of hero.
        final int x = myHero.getCurrentPassable().getX();
        final int y = myHero.getCurrentPassable().getY();

        // Current hero coordinate info.
        final String coordinateInfo = "     |  " + Color.WHITE + "X:" + Color.CYAN +
                (x > 8 ? "" : "0") + (x + 1) + Color.GREY + "  |  " +
                Color.WHITE + "Y:" + Color.CYAN + (y > 8 ? "" : "0") +
                (y + 1) + Color.GREY + "  |     ";

        // Hero coordinate info.
        sb
                .append(
                        PATTERN.generateVerticalBorder(
                                1,
                                LEFT_MENU_WIDTH,
                                coordinateInfo,
                                false,
                                false
                        )
                )
                .append('\n');

        // Bottom border of hero info.
        appendSimpleHorizontal(sb);

        return sb.append(" ".repeat(33)).append('\n').toString();

    }

    private String generateInventoryInfo() {

        // String builder.
        final StringBuilder sb = new StringBuilder();

        // Top border of inventory info.
        appendSimpleHorizontal(sb);

        // Type of hero displayed.
        appendTextVertical(sb, "INVENTORY", true);

        // Middle border of inventory info.
        appendSimpleHorizontal(sb);

        // Pillar inventory bar.
        appendInventoryRow(sb, 0, 4, Color.YELLOW + "?");

        // Usable items header 1.
        appendRowHeaderBar(sb, 0, 4);

        // Usable items row 1.
        appendInventoryRow(sb, 4, 8, Color.GREEN + "+");

        // Usable items header 2.
        appendRowHeaderBar(sb, 4, 8);

        // Usable items row 1.
        appendInventoryRow(sb, 8, 12, Color.GREEN + "+");

        // Bottom border of inventory info.
        appendSimpleHorizontal(sb);

        return sb.toString();

    }

    private void appendSimpleHorizontal(
            final StringBuilder theStringBuilder
    ) {

        // Append the horizontal border.
        theStringBuilder
                .append(PATTERN.generateHorizontalBorder(LEFT_MENU_WIDTH))
                .append('\n');

    }

    private void appendTextVertical(
            final StringBuilder theStringBuilder,
            final String theText,
            boolean theTripleBorder
    ) {

        // Append the vertical border with text.
        theStringBuilder
                .append(PATTERN.generateVerticalBorder(
                        theTripleBorder,
                        LEFT_MENU_WIDTH,
                        theText))
                .append('\n');

    }

    private void appendInventoryRow(
            final StringBuilder theStringBuilder,
            final int theStart,
            final int theEnd,
            final String theEmptyChar

    ) {

        for (int i = theStart; i < theEnd; i++) {
            final Item item = myHero.getInventory().getSlots()[i];
            theStringBuilder
                    .append(PATTERN.generateSingle())
                    .append(GENERIC_SEPARATOR_3)
                    .append(item == null ?
                            theEmptyChar :
                            item.getColoredDisplay())
                    .append(GENERIC_SEPARATOR_3);
        }
        theStringBuilder.append(PATTERN.generateSingle()).append('\n');

    }

    private void appendRowHeaderBar(
            final StringBuilder theStringBuilder,
            final int theStart,
            final int theEnd
    ) {

        for (int i = theStart + 1; i < theEnd + 1; i++) {
            theStringBuilder
                    .append(PATTERN.generateSingle())
                    .append(" -(")
                    .append(Color.WHITE)
                    .append(i)
                    .append(Color.GREY)
                    .append(")- ");
        }
        theStringBuilder.append(PATTERN.generateSingle()).append('\n');

    }

}
