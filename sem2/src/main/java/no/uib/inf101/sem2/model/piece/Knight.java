package no.uib.inf101.sem2.model.piece;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isLegalMove(ChessBoard board, CellPosition from, CellPosition to) {
        int row = from.row();
        int col = from.col();
        int toRow = to.row();
        int toCol = to.col();

        if (Math.abs(toRow - row) == 2 && Math.abs(toCol - col) == 1) {
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        } else if (Math.abs(toRow - row) == 1 && Math.abs(toCol - col) == 2) {
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        }
        return false;
    }
    
}
