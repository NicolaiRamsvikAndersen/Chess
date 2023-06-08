package no.uib.inf101.sem2.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.sem2.model.piece.*;

public class ChessBoard extends Grid<Piece> {

    /**
     * Create a new ChessBoard.
     * 
     * @param rows Number of rows
     * @param cols Number of columns
     */
    public ChessBoard(int rows, int cols) {
        super(rows, cols);
        fillBoard();
        //dummyBoard();
    }

    /**
     * Create a new ChessBoard with a default value.
     * 
     * @param rows Number of rows
     * @param cols Number of columns
     * @param defaultValue Default value for all cells
     */
    public ChessBoard(int rows, int cols, Piece defaultValue) {
        super(rows, cols, defaultValue);
    }

    private void dummyBoard() {
        this.set (new CellPosition(2, 4), new Pawn(true));
        this.set (new CellPosition(7, 7), new King(true));
        this.set (new CellPosition(1, 7), new King(false));
        this.set (new CellPosition(5, 4), new Pawn(false));
    }

    private void fillBoard() {
        boolean isWhite = false;
        for (int row = 0; row < this.rows(); row++) {
            for (int col = 0; col < this.cols(); col++) {
                CellPosition pos = new CellPosition(row, col);
                if (row < 2)
                    isWhite = false;
                else if (row > this.rows() - 3)
                    isWhite = true;
                if (row == 0 || row == this.rows() - 1) {
                    if (col == 0 || col == this.cols() - 1)
                        this.set(pos, new Rook(isWhite));
                    if (col == 1 || col == this.cols() - 2)
                        this.set(pos, new Knight(isWhite));
                    if (col == 2 || col == this.cols() - 3)
                        this.set(pos, new Bishop(isWhite));
                    if (col == 3)
                        this.set(pos, new Queen(isWhite));
                    if (col == 4)
                        this.set(pos, new King(isWhite));
                }
                if (row == 1 || row == this.rows() - 2) {
                    this.set(pos, new Pawn(isWhite));
                }
            }
        }
    }

    /**
     * Makes a string representation of the board.
     * 
     * @return String representation of the board.
     */
    public String prettyString() {
        String prettyString = "";
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                CellPosition pos = new CellPosition(i, j);
                prettyString += this.get(pos);
            }
            if (i < rows() - 1)
                prettyString += "\n";
        }
        return prettyString;
    }
}
