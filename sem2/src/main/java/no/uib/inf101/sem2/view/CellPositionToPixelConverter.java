package no.uib.inf101.sem2.view;

import java.awt.geom.Rectangle2D;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.grid.CellPosition;

public class CellPositionToPixelConverter {

  private Rectangle2D box;
  private GridDimension gd;
  private double margin;

  /**
   * Create a new converter.
   * 
   * @param box the box to draw the grid in
   * @param gd the grid dimension
   * @param margin the margin between cells
   */
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }

  /**
   * Get the bounds of a cell in the grid.
   * 
   * @param cp the cell position
   * @return a rectangle representing the bounds of the cell
   */
  public Rectangle2D getBoundsForCell(CellPosition cp) {
    int rows = gd.rows();
    int cols = gd.cols();
    double x = box.getX();
    double y = box.getY();
    double width = box.getWidth();
    double height = box.getHeight();
    double margin = this.margin;

    double cellWidth = (width - margin * (cols + 1)) / cols;
    double cellHeight = (height - margin * (rows + 1)) / rows;
    double cellX = x + margin + (cellWidth + margin) * cp.col();
    double cellY = y + margin + (cellHeight + margin) * cp.row();

    Rectangle2D rect = new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);

    return rect;
  }
}
