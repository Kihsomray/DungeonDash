package view.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Map extends JPanel {

    private int myWidth;
    private int myHeight;

    private int size = 64; // Number of pixels each art asset is

    public Map(int myWidth, int myHeight) {
        super();
        this.setLayout(new GridLayout(myHeight, myWidth)); // GONNA TRY WITHOUT LAYOUT MANGAGER
        //this.setLayout(null);

        String greyTile = "res" + File.separator + "GreyTile.jpg";
        String playerPath = "res" + File.separator + "SamplePlayerCharacter.png";


        File greyTileFile = new File(greyTile);
        System.out.println(greyTileFile);
        BufferedImage greyTileImage;

        try {
            greyTileImage = ImageIO.read(greyTileFile);

        } catch (Exception e) {
            System.out.println("something bad just happened " + e);
            return;
        }


        JComponent[][] mapPanels = new JComponent[myWidth][myHeight];

//        try {
//            JLabel player = new JLabel(new ImageIcon(ImageIO.read(new File(playerPath))));
//            player.setBounds(1 * size, 1 * size, size, size);
//            mapPanels[1][1] = (player);
//            this.add(player);
//        } catch (Exception e) {
//            System.out.println("Something Bad just happened" + e);
//        }

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {

                //if (i == j && j == 1) {continue;}
                //mapPanels[i][j] = new JPanel();
                //mapPanels[i][j].add(new JLabel(new ImageIcon(greyTileImage)));
                mapPanels[i][j] = new JLabel(new ImageIcon(greyTileImage));

                mapPanels[i][j].setPreferredSize(new Dimension(64,64));

//                mapPanels[i][j].setBounds(i * size, j * size, size, size);

                this.add(mapPanels[i][j]);
            }
        }


    }

    void createTheTileMap() {
        String realPath = "res" + File.separator + "GreyTile.jpg";
        //System.out.println(getClass().getResource("/res/GreyTile.jpg").getPath());
        //System.out.println("/res/GreyTile.jpg");
        File greyTileFile = new File(realPath);
        System.out.println(greyTileFile);
        BufferedImage greyTileImage;

        try {
            greyTileImage = ImageIO.read(greyTileFile);

        } catch (Exception e) {
            System.out.println("something bad just happened " + e);
            return;
        }


        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                this.add(new JLabel(new ImageIcon(greyTileImage)));
            }
        }
    }
}
