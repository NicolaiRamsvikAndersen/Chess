package no.uib.inf101.sem2.model.piece;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;

public class Pawn extends Piece {

    private boolean hasMoved;
    private boolean canPromote;

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isLegalMove(ChessBoard board, CellPosition from, CellPosition to) {
        int row = from.row();
        int col = from.col();
        int toRow = to.row();
        int toCol = to.col();

        if (isWhite()) {
            if (toRow == row - 1 && toCol == col && board.get(to) == null) {
                movePiece(board, from, to);
                return true;
            } else if (toRow == row - 2 && toCol == col && !hasMoved) {
                movePiece(board, from, to);
                return true;
            } else if (toRow == row - 1 && (toCol == col + 1 || toCol == col - 1) && board.get(to) != null && board.get(to).isWhite() == false) {
                movePiece(board, from, to);
                return true;
            }
        } else {
            if (toRow == row + 1 && toCol == col && board.get(to) == null) {
                movePiece(board, from, to);
                return true;
            } else if (toRow == row + 2 && toCol == col && !hasMoved) {
                movePiece(board, from, to);
                return true;
            } else if (toRow == row + 1 && (toCol == col + 1 || toCol == col - 1) && board.get(to) != null && board.get(to).isWhite() == true) {
                movePiece(board, from, to);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void movePiece(ChessBoard board, CellPosition from, CellPosition to) {
        this.hasMoved = true;
        super.movePiece(board, from, to);
        if (to.row() == 0 && isWhite()) {
            canPromote = true;
            //board.set(to, new Queen(isWhite()));
        }
        if (to.row() == 7 && !isWhite()) {
            canPromote = true;
            //board.set(to, new Queen(isWhite()));
        }
    }

    /**
     * Checks if the pawn can promote or not
     * 
     * @return true if the pawn can promote, false if not
     */
    public boolean canPromote() {
        return canPromote;
    }
}
