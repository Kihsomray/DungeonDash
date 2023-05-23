package model.inventory;

public class Inventory {

    private int myHealthPotion;
    private int myVisionPotion;
    private final char[] myPillarsObtained;

    public Inventory() {
        myHealthPotion = 0;
        myVisionPotion = 0;
        myPillarsObtained = new char[]{' ', ' ', ' ', ' '};
    }

    public int getHealthPotions() {
        return myHealthPotion;
    }

    public int getVisionPotions() {
        return myVisionPotion;
    }

    public char[] getPillars() {
        return myPillarsObtained;
    }

    public String grabPillar(final char thePillar) {
        StringBuilder sb = new StringBuilder();
        sb.append("You found the pillar of ");
        if (thePillar == 'A') {
            sb.append("Abstraction! Your [effect goes here]!");
            // Do some buff to the character.

            // Add it to the obtained pillars.
            myPillarsObtained[0] = 'A';
        }
        else if (thePillar == 'E') {
            sb.append("Encapsulation! " +
                    "Your potion efficacy has increased!");

            // Add it to the obtained pillars.
            myPillarsObtained[1] = 'E';
        }
        else if (thePillar == 'I') {
            sb.append("Inheritance! Your [effect goes here]!");
            // Do some buff to the character.

            // Add it to the obtained pillars.
            myPillarsObtained[2] = 'I';
        }
        else if (thePillar == 'P') {
            sb.append("Polymorphism! Your [effect goes here]!");
            // Do some buff to the character.

            // Add it to the obtained pillars.
            myPillarsObtained[3] = 'P';
        }
        return sb.toString();
    }

    public boolean hasPotion(final boolean theUsePotion) {
        if (myHealthPotion > 0) {
            if (theUsePotion) myHealthPotion--;
            return true;
        }
        return false;
    }
}
