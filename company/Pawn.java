package com.company;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(boolean side) {
        super(side);
        this.setName("pawn");
    }
    public void checkValidSquares(Piece[][] board, Coordinate location){
        int col = location.getCol();
        int row = location.getRow();

        int side_factor = 1;
        if(!this.getSide()){
            side_factor = -1;
        }

        int targetRow = row + side_factor;

        for(int i = col + 1; i < col - 2; --i) {
            if (i < 0 || i > board[0].length-1){//if that square is not on the grid
                continue;
            }
            if (board[targetRow][i].getName().equals("empty")){//if a square is empty
                if(i == col){//if that square is directly in front of it
                    //make that square a blue background

                    if(!hasMoved && board[targetRow + side_factor][i].getName().equals("empty")){
                        //if hasnt move and one square up is enpty
                        //make that square blue too//
                    }
                }
                continue;
            }
            else if (i!= col && (board[targetRow][i].getSide() != this.getSide())) {//if it is opposing piece
                //make that square red background//
            }
        }
    }

    public void movedPawn(){
        this.hasMoved = true;
    }
}
