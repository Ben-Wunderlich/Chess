package com.company;

public class Piece {
    private String name;
    private boolean side; // note that white is true and black is false
    private int[] rowFactors;
    private int[] colFactors;

    public void destroyPiece(){
        System.out.println("piece has been destroyed");
        this.name = "empty";//dont know how this will work
    }

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

        for(int i = 0;i < rowFactor.length-1;i++){//could use either as max
            int curRowFact = rowFactor[i];
            int curColFact = colFactor[i];
        }
    }

    public void checkDirection(Piece[][] board, Coordinate location, int rowFactor, int colFactor){
        boolean hitEnd = false;
        int curRow = location.getRow();
        int curCol = location.getCol();

        int maxRow = board.length-1;
        int maxCol = board[0].length-1;

        while(!hitEnd){
            curRow += rowFactor;
            curCol += colFactor;

            if(!Piece.isValidCoord(curRow, curCol, board)){
                continue;
            }

            if(board[curRow][curCol].getName().equals("")){
                //make it blue
            }
            else if(board[curRow][curCol].side != this.side){
                //make it red
                hitEnd = true;
            }
        }

    }
    public static boolean isValidCoord(int row, int col, Piece[][] board){

        if(row < 0 || col < 0){
            return false;
        }
        if (row > board.length-1 || col > board[0].length-1){
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

    public static Piece[][] movePiece(Piece[][] board, Coordinate start, Coordinate end){
        Piece[][] modBoard = board;
        //board[start.getRow()][start.getRow].colour is a default colour
        //call method to return all colours to default
        //return board

        Piece saveEnd = board[end.getRow()][end.getCol()];

        modBoard[end.getRow()][end.getCol()] = board[start.getRow()][start.getCol()];
        //if it is blue
        modBoard[start.getRow()][start.getCol()] = saveEnd;
        //else
        //modBoard[start.getRow()][start.getCol()] = new Empty();

        //make all squares not blue or red and switch sides
        return modBoard;
    }

}
