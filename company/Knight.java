package com.company;

public class Knight extends Piece {
    public Knight(boolean side) {
        super(side);
        this.setName("knight");
    }

    public void CheckValidSquares(Piece[][] board, Coordinate location){
        int curRow = location.getRow();
        int curCol = location.getCol();

        int[] rowMods = { 1,2, 2, 1,-1,-2, -2, -1};
        int[] colMods = {-2, -1, 1, 2, 2, 1, -1, -2 };

        for(int i = 0; i < colMods.length; ++i){
            int tempRow = curRow + rowMods[i];
            int tempCol = curCol + colMods[i];

            if(!Piece.isValidCoord(tempRow, tempCol)){
                continue;
            }

            if(board[tempRow][tempCol].getName().equals("empty")){
                Main.addTarget(tempRow, tempCol, 0, 0);

            }
            else if(board[tempRow][tempCol].getSide() != this.getSide()){
                Main.addTarget(tempRow, tempCol, 1, 0);
            }
        }
    }
}
