package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.piece.Piece;

public interface ControllableChessModel {
    /**
     * Gets the current state of the game.
     * 
     * @return the current game state
     */
    GameState getGameState();

    /**
     * Sets the current state of the game.
     * 
     * @param state the new game state
     */
    void setGameState(GameState state);

    /**
     * Gets the piece at the given cell.
     * 
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the piece at the given cell
     */
    Piece getCell(int row, int col);
}
