package com.company;

public class King extends Piece{
    public King(boolean side) {
        super(side);
        this.setName("king");
    }

    public void CheckValidSquares(Piece[][] board, Coordinate location){
        int column = location.getCol();
        int rows = location.getRow();

        boolean theSide;
        String targetName;

        for(int col = column-1; col <= column+1; ++col){
            for(int row = rows-1; row <= rows+1; ++row){
                if(!Piece.isValidCoord(row, col)){
                    continue;
                }

                targetName = board[row][col].getName();

                if(!safeToMove(row, col, getSide())){
                    continue;
                }

                if (targetName.equals("empty")) {
                    Main.addTarget(row, col, 0, 0);
                    continue;
                }
                theSide = board[row][col].getSide();
                if (theSide != this.getSide()){
                    Main.addTarget(row, col, 1, 0);
                }
            }
        }
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
