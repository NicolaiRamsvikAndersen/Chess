package no.uib.inf101.sem2.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.piece.Piece;

public interface ViewableChessModel {

    /**
     * Gets the chess board.
     * 
     * @return the chess board
     */
    ChessBoard getBoard();

    /**
     * Gets the dimension of the grid.
     * 
     * @return the dimension of the grid as a GridDimension object
     */
    GridDimension getDimension();

    /**
     * Gives all the cells in the grid
     * 
     * @return an Iterable of GridCell objects in the grid
     */
    Iterable<GridCell<Piece>> getCells();

    /**
     * Gets the current state of the game.
     * 
     * @return the current game state
     */
    GameState getGameState();

    /**
     * Sets the current state of the game.
     * 
     * @param state
     */
    void setGameState(GameState state);
}
