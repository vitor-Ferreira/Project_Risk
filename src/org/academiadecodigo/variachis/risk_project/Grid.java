package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Movable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid implements Movable { 

    public static final int PADDING = 10;

    private int cols;
    private int rows;

    //private GridPosition gridPosition;

    private Rectangle gridRect; //a grelha em si - o field, podemos dizer.
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle selectRect; //rectangulo vermelho de seleçao.

    private int cellSize = 300;

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void canvas() {

        Canvas.getInstance();
    }

    public void init() {

        gridRect = new Rectangle(PADDING, PADDING, cellSize, cellSize * rows);
        gridRect.setColor(Color.BLUE);
        gridRect.draw();

        rectangle1 = new Rectangle(columnToX(0), rowToY(0), cellSize, cellSize);
        rectangle1.setColor(Color.GREEN);
        rectangle1.draw();

        rectangle2 = new Rectangle(columnToX(0), rowToY(1), cellSize, cellSize);
        rectangle2.setColor(Color.GREEN);
        rectangle2.draw();

        rectangle3 = new Rectangle(columnToX(0), rowToY(2), cellSize, cellSize);
        rectangle3.setColor(Color.GREEN);
        rectangle3.draw();

        /* selectRect = new Rectangle(columnToX(0), rowToY(0), cellSize, cellSize);
        selectRect.setColor(Color.RED);
        selectRect.draw(); */
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