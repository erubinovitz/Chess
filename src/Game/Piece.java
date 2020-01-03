package Game;

abstract public class Piece {
    protected boolean hasMoved=false;
    private boolean isWhite;
    protected char token='L';
    protected abstract boolean attemptMove(ChessBoard board, Tile start, Tile end);
    protected boolean checkSelfCapture(ChessBoard board, Tile start, Tile end){
        if (!end.isOccupied()) return false;
        return (start.getPiece().isWhite==end.getPiece().isWhite);
    }
    public boolean isWhite(){
        return isWhite;
    }
    public Piece(boolean isWhite, char token){
        this.isWhite=isWhite;
        this.token=token;
    }
    public boolean hasMoved(){
        return hasMoved;
    }
    public char getToken(){
        return token;
    }
}
