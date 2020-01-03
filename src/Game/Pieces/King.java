package Game.Pieces;

import Game.ChessBoard;
import Game.Piece;
import Game.Tile;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite,'K');

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
        if (xDiff == 0 && yDiff == 0 )return false;
        if (xDiff <= 1 && yDiff <= 1){ // if both x and y moved a max of 1, and we moved somewhere
            end.setPiece(start.getPiece());
            start.setPiece(null);
            hasMoved=true;
            return true;
        }
        if ((xDiff == 2) && yDiff == 0){ //user is attempting to castle

            boolean moveRight = true;
            if (x2<x1) moveRight=false;
            int rookX=moveRight?7:0;
            int rookY=start.getY();
            if (hasMoved) return false;
            if (!board.horiVertiMovement(x1,rookX,y1,y2))
                return false;

            if (board.getTile(rookY,rookX).isOccupied() && !board.getTile(rookY,rookX).getPiece().hasMoved() && board.getTile(rookY,rookX).getPiece() instanceof Rook){
                int offset = 1;
                if (!moveRight) offset = -1;
                end.setPiece(start.getPiece());
                start.setPiece(null);
                board.getTile(y1,x1+offset).setPiece(board.getTile(rookY,rookX).getPiece());
                board.getTile(rookY,rookX).setPiece(null);
                return true;
            }
        }
        return false;
    }
}
