package view.console;

import controller.DungeonAdventure;
import model.Utility;
import model.sprite.hero.Hero;
import model.sprite.hero.Priestess;
import model.sprite.hero.Thief;
import model.sprite.hero.Warrior;
import view.DungeonGUI;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleDisplay implements DungeonGUI {

    private final DungeonAdventure myMain;

    private static final String INTRO_TIP = "TIP: W for warrior, T for thief, P for priestess\n" +
            "Type letter twice to confirm.";

    public ConsoleDisplay(final DungeonAdventure theMain) {

        myMain = theMain;

    }

    public void display() {

        System.out.println(Utility.generateCharacterMenu('W', INTRO_TIP));

        char chosen = ' ';

        while(true) {

            char input = new Scanner(System.in)
                    .next()
                    .toUpperCase(Locale.ROOT)
                    .charAt(0);

            if (input == 'W' || input == 'T' || input == 'P')
                System.out.println(Utility.generateCharacterMenu(input, INTRO_TIP));

            // if selected twice.
            if (input == chosen) break;
            chosen = input;

        }

        System.out.print("Please input your username (up to 13 characters): ");
        myMain.initializeDungeon(chosen, new Scanner(System.in).nextLine());

        while(true) {

            System.out.println(myMain.getDungeon());

            char input = new Scanner(System.in)
                    .next()
                    .toUpperCase(Locale.ROOT)
                    .charAt(0);

            switch (input) {

                case 'W':
                    myMain.getDungeon().getHero().moveNorth();
                    break;

                case 'A':
                    myMain.getDungeon().getHero().moveWest();
                    break;

                case 'S':
                    myMain.getDungeon().getHero().moveSouth();
                    break;

                case 'D':
                    myMain.getDungeon().getHero().moveEast();
                    break;

                case '1', '2', '3', '4', '5', '6', '7', '8':
                    myMain.getDungeon().getHero().useInventoryItem(input - 48);
                    break;


            }

        }




    }



}
