package com.company;

public class Knight extends Piece {
    public Knight(boolean side) {
        super(side);
        this.setName("knight");
    }

    public void checkValidSquares(Piece[][] board, Coordinate location){
        int curRow = location.getRow();
        int curCol = location.getCol();

        int[] rowMods = {1,-1,2,1,-1,-2,-2,-1};
        int[] colMods = {-2,2,1,2,2,1,-1,-2};

        for(int i = 0; i < colMods.length; ++i){
            int tempRow = curRow + rowMods[i];
            int tempCol = curCol + colMods[i];

            if(!Piece.isValidCoord(tempRow, tempCol, board)){
                continue;
            }

            if(board[tempRow][tempCol].getName().equals("empty")){
                //make square blue
            }
            else if(board[tempRow][tempCol].getSide() != this.getSide()){
                //make square red
            }
        }
    }
}
