package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid implements Movable {

    public static final int PADDING = 10;

    private int cols;
    private int rows;
    private Territory territory;

    //private GridPosition gridPosition;

    private Picture backgroundPicture;
    private Rectangle gridRect; //a grelha em si - o field, podemos dizer.
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle selectRect; //rectangulo vermelho de seleçao.

    private int cellSize = 200;

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void canvas() {

        Canvas.getInstance();
    }

    public void init() {


        gridRect = new Rectangle(PADDING, PADDING, cellSize, cellSize);
        backgroundPicture = new Picture(PADDING, PADDING, "Resources/terrain.jpg");
        backgroundPicture.draw();
        gridRect.setColor(Color.BLUE);
        gridRect.draw();

        rectangle1 = new Rectangle(columnToX(0), rowToY(0), cellSize, cellSize);

        rectangle1.draw();
        rectangle1.setColor(Color.BLACK);


        rectangle2 = new Rectangle(columnToX(0), rowToY(1), cellSize, cellSize);
        rectangle2.draw();


        rectangle3 = new Rectangle(columnToX(0), rowToY(2), cellSize, cellSize);
        rectangle3.setColor(Color.BLACK);
        rectangle3.draw();

        /* selectRect = new Rectangle(columnToX(0), rowToY(0), cellSize, cellSize);
        selectRect.setColor(Color.RED);
        selectRect.draw(); */
    }

    public void playerImagesShow() {
        Picture playerIcon = new Picture(columnToX(0) - 150, rowToY(0) - 150, "Resources/tank.png");
        playerIcon.draw();
        playerIcon.grow(-150, -150);

    }

    public void soldierNumber() {

        if (territory.getPlayer().getColor().equals("RED")) {
            Text text = new Text(columnToX(getCols()) + cellSize * 0.4, rowToY(getRows()) + cellSize * 0.5, "20");
            text.grow(25, 25);
            text.draw();
            text.setColor(Color.RED);
        }
        if (territory.getPlayer().getColor().equals("BLUE")) {
            Text text = new Text(columnToX(getCols()) + cellSize * 0.4, rowToY(getRows()) + cellSize * 0.5, "20");
            text.grow(25, 25);
            text.draw();
            text.setColor(Color.BLUE);
        }

    }

    /*public void player2ImagesShow(){
        Picture playerIcon = new Picture(columnToX(2) -550 , rowToY(2) -170 , "Resources/tank.png");
        playerIcon.draw();
        playerIcon.grow(-150, -150);
        Text text = new Text(columnToX(2) - 320, rowToY(2) + 80, "20");
        text.grow(25,25);
        text.setColor(Color.BLUE);
        text.draw();
    }*/

    public void player1ImagesHide() {
        Picture playerIcon = new Picture(columnToX(0) - 150, rowToY(0) - 150, "Resources/tank.png");
        playerIcon.draw();
        playerIcon.grow(-150, -150);
        playerIcon.delete();
        Text text = new Text(columnToX(0) + cellSize * 0.4, rowToY(0) + cellSize * 0.5, "20");
        text.grow(25, 25);
        text.setColor(Color.RED);
        text.draw();
        text.delete();
    }

    public void player2ImagesHide() {
        Picture playerIcon = new Picture(columnToX(2) - 550, rowToY(2) - 170, "Resources/tank.png");
        playerIcon.draw();
        playerIcon.grow(-150, -150);
        playerIcon.delete();
        Text text = new Text(columnToX(2) - 320, rowToY(2) + 80, "20");
        text.grow(25, 25);
        text.setColor(Color.BLUE);
        text.draw();
        text.delete();

    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int setCellSize() {
        //int cellSize = 100;
        return cellSize;
    }

    public int getWidth() {
        return gridRect.getWidth();
    }

    public int getHeight() {
        return gridRect.getHeight();
    }

    public int getX() {
        return gridRect.getX();
    }

    public int getY() {
        return gridRect.getY();
    }

    public int columnToX(int column) {
        return column * getCellSize() + PADDING;
    }

    public int rowToY(int row) {
        return row * getCellSize() + PADDING;
    }

    @Override
    public void translate(double v, double v1) {

    }
}
