package com.company;

public class Knight extends Piece {
    public Knight(boolean side) {
        super(side);
        this.setName("knight");
    }

    public void CheckValidSquares(Coordinate location){
        int curRow = location.getRow();
        int curCol = location.getCol();

        int[] rowMods = { 1,2, 2, 1,-1,-2, -2, -1};
        int[] colMods = {-2, -1, 1, 2, 2, 1, -1, -2 };

        boolean isDryRun = Main.getIsDryRun();
        Coordinate[] checkedSpots = new Coordinate[8];
        int curSpot = 0;

        Main.gui.resetColours();
        Piece[][] board = Main.board;
        for(int i = 0; i < colMods.length; ++i){
            int tempRow = curRow + rowMods[i];
            int tempCol = curCol + colMods[i];

            if(!Piece.isValidCoord(tempRow, tempCol)){
                continue;
            }

            if(board[tempRow][tempCol].getName().equals("empty")){
                if(!isDryRun) {
                    Main.gui.setBlue(tempRow, tempCol);
                }
                else{checkedSpots[curSpot] = new Coordinate(tempRow, tempCol);
                    curSpot++;}
            }
            else if(board[tempRow][tempCol].getSide() != this.getSide()){
                if(!isDryRun) {
                    Main.gui.setRed(tempRow, tempCol);
                }
                else{checkedSpots[curSpot] = new Coordinate(tempRow, tempCol);
                    curSpot++;}
            }
        }
        if(isDryRun) {BoolGrids.setCoords(checkedSpots); }
    }
            }
