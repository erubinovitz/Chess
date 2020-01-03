package Game;

public class Tile {
    private Piece piece;
    private int x;  private int y;

    public Tile(int y, int x){
        this.x=x;
        this.y=y;
    }
    public Tile (int y, int x, Piece piece){
        this.x=x;
        this.y=y;
        this.piece=piece;
    }

    public Piece getPiece() {
        return piece;
    }
    public boolean isOccupied(){
        return piece!=null;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void removePiece(){
        this.piece=null;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
