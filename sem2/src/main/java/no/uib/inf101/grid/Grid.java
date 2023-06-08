package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid<E> implements IGrid<E> {

    private int rows;
    private int cols;
    private ArrayList<GridCell<E>> cells = new ArrayList<GridCell<E>>();

    /**
     * Create a new grid with the given dimensions and default value.
     *
     * @param rows         The number of rows
     * @param cols         The number of columns
     * @param defaultValue The default value for all cells
     */
    public Grid(int rows, int cols, E defaultValue) {
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CellPosition pos = new CellPosition(i, j);
                GridCell<E> cell = new GridCell<>(pos, defaultValue);
                cells.add(cell);
            }
        }
    }

    /**
     * Create a new grid with the given dimensions.
     *
     * @param rows The number of rows
     * @param cols The number of columns
     */
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CellPosition pos = new CellPosition(i, j);
                GridCell<E> cell = new GridCell<>(pos, null);
                cells.add(cell);
            }
        }
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.cols;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        return cells.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).pos().equals(pos)) {
                cells.remove(i);
                cells.add(i, new GridCell<E>(pos, value));
            }
        }
    }

    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException();
        }
        for (GridCell<E> cell : cells) {
            if (cell.pos().equals(pos)) {
                return cell.value();
            }
        }
        return null;
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        int col = pos.col();
        int row = pos.row();
        if (col < 0 || col >= cols()) {
            return false;
        }
        if (row < 0 || row >= rows()) {
            return false;
        }
        return true;
    }
}
