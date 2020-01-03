package Game;

import Game.Pieces.*;

import java.util.Scanner;

public class ChessBoard {

    Tile[][]  tiles;
    private boolean whitesTurn = true;

    public Tile getTile(int y, int x){
        return tiles[y][x];
    }

    public void startGame(){
        Scanner sc = new Scanner(System.in);
        while (true){
            printBoard();
            System.out.println("Enter starting x, starting y, ending x, ending y, all starting from 0");

            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            if (x1<0||x1>7||x2<0||x2>7||y1<0||y1>7||y2<0||y2>7){
                System.out.println("Invalid input. Try again.");
                continue;
            }
            if (!tiles[y1][x1].isOccupied()){
                System.out.println("No piece on that tile.");
                continue;
            }
            if (whitesTurn!=tiles[y1][x1].getPiece().isWhite()){
                System.out.println("Illegal turn order. Try again.");
                continue;
            }

            boolean threatensKing=tiles[y2][x2].isOccupied()&&tiles[y2][x2].getPiece() instanceof King;
            if (tiles[y1][x1].getPiece().attemptMove(this,tiles[y1][x1],tiles[y2][x2])){
                if (threatensKing){
                    System.out.println("The game has ended.");
                    System.exit(0);
                }
                whitesTurn=!whitesTurn;
            }
            else{
                System.out.println("Illegal move.");
            }
        }
    }
    public void printBoard(){
        for (int i=0; i<tiles.length; i++){
            for (int j=0; j<tiles.length; j++){
                if (!tiles[i][j].isOccupied()){
                    System.out.print((char)27 + "[37m" );
                    System.out.print(" X");
                    continue;
                }
                if (tiles[i][j].getPiece().isWhite())
                    System.out.print((char)27 + "[31m" );
                else
                    System.out.print((char)27 + "[34m" );
                System.out.print(" "+tiles[i][j].getPiece().getToken());
            }
            System.out.println();
        }
        System.out.print((char)27 + "[37m" );
    }



    /*Returns false if a crossing is encountered, true if legal*/
    public boolean horiVertiMovement(int x1, int x2, int y1, int y2){
        int startTile = x1;
        int endTile = x2;
        boolean xAxis = true; //we move along the x axis
        if (x1 == x2) {
            startTile = y1;
            endTile = y2;
            xAxis = false; // we move along the y axis
        }
        int lower = Math.min(startTile,endTile);
        int higher = Math.max(startTile,endTile);
        for (int i = lower + 1; i < higher; i += 1){
            /*For each tile between starting and ending, check if the tile is occupied.*/
            if (xAxis && getTile(y1,i).isOccupied()){
                return false;
            }
            if ((!xAxis) && getTile(i,x1).isOccupied()){
                return false;
            }
        }
        return true;
    }
    /*Returns false if a crossing is encountered, true if legal*/
    public boolean diagonalMovement(int x1, int x2, int y1, int y2) {
        int xMovement = 1; // move right
        if (x1 > x2)
            xMovement = -1; //move left
        int yMovement = 1; // move up
        if (y1 > y2)
            yMovement = -1; // move down
        int spacesMoved = Math.abs(x1 - x2);
        for (int i = 1; i < spacesMoved; i++) {
            /*For each tile between starting and ending, check if the tile is occupied.*/
            if (getTile(y1 + yMovement * i, x1 +xMovement * i).isOccupied()) {
                return false;
            }
        }
        return true;
    }



    public ChessBoard(){
        tiles = new Tile[8][8];
        for (int i=0; i<8; i++){
            tiles[1][i]=new Tile(1,i,new Pawn(false));
            tiles[6][i]=new Tile(6,i,new Pawn(true));
            if (i==0||i==7){
                tiles[0][i]=new Tile(0,i,new Rook(false));
                tiles[7][i]=new Tile(7,i,new Rook(true));
            }
            else if (i==1||i==6){
                tiles[0][i]=new Tile(0,i,new Bishop(false));
                tiles[7][i]=new Tile(7,i,new Bishop(true));
            }
            else if (i==2||i==5){
                tiles[0][i]=new Tile(0,i,new Knight(false));
                tiles[7][i]=new Tile(7,i,new Knight(true));
            }
            else if (i==3){
                tiles[0][i]=new Tile(0,i,new Queen(false));
                tiles[7][i]=new Tile(7,i,new Queen(true));
            }
            else if (i==4){
                tiles[0][i]=new Tile(0,i,new King(false));
                tiles[7][i]=new Tile(7,i,new King(true));
            }
        }
        for (int i=2; i<6; i++){
            for (int j=0; j<8; j++) {
                tiles[i][j] = new Tile(i,j,null);
            }
        }

    }



}
