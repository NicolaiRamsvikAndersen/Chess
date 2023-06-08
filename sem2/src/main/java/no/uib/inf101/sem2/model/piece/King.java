package no.uib.inf101.sem2.model.piece;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;

public class King extends Piece {

    private boolean hasMoved;

    public King(boolean isWhite) {
        super(isWhite);
    }

    /**
     * Checks if the king has moved or not
     * 
     * @return true if the king has moved, false if not
     */
    public boolean hasMoved() {
        return this.hasMoved;
    }

    /**
     * Sets the hasMoved variable to true or false
     * 
     * @param hasMoved 
     *           true if the king has moved, false if not
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

        if (Math.abs(toRow - row) == 1 && Math.abs(toCol - col) == 1) {
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        } else if (Math.abs(toRow - row) == 1 && Math.abs(toCol - col) == 0) {
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        } else if (Math.abs(toRow - row) == 0 && Math.abs(toCol - col) == 1) {
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        }
        if (canCastle(board, from, to)) {
            return true;
        }

        return false;
    }

    private boolean canCastle(ChessBoard board, CellPosition from, CellPosition to) {
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
            Rook rook = (Rook) board.get(to);
            if (this.hasMoved || rook.hasMoved() || board.get(to).isWhite() != this.isWhite()) {
                return false;
            }
            this.hasMoved = true;
            rook.setMoved(true);
            board.set(from, null);
            if (to.col() == 7) {
                board.set(new CellPosition(row, 7), null);
                board.set(new CellPosition(row, 6), this);
                board.set(new CellPosition(row, 5), rook);
            } else {
                board.set(new CellPosition(row, 0), null);
                board.set(new CellPosition(row, 2), this);
                board.set(new CellPosition(row, 3), rook);
            }
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
