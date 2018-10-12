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

    private Rectangle[][] cellsArray = new Rectangle[6][6];

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

    public void showNumberSoldiers() {

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {

                numSoldiers = Integer.toString(territory[i][j].getSoldiers());

                if (territory[i][j].getPlayer() == null) {
                    territory[i][j].setText(i * cellSize + 0.5 * cellSize,j * cellSize + 0.5 * cellSize, Color.WHITE);
                    continue;
                }

                if (territory[i][j].getPlayer().getColor().equals("Red")) {
                    territory[i][j].setText(i * cellSize + 0.5 * cellSize, j * cellSize + 0.5 * cellSize,Color.RED);
                }

                if (territory[i][j].getPlayer().getColor().equals("Blue")) {
                    territory[i][j].setText(i * cellSize + 0.5 * cellSize, j * cellSize + 0.5 * cellSize,Color.BLUE);
                }
            }
        }
    }
}