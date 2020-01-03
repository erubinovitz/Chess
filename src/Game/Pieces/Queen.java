package Game.Pieces;

import Game.ChessBoard;
import Game.Piece;
import Game.Tile;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite,'Q');
    }



    @Override
    protected boolean attemptMove(ChessBoard board, Tile start, Tile end) {
        if (checkSelfCapture(board,start,end))return false;

        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (x1 == x2 ^ y1 == y2){ //horizontal or vertical movement
            if (!board.horiVertiMovement(x1,x2,y1,y2)) return false;
            end.setPiece(start.getPiece());
            start.setPiece(null);
            hasMoved = true;
            return true;
        }
        int xDiff = Math.abs(x1-x2);
        int yDiff = Math.abs(y1-y2);
        if (xDiff == yDiff){
            if (xDiff == 0) return false;
            if (!board.diagonalMovement(x1,x2,y1,y2)) return false;
            end.setPiece(start.getPiece());
            start.setPiece(null);
            hasMoved = true;
            return true;
        }
        return false;
    }
}
