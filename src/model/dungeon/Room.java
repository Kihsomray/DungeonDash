package model.dungeon;

//import java.util.Random;

public class Room {
    private final char myContents;
    private final double myPotionChance;
//    private final char myPotion;

    public Room(final char theContents) {
        myContents = theContents;
        myPotionChance = 0.5;

//        Eventually, generate a potion for a given room.
//        Random r = new Random();
//        if (r.nextDouble() > myPotionChance) {
//            r.nextDouble() > 0.5 ? myPotion = 'h' : myPotion = 'v';
//        } else {
//            myPotion = 'n';
//        }
    }

    public char getContents() {
        return myContents;
    }

//    public char getMyPotion() {
//        return myPotion;
//    }
}


