package com.company;

public class King extends Piece{
    public King(boolean side) {
        super(side);
        this.setName("king");
    }

    public void CheckValidSquares(Coordinate location){
        Piece[][] board = Main.board;
        int column = location.getCol();
        int rows = location.getRow();

        Main.gui.resetColours();
        boolean theSide;
        String targetName;

        Coordinate[] checkedSpots = new Coordinate[8];
        int curspot = 0;
        boolean isDryRun = Main.getIsDryRun();

        for(int col = column-1; col <= column+1; ++col){
            for(int row = rows-1; row <= rows+1; ++row){
                if(!Piece.isValidCoord(row, col)){
                    continue;
                }

                targetName = board[row][col].getName();
                if (targetName.equals("empty")){
                    if(!isDryRun) {
                        Main.gui.setBlue(row, col);
                        continue;
                    }
                    else{
                        checkedSpots[curspot] = new Coordinate(row, col);
                        curspot++;
                    }
                }

                theSide = board[row][col].getSide();
                if (theSide != this.getSide()){
                    if(!isDryRun) {
                        Main.gui.setRed(row, col);
                    }
                    else{
                        checkedSpots[curspot] = new Coordinate(row, col);
                        curspot++;
                    }
                }
            }
        }
        if(isDryRun) {BoolGrids.setCoords(checkedSpots); }
    }
}
