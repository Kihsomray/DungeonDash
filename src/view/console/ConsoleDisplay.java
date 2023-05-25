package view.console;

import controller.DungeonAdventure;
import view.DungeonGUI;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleDisplay implements DungeonGUI {

    private final DungeonAdventure myMain;

    public ConsoleDisplay(final DungeonAdventure theMain) {

        myMain = theMain;

    }

    public void display() {

        while(true) {

            System.out.println(myMain.getDungeon());

            switch (new Scanner(System.in)
                    .next()
                    .toUpperCase(Locale.ROOT)
                    .charAt(0)
            ) {

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


            }







        }




    }



}
