package no.uib.inf101.sem2.model.piece;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;

public abstract class Piece {
    private boolean isClicked;
    private boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public void setClicked() {
        this.isClicked = true;
    }

    public void setUnclicked() {
        this.isClicked = false;
    }

    public boolean isClicked() {
        return this.isClicked;
    }

    public abstract boolean isLegalMove(ChessBoard board, CellPosition from, CellPosition to);

    public void movePiece(ChessBoard board, CellPosition from, CellPosition to) {
        board.set(to, this);
        board.set(from, null);	
    }
}
