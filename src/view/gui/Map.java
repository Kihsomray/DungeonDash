package view.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Map extends JPanel {

    private int myWidth;
    private int myHeight;

    private JComponent[][] myPanels;

    private int size = 64; // Number of pixels each art asset is

    public Map(int myWidth, int myHeight) {
        super();
        this.setLayout(new GridLayout(myHeight, myWidth)); // GONNA TRY WITHOUT LAYOUT MANGAGER
        //this.setLayout(null);

        String greyTile = "res" + File.separator + "GreyTile.jpg";


        File greyTileFile = new File(greyTile);
        System.out.println(greyTileFile);
        BufferedImage greyTileImage;

        try {
            greyTileImage = ImageIO.read(greyTileFile);

        } catch (Exception e) {
            System.out.println("something bad just happened " + e);
            return;
        }

        myPanels = new JComponent[myHeight][myWidth];

        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                myPanels[i][j] = new JPanel();
                myPanels[i][j].setLayout(new OverlayLayout(myPanels[i][j]));
                myPanels[i][j].add(BorderLayout.CENTER,new JLabel(new ImageIcon(greyTileImage)));
                myPanels[i][j].setPreferredSize(new Dimension(64,64));

                this.add(myPanels[i][j]);
            }
        }

        AddCharacter();


//        for (int i = 0; i < myWidth; i++) {
//            for (int j = 0; j < myHeight; j++) {
//
//                //if (i == j && j == 1) {continue;}
//                //mapPanels[i][j] = new JPanel();
//                //mapPanels[i][j].add(new JLabel(new ImageIcon(greyTileImage)));
//                mapPanels[i][j] = new JLabel(new ImageIcon(greyTileImage));
//
//                mapPanels[i][j].setPreferredSize(new Dimension(64,64));
//
////                mapPanels[i][j].setBounds(i * size, j * size, size, size);
//
//                this.add(mapPanels[i][j]);
//            }
//        }


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

    private void AddCharacter() {
        try {

            String playerPath = "res" + File.separator + "SamplePlayerCharacter.png";
            JLabel player = new JLabel(new ImageIcon(ImageIO.read(new File(playerPath))));
            //JComponent cur = (JComponent) mapPanels[1][1].getComponent(0);
            //mapPanels[1][1].removeAll();
            myPanels[0][13].add(player,0);
            //mapPanels[1][1].add(cur);
//            player.setBounds(1 * size, 1 * size, size, size);
//            mapPanels[1][1] = (player);
//            this.add(player);
        } catch (Exception e) {
            System.out.println("Something Bad just happened" + e);
        }
    }
}
