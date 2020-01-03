package Game.Pieces;

import Game.ChessBoard;
import Game.Piece;
import Game.Tile;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite,'P');
    }



    @Override
    protected boolean attemptMove(ChessBoard board, Tile start, Tile end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();
        int forward = -1;
        if (!start.getPiece().isWhite()){
            forward = 1;
        }
        if ((!end.isOccupied()) && (y1 + forward == y2) && (x1 == x2)){
            end.setPiece(start.getPiece());
            start.setPiece(null);
            hasMoved=true;
            return true;
        }
        if ((!end.isOccupied()) && y1 + 2*forward == y2 && x1 == x2 && board.horiVertiMovement(x1,x2,y1,y2) &&!hasMoved){
            end.setPiece(start.getPiece());
            start.setPiece(null);
            hasMoved=true;
            return true;
        }
        if (Math.abs(x1-x2)==1 && y1 + 1*forward == y2 && end.isOccupied() && end.getPiece().isWhite() != start.getPiece().isWhite()){
            if ((forward == 1 && x2 == 7) || (forward == -1 && x2==0) ){
                end.setPiece(new Queen(start.getPiece().isWhite())); // pawn becomes queen upon reaching end
            }
            else
                end.setPiece(start.getPiece());
            start.setPiece(null);
            hasMoved=true;
            return true;
        }
        return false;
    }
}
