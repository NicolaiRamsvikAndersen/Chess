package no.uib.inf101.sem2.view;

import java.awt.Color;

public interface ColorTheme {

    /**
    * Get the color of a cell.
    * 
    * @param c
    *            The character representing the cell
    * @return The color of the cell
    */
    Color getCellColor(char c);

    /**
    * Gets the color of the frame. 
    * 
    * @return The color of the frame
    */
    Color getFrameColor();

    /**
    * Gets the color of the background.
    * 
    * @return The color of the background
    */
    Color getBackgroundColor();

    /**
    * Gets the color of the text. 
    * 
    * @return The color of the text
    */
    Color getTextColor();
}
