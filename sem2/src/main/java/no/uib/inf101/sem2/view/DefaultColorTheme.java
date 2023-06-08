package no.uib.inf101.sem2.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(char c) {
        Color color = switch (c) {
            case '-' -> Color.GRAY;
            case '+' -> Color.LIGHT_GRAY;
            case 'c' -> Color.GREEN;
            default -> throw new IllegalArgumentException(
                    "No available color for '" + c + "'");
        };
        return color;
    }

    @Override
    public Color getFrameColor() {
        return Color.DARK_GRAY;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getTextColor() {
        return Color.WHITE;
    }

}
