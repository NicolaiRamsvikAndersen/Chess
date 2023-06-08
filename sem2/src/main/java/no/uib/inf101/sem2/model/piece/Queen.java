package no.uib.inf101.sem2.model.piece;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isLegalMove(ChessBoard board, CellPosition from, CellPosition to) {
        int row = from.row();
        int col = from.col();
        int toRow = to.row();
        int toCol = to.col();

        if (Math.abs(toRow - row) == Math.abs(toCol - col)) {
            if (toRow > row && toCol > col) {
                for (int i = 1; i < Math.abs(toRow - row); i++) {
                    CellPosition pos = new CellPosition(row + i, col + i);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            } else if (toRow > row && toCol < col) {
                for (int i = 1; i < Math.abs(toRow - row); i++) {
                    CellPosition pos = new CellPosition(row + i, col - i);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            } else if (toRow < row && toCol > col) {
                for (int i = 1; i < Math.abs(toRow - row); i++) {
                    CellPosition pos = new CellPosition(row - i, col + i);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            } else if (toRow < row && toCol < col) {
                for (int i = 1; i < Math.abs(toRow - row); i++) {
                    CellPosition pos = new CellPosition(row - i, col - i);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            }
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        } else if (toRow == row) {
            if (toCol > col) {
                for (int i = 1; i < Math.abs(toCol - col); i++) {
                    CellPosition pos = new CellPosition(row, col + i);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            } else if (toCol < col) {
                for (int i = 1; i < Math.abs(toCol - col); i++) {
                    CellPosition pos = new CellPosition(row, col - i);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            }
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        } else if (toCol == col) {
            if (toRow > row) {
                for (int i = 1; i < Math.abs(toRow - row); i++) {
                    CellPosition pos = new CellPosition(row + i, col);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            } else if (toRow < row) {
                for (int i = 1; i < Math.abs(toRow - row); i++) {
                    CellPosition pos = new CellPosition(row - i, col);
                    if (board.get(pos) != null) {
                        return false;
                    }
                }
            }
            if (board.get(to) != null && board.get(to).isWhite() == this.isWhite()) {
                return false;
            }
            movePiece(board, from, to);
            return true;
        }
        return false;
    }
}
