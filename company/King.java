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

        if(!safeToMove(rows, column, getSide())){
            boolean kingChecked = true;//impliment this
        }

        Coordinate[] checkedSpots = new Coordinate[20];
        int curspot = 0;
        boolean isDryRun = Main.getIsDryRun();

        for(int col = column-1; col <= column+1; ++col){
            for(int row = rows-1; row <= rows+1; ++row){
                if(!Piece.isValidCoord(row, col)){
                    continue;
                }

                targetName = board[row][col].getName();

                if (targetName.equals("empty")){
                    if(!isDryRun && safeToMove(row, col, getSide())) {
                        Main.gui.setBlue(row, col);
                        continue;
                    }
                    else if(isDryRun){
                        checkedSpots[curspot] = new Coordinate(row, col);
                        curspot++;
                    }
                }

                theSide = board[row][col].getSide();
                if (theSide != this.getSide()){
                    if(!isDryRun  && safeToMove(row, col, getSide())) {
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

    private boolean safeToMove(int row, int col, boolean side){
        if(Main.getRound() < 3){
            return true;
        }
        boolean[][] enemyBoard = Main.getBoolGrid(!side);
        if(enemyBoard[row][col]){//if space is checked
            return false;
        }
        else{
            return true;
        }
    }
}
