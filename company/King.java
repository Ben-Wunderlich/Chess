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
        for(int col = column-1; col <= col+1; ++col){
            for(int row = rows-1; row <= rows+1; ++row){

                if(!Piece.isValidCoord(row, col)){
                    continue;
                }

                targetName = board[row][col].getName();

                if (targetName.equals("empty")){
                    Main.gui.setBlue(row, col);
                    continue;
                }

                theSide = board[row][col].getSide();
                if (theSide != this.getSide()){
                    Main.gui.setRed(row, col);
                }
            }
        }
    }
}
