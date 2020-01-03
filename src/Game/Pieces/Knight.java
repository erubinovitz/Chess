package Game.Pieces;

import Game.ChessBoard;
import Game.Piece;
import Game.Tile;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite,'N'); // K taken, use N
    }

    @Override
    protected boolean attemptMove(ChessBoard board, Tile start, Tile end) {
        if (checkSelfCapture(board,start,end))return false;
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();
        int xDiff = Math.abs(x1-x2);
        int yDiff = Math.abs(y1-y2);
        if (xDiff > 2 || yDiff > 2) return false;
        if (xDiff + yDiff != 3) return false;
        end.setPiece(start.getPiece());
        start.setPiece(null);
        hasMoved = true;
        return true;
    }
}
