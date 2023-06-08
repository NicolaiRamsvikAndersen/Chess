package no.uib.inf101.grid;

/**
* A cell in a grid.
* 
* @param <E> The type of the cell's value
* @param CellPosition The type of the cell's position
*/
public record GridCell<E>(CellPosition pos, E value) {
}
