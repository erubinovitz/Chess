package Game.Pieces;

import Game.ChessBoard;
import Game.Piece;
import Game.Tile;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite,'R');
    }

    @Override
    protected boolean attemptMove(ChessBoard board, Tile start, Tile end) {
        if (checkSelfCapture(board,start,end))return false;

        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (!(x1 == x2 ^ y1 == y2)){
            return false; //if both are equal, then piece didnt move at all. if neither are equal, it wasn't a horizontal or vertical movement.
        }
        if (!board.horiVertiMovement(x1,x2,y1,y2)) return false; // tried to move through other units
        end.setPiece(start.getPiece());
        start.setPiece(null);
        hasMoved=true;
        return true; // move is legal

    }
}
