package no.uib.inf101.sem2.model.piece;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;

public class Rook extends Piece {

    private boolean hasMoved = false;

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    /**
     * Returns if the rook has moved.
     * 
     * @return true if the rook has moved, false otherwise.
     */
    public boolean hasMoved() {
        return this.hasMoved;
    }

    /**
     * Sets if the rook has moved.
     * 
     * @param hasMoved
     *            true if the rook has moved, false otherwise.
     */
    public void setMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean isLegalMove(ChessBoard board, CellPosition from, CellPosition to) {
        int row = from.row();
        int col = from.col();
        int toRow = to.row();
        int toCol = to.col();

        if (toRow == row && toCol != col) {
            if (toCol > col) {
                for (int i = col + 1; i < toCol; i++) {
                    CellPosition pos = new CellPosition(row, i);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = col - 1; i > toCol; i--) {
                    CellPosition pos = new CellPosition(row, i);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            }
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        } else if (toCol == col && toRow != row) {
            if (toRow > row) {
                for (int i = row + 1; i < toRow; i++) {
                    CellPosition pos = new CellPosition(i, col);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = row - 1; i > toRow; i--) {
                    CellPosition pos = new CellPosition(i, col);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            }
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        }
        return false;
    }

    @Override
    public void movePiece(ChessBoard board, CellPosition from, CellPosition to) {
        this.hasMoved = true;
        super.movePiece(board, from, to);
    }

}
