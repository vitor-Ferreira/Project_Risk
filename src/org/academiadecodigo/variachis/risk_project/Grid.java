package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid {

    public static final int PADDING = 10;

    private int cols;
    private int rows;
    private int x;
    private int y;
    private String numSoldiers;
    private Territory[][] territory;

    private Player p1;

    private Player activePlayer = p1;

    //private GridPosition gridPosition;

    private Rectangle[][] cellsArray = new Rectangle[6][6];
    private Picture moveableImage;

    private int cellSize = 200;

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void init(Territory[][] territories) {

        this.territory = territories;

        Rectangle gridRect = new Rectangle(PADDING, PADDING, cellSize * cols, cellSize * rows);
        Picture backgroundPicture = new Picture(PADDING, PADDING, "Resources/terrain2.jpg");
        backgroundPicture.draw();
        gridRect.setColor(Color.BLACK);
        gridRect.draw();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                //  System.out.println("i: " + i);
                // System.out.println("j: " + j);
                Rectangle cell = new Rectangle(colsToX(i), rowToY(j), cellSize, cellSize);
                cell.setColor(Color.BLACK);
                cell.draw();
                cellsArray[i][j] = cell;
            }
        }
    }

    public int colsToX(int cols) {
        x = cols * cellSize + PADDING;
        return x;
    }

    public int rowToY(int rows) {
        y = rows * cellSize + PADDING;
        return y;
    }

    /* public void movementImage() { //Por argumentos para as posicoes pois vai ser ussado para fazer o set dos 2 players

        //  System.out.println("RED " + territory[0][0].getPlayer());
        // System.out.println("BLUE" + territory[2][2].getPlayer());

        if (territory[2][2].getPlayer().getColor().equals("Blue")) {
            Picture movableImage = territory[2][2].getPlayer().getPicture();
            moveableImage.draw();
        }

        if (territory[0][0].getPlayer().getColor().equals("Red")) {
            //moveableImage = new Picture(PADDING, PADDING, "Resources/tank1.png");

            moveableImage.draw();
        }

    } */

    public void showNumberSoldiers() {

        //System.out.println(territory.getSoldiers());

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {

                // System.out.println("i: " + i);
                // System.out.println("j: " + j);

                numSoldiers = Integer.toString(territory[i][j].getSoldiers());
                //   System.out.println(numSoldiers);

                //  System.out.println("territory array " + territory[i][j]);

                // System.out.println("t + p " + territory[i][j].getPlayer().getColor());

                if (territory[i][j].getPlayer() == null) {
                    Text text = new Text(i * cellSize + 0.5 * cellSize, j * cellSize + 0.5 * cellSize, numSoldiers);
                    text.grow(25, 25);
                    text.draw();
                    text.setColor(Color.WHITE);
                    continue;
                }

                if (territory[i][j].getPlayer().getColor().equals("Red")) {
                    Text text = new Text(i * cellSize + 0.5 * cellSize, j * cellSize + 0.5 * cellSize, numSoldiers);
                    text.grow(25, 25);
                    text.draw();
                    text.setColor(Color.RED);
                }

                if (territory[i][j].getPlayer().getColor().equals("Blue")) {
                    Text text = new Text(i * cellSize + 0.5 * cellSize, j * cellSize + 0.5 * cellSize, numSoldiers);
                    text.grow(25, 25);
                    text.draw();
                    text.setColor(Color.BLUE);
                }
            }
        }
    }
}