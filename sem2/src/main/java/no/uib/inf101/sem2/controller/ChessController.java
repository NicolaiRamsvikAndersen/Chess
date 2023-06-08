package no.uib.inf101.sem2.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.piece.Bishop;
import no.uib.inf101.sem2.model.piece.King;
import no.uib.inf101.sem2.model.piece.Knight;
import no.uib.inf101.sem2.model.piece.Pawn;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Queen;
import no.uib.inf101.sem2.model.piece.Rook;
import no.uib.inf101.sem2.view.ChessView;
import no.uib.inf101.sem2.view.ViewableChessModel;

public class ChessController implements MouseListener {

    private ControllableChessModel model;
    private ChessView view;
    private int deltaRow = 0;
    private int deltaCol = 0;

    public ChessController(ControllableChessModel model, ChessView view) {
        this.model = model;
        this.view = view;
        view.setFocusable(true);
        view.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // System.out.println("Mouse clicked at " + arg0.getX() + ", " + arg0.getY());
        if (model.getGameState() == GameState.START) {
            model.setGameState(GameState.WHITE);
            view.repaint();
        }
        if (model.getGameState() == GameState.WHITE_PROMOTION) {
            CellPosition pos = getCellPosition(arg0);
            promote(true, pos);
        }
        if (model.getGameState() == GameState.BLACK_PROMOTION) {
            CellPosition pos = getCellPosition(arg0);
            promote(false, pos);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // System.out.println("Mouse exited");
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        CellPosition pos = getCellPosition(arg0);
        int row = pos.row();
        int col = pos.col();
        this.deltaRow = row;
        this.deltaCol = col;
        if (col != -1) {
            Piece cell = this.model.getCell(row, col);
            if (cell != null) {
                if (cell.isWhite() && this.model.getGameState() == GameState.WHITE)
                    cell.setClicked();
                else if (!cell.isWhite() && this.model.getGameState() == GameState.BLACK)
                    cell.setClicked();
            }

        }
        this.view.repaint();
        // System.out.println("Mouse pressed at " + row + ", " + col);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        if (deltaCol != -1) {
            CellPosition pos = getCellPosition(arg0);
            int row = pos.row();
            int col = pos.col();
            this.deltaRow -= row;
            this.deltaCol -= col;
            Piece cell = this.model.getCell(deltaRow + row, deltaCol + col);
            ChessBoard board = (ChessBoard) this.view.getModel().getCells();
            CellPosition oldPos = new CellPosition(deltaRow + row, deltaCol + col);
            if (cell != null) {
                if (cell.isWhite() && this.model.getGameState() == GameState.WHITE) {
                    if (cell.isLegalMove(board, oldPos, pos)) {
                        if (cell instanceof Pawn) {
                            Pawn pawn = (Pawn) cell;
                            if (pawn.canPromote())
                                this.model.setGameState(GameState.WHITE_PROMOTION);
                            else
                                this.model.setGameState(GameState.BLACK);
                        } else
                            this.model.setGameState(GameState.BLACK);
                    }
                } else if (!cell.isWhite() && this.model.getGameState() == GameState.BLACK) {
                    if (cell.isLegalMove(board, oldPos, pos)) {
                        if (cell instanceof Pawn) {
                            Pawn pawn = (Pawn) cell;
                            if (pawn.canPromote())
                                this.model.setGameState(GameState.BLACK_PROMOTION);
                            else
                                this.model.setGameState(GameState.WHITE);
                        } else
                            this.model.setGameState(GameState.WHITE);
                    }
                }
                checkWin();
                cell.setUnclicked();
            }
        }
        this.view.repaint();
        /*
         * System.out.println("Mouse released at " + row + ", " + col);
         * System.out.println("Delta row: " + this.deltaRow + ", delta col: " +
         * this.deltaCol);
         */
    }

    private CellPosition getCellPosition(MouseEvent arg0) {
        if (this.model.getGameState() == GameState.WHITE || this.model.getGameState() == GameState.BLACK) {
            ViewableChessModel viewModel = this.view.getModel();

            int width = this.view.getWidth();
            int height = this.view.getHeight();

            int cols = viewModel.getDimension().cols();
            int cellWidth = width / cols;
            int col = arg0.getX() / cellWidth;

            int rows = viewModel.getDimension().rows();
            int cellHeight = height / rows;
            int row = arg0.getY() / cellHeight;

            return new CellPosition(row, col);

        } else if (this.model.getGameState() == GameState.WHITE_PROMOTION
                || this.model.getGameState() == GameState.BLACK_PROMOTION) {

            int width = this.view.getWidth();
            int height = this.view.getHeight();

            int cols = 4;
            int cellWidth = (width / 2) / cols;
            int col = -1;
            

            int row = -1;
            if (arg0.getY() > (height / 2) - (cellWidth / 2) && arg0.getY() < (height / 2) + (cellWidth / 2)) {
                if (arg0.getX() > width / 4 && arg0.getX() < width - (width / 4)) {
                    col = (arg0.getX() - (width / 4)) / cellWidth;
                    row = 0;
                }
            }
            return new CellPosition(row, col);
        } else
            return new CellPosition(0, 0);
    }

    private void checkWin() {
        Iterable<GridCell<Piece>> cells = view.getModel().getCells();
        boolean whiteKing = false;
        boolean blackKing = false;
        for (GridCell<Piece> cell : cells) {
            Piece piece = cell.value();
            if (piece != null) {
                if (piece instanceof King) {
                    if (piece.isWhite()) {
                        whiteKing = true;
                    } else {
                        blackKing = true;
                    }
                }
            }
        }
        if (!whiteKing) {
            this.model.setGameState(GameState.BLACK_WON);
        } else if (!blackKing) {
            this.model.setGameState(GameState.WHITE_WON);
        }

    }

    private void promote(Boolean isWhite, CellPosition pos) {
        int col = pos.col();
        for (GridCell<Piece> cell : this.view.getModel().getCells()) {
        if (cell.value() == null)
            continue;
        Piece piece = cell.value();
        if (piece instanceof Pawn) {
            Pawn pawn = (Pawn) piece;
            if (pawn.isWhite() == isWhite && pawn.canPromote()) {
                if (col == 0)
                    this.view.getModel().getBoard().set(cell.pos(), new Rook(isWhite));
                if (col == 1)
                    this.view.getModel().getBoard().set(cell.pos(), new Knight(isWhite));
                if (col == 2)
                    this.view.getModel().getBoard().set(cell.pos(), new Bishop(isWhite));
                if (col == 3)
                    this.view.getModel().getBoard().set(cell.pos(), new Queen(isWhite));
                if (col != -1 && pos.row() != -1)
                    if (isWhite)
                        this.model.setGameState(GameState.BLACK);
                    else
                        this.model.setGameState(GameState.WHITE);
                view.repaint();
                break;
            }
        }
    }

    }
}
