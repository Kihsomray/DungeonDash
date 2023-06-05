package view.gui;

import model.dungeon.Dungeon;
import model.inventory.item.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InventoryGUI extends JPanel {

    private final int myRows = 3;
    private final int myColumns = 4;

    private final Dungeon myDungeon;

    private final JComponent[][] myPanels;

    private final String healthSlotPath = "res" + File.separator + "InventoryRes" + File.separator + "HealthSlot.png";
    private final String visionSlotPath = "res" + File.separator + "InventoryRes" + File.separator + "VisionSlot.png";
    private final String emptySlotPath = "res" + File.separator + "InventoryRes" + File.separator + "EmptySlot.png";

    public InventoryGUI(Dungeon theDungeon) {
        super();

        myDungeon = theDungeon;

        this.setLayout(new GridLayout(myRows, myColumns));


        // This the tileMap that will have every sprite
        myPanels = new JComponent[myRows][myColumns];

        for (int i = 0; i < myColumns; i++) {
            for (int j = 0; j < myRows; j++) {
                myPanels[j][i] = new JPanel();
                myPanels[j][i].setLayout(new BorderLayout());

                //myPanels[j][i].setPreferredSize(new Dimension(32,32));

                this.add(myPanels[j][i]);

                JLabel invLabel;
                try {
                    invLabel = new JLabel(new ImageIcon(ImageIO.read(new File(emptySlotPath))));
                } catch (IOException e) {
                    System.out.println("There was an issue loading the Entity with path " + emptySlotPath + "\n with error "  + e);
                    return;
                }
                invLabel.setPreferredSize(new Dimension(32, 32));
                myPanels[j][i].add(invLabel, BorderLayout.CENTER);
            }
        }
    }

    public void updateInvGUI() {
        for (int i = 0; i < myColumns; i++) {
            for (int j = 0; j < myRows; j++) {
                Item item = myDungeon.getHero().getInventory().getItemAt(i, j, false);

                if (item == null) {
                    myPanels[j][i].removeAll();
                    try {
                        myPanels[j][i].add(new JLabel(new ImageIcon(ImageIO.read(new File(emptySlotPath)))));
                    } catch (Exception e) {System.out.println("Issue with loading empty slot"); }
                } else {
                    String path = item.getSlotArtPath();
                    myPanels[j][i].removeAll();
                    try {
                        myPanels[j][i].add(new JLabel(new ImageIcon(ImageIO.read(new File(path)))));
                    } catch (Exception e) {System.out.println("Issue with loading slot"); }
                }
            }
        }
    }

}
