package com.company;

public class Piece {
    private String name;
    private boolean side; // note that white is true and black is false
    private int[] rowFactors;
    private int[] colFactors;
    private boolean hasMoved = false;

    public String getName(){
        return this.name;
    }

    public Piece(boolean side) {
        this.side = side;
    }

    public boolean getSide(){
        return this.side;
    }

    public void CheckValidSquares(Piece[][] board, Coordinate location){
        int[] rowFactor = this.rowFactors;
        int[] colFactor = this.colFactors;

        for(int i = 0;i < rowFactor.length;i++){//could use either as max
            int curRowFact = rowFactor[i];
            int curColFact = colFactor[i];
            checkDirection(board, location, curRowFact, curColFact);
        }
    }

    public void checkDirection(Piece[][] board, Coordinate location, int rowFactor, int colFactor){
        boolean hitEnd = false;
        int row = location.getRow();
        int col = location.getCol();

        while(!hitEnd){
            row += rowFactor;
            col += colFactor;

            if(!Piece.isValidCoord(row, col)){
                break;
            }

            if(board[row][col].getName().equals("empty")){
                Main.addTarget(row, col, 0, 0);
                continue;
            }
            if(board[row][col].side != this.side){
                Main.addTarget(row, col, 1, 0);
            }
            hitEnd = true;
        }
    }
    public static boolean isValidCoord(int row, int col){

        if(row < 0 || col < 0){
            return false;
        }
        if (row > 7 || col > 7){
            return false;
        }
        return true;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setFactors(int[] rowFactors, int[] colFactors){
        this.rowFactors = rowFactors;
        this.colFactors = colFactors;
    }

    public void pieceMoved(){
        this.hasMoved = true;
    }

    public boolean hasMoved(){
        return hasMoved;
    }

}
