package no.uib.inf101.sem2.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sem2.controller.ControllableChessModel;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.view.ViewableChessModel;

public class ChessModel implements ViewableChessModel, ControllableChessModel {

    private ChessBoard board;
    private GameState state;

    /**
     * Create a new ChessModel.
     * 
     * @param board the board to use
     */
    public ChessModel(ChessBoard board) {
        this.board = board;
        this.state = GameState.START;
    }

    public ChessBoard getBoard() {
        return board;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell<Piece>> getCells() {
        return board;
    }

    @Override
    public GameState getGameState() {
        return this.state;
    }

    @Override
    public void setGameState(GameState state) {
        this.state = state;
    }

    @Override
    public Piece getCell(int row, int col) {
        CellPosition pos = new CellPosition(row, col);
        return board.get(pos);
    }
}
