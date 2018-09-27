package com.company;

public class Pawn extends Piece {

    public Pawn(boolean side) {
        super(side);
        this.setName("pawn");
    }

    public void CheckValidSquares(Coordinate location){
        Piece[][] board = Main.board;
        int column = location.getCol();
        int row = location.getRow();

        int side_factor = -1;
        if(!this.getSide()){
            side_factor = 1;
        }

        boolean isDryRun = Main.getIsDryRun();

        Coordinate[] checkedSpots = new Coordinate[2];
        int curSpot = 0;

        int targetRow = row + side_factor;

        Main.gui.resetColours();
        for(int col = column + 1; col > column - 2; --col) {
            if (!Piece.isValidCoord(targetRow, col)){
                continue;
            }
            if (board[targetRow][col].getName().equals("empty")){//if a square is empty
                if(col == column){//if that square is directly in front of it
                    if(!isDryRun) {
                        Main.gui.setBlue(targetRow, col);
                    }

                    if(!this.hasMoved() && board[targetRow + side_factor][col].getName().equals("empty")){
                        //if hasnt move and one square up is enpty
                        if(!isDryRun){
                            Main.gui.setBlue(targetRow+side_factor, col);
                        }
                    }
                }
                else if(isDryRun && col != column){
                    checkedSpots[curSpot] = new Coordinate(targetRow, col);
                    curSpot++;
                }
            }
            else if (col != column) {//if it is other column/diagonal
                if(isDryRun){checkedSpots[curSpot] = new Coordinate(targetRow, col);}
                curSpot++;

                if(board[targetRow][col].getSide() != this.getSide()){
                     if(!isDryRun) {
                         Main.gui.setRed(targetRow, col);
                     }
                 }
            }
        }
        if(isDryRun) {BoolGrids.setCoords(checkedSpots); }
    }


}

