package model.heroes;

public class Inventory {

    private final int myHealthPotion;
    private final int myVisionPotion;
    private final char[] myPillarsObtained;

    public Inventory() {
        myHealthPotion = 0;
        myVisionPotion = 0;
        myPillarsObtained = new char[4];
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

}
