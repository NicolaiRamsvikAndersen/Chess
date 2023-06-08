package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.piece.Bishop;
import no.uib.inf101.sem2.model.piece.Knight;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Queen;
import no.uib.inf101.sem2.model.piece.Rook;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class ChessView extends JPanel {

    private ViewableChessModel model;
    private ColorTheme theme;

    /**
     * Create a new ChessView.
     * 
     * @param model
     *              The model to view
     * @param theme
     *              The color theme to use
     */
    public ChessView(ViewableChessModel model, DefaultColorTheme theme) {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.model = model;
        this.theme = theme;
        this.setBackground(theme.getBackgroundColor());
    }

    /**
     * Get the model this view is viewing.
     * 
     * @return the model
     */
    public ViewableChessModel getModel() {
        return model;
    }

    // The paintComponent method is called by the Java Swing framework every time
    // either the window opens or resizes, or we call .repaint() on this object.
    // Note: NEVER call paintComponent directly yourself
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (model.getGameState() == GameState.START)
            drawStart(g2, theme);
        else if (model.getGameState() == GameState.WHITE_WON) {
            drawWinner(g2, GameState.WHITE_WON);

        } else if (model.getGameState() == GameState.BLACK_WON) {
            drawWinner(g2, GameState.BLACK_WON);

        } else
            drawGame(g2);
    }

    // The drawGame method is called by paintComponent, and is responsible for
    // drawing the entire game.
    private void drawGame(Graphics2D g2) {
        // ansvar å tegne et fullstendig rutenett, inkludert alle rammer og ruter.
        int width = this.getWidth();
        int height = this.getHeight();
        final Color MARGINCOLOR = theme.getFrameColor();
        Rectangle2D boardBox = new Rectangle2D.Double(0, 0, width, height);
        g2.setColor(MARGINCOLOR);
        g2.fill(boardBox);
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(boardBox, model.getDimension(), 2);
        Rectangle2D promotionRect = new Rectangle2D.Double(boardBox.getX() + (boardBox.getWidth() / 4),
                (boardBox.getHeight() * (3.5 / 8)), boardBox.getWidth() / 2, (boardBox.getHeight() / 8));
        drawCells(g2, model.getCells(), converter, theme);
        if (model.getGameState() == GameState.WHITE_PROMOTION) {
            ChessBoard promotion = createPromotionBoard(true);
            g2.setColor(MARGINCOLOR);
            g2.fill(promotionRect);
            CellPositionToPixelConverter promotionConverter = new CellPositionToPixelConverter(promotionRect, promotion,
                    2);
            drawCells(g2, promotion, promotionConverter, theme);
        }
        if (model.getGameState() == GameState.BLACK_PROMOTION) {
            ChessBoard promotion = createPromotionBoard(false);
            g2.setColor(MARGINCOLOR);
            g2.fill(promotionRect);
            CellPositionToPixelConverter promotionConverter = new CellPositionToPixelConverter(promotionRect, promotion,
                    2);
            drawCells(g2, promotion, promotionConverter, theme);
        }

    }

    // The drawCells method is called by drawGame, and is responsible for drawing
    // a collection of cells.
    private static void drawCells(Graphics2D g2, Iterable<GridCell<Piece>> cells,
            CellPositionToPixelConverter converter, ColorTheme theme) {
        // ansvar å tegne en samling av ruter.
        // For hver rute regner denne metode ut hvor ruten skal være ved å kalle på
        // hjelpemetoden
        for (GridCell<Piece> cell : cells) {
            Rectangle2D rect = converter.getBoundsForCell(cell.pos());
            g2.setColor(theme.getCellColor('-'));
            if ((cell.pos().row() + cell.pos().col()) % 2 == 0) {
                g2.setColor(theme.getCellColor('+'));
            }
            g2.fill(rect);
            if (cell.value() != null) {
                Piece p = cell.value();
                String name = p.getClass().getSimpleName();
                Image img = getImage(name, p.isWhite());
                if (cell.value().isClicked()) {
                    g2.setColor(theme.getCellColor('c'));
                    g2.fill(rect);
                }
                Inf101Graphics.drawImage(g2, img, rect.getX(), rect.getY(), rect.getWidth() / 45);
            }
        }
    }

    private static Image getImage(String piece, boolean white) {
        if (white) {
            return Inf101Graphics.loadImageFromResources("/ChessSprites/w" + piece + ".png");
        }
        return Inf101Graphics.loadImageFromResources("/ChessSprites/b" + piece + ".png");
    }

    private void drawStart(Graphics2D g2, ColorTheme theme) {
        // ansvar å tegne startskjermen
        int width = this.getWidth();
        int height = this.getHeight();

        Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
        g2.setColor(theme.getFrameColor());
        g2.fill(rect);
        Rectangle2D topCenteredRect = new Rectangle2D.Double(0, 0, width, height / 5);
        g2.setColor(theme.getTextColor());
        Font font = new Font("Arial", Font.BOLD, 40);
        g2.setFont(font);
        Inf101Graphics.drawCenteredString(g2, "Chess", topCenteredRect);
        font = new Font("Arial", Font.PLAIN, 20);
        g2.setFont(font);
        Inf101Graphics.drawCenteredString(g2, "Click anywhere to start the game", rect);
    }

    private void drawWinner(Graphics2D g2, GameState winner) {
        // ansvar å tegne vinneren
        int width = this.getWidth();
        int height = this.getHeight();
        Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
        if (winner == GameState.WHITE_WON)
            g2.setColor(Color.WHITE);
        else
            g2.setColor(Color.BLACK);
        g2.fill(rect);
        Rectangle2D topCenteredRect = new Rectangle2D.Double(0, 0, width, height / 5);
        if (winner == GameState.WHITE_WON)
            g2.setColor(Color.BLACK);
        else
            g2.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 40);
        g2.setFont(font);
        Inf101Graphics.drawCenteredString(g2, "Game over", topCenteredRect);
        font = new Font("Arial", Font.PLAIN, 20);
        g2.setFont(font);
        String winString;
        if (winner == GameState.WHITE_WON)
            winString = "White";
        else
            winString = "Black";
        winString += " won the game";
        Inf101Graphics.drawCenteredString(g2, winString, rect);
    }

    private ChessBoard createPromotionBoard(Boolean isWhite) {
        ChessBoard promotion = new ChessBoard(1, 4, null);
        for (int i = 0; i < 4; i++) {
            CellPosition pos = new CellPosition(0, i);
            if (i == 0)
                promotion.set(pos, new Rook(isWhite));
            else if (i == 1)
                promotion.set(pos, new Knight(isWhite));
            else if (i == 2)
                promotion.set(pos, new Bishop(isWhite));
            else if (i == 3)
                promotion.set(pos, new Queen(isWhite));
        }
        return promotion;
    }
}