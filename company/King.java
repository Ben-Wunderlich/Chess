package com.company;

public class King extends Piece{
    public King(boolean side) {
        super(side);
        this.setName("king");
    }

    public void checkValidSquares(Piece[][] board, Coordinate location){
        int col = location.getCol();
        int rows = location.getRow();

        for(int column = col-1; column <= col+1; ++column){
            for(int row = rows-1; row <= rows+1; ++row){

                if(!Piece.isValidCoord(row, col, board)){
                    continue;
                }

                String targetName = board[row][column].getName();

                if (targetName.equals("empty")){
                    //board[row][column]. make it blue
                }

                boolean theSide = board[row][column].getSide();
                if (theSide != this.getSide()){
                    //make red
                }
            }
        }
    }
}
