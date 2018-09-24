package com.company;

public class Knight extends Piece {
    public Knight(boolean side) {
        super(side);
        this.setName("knight");
    }

    public void CheckValidSquares(Coordinate location){
        int curRow = location.getRow();
        int curCol = location.getCol();

        int[] rowMods = {1,-1,2,1,-1,-2,-2,-1};
        int[] colMods = {-2,2,1,2,2,1,-1,-2};

        Main.gui.resetColours();
        for(int i = 0; i < colMods.length; ++i){
            int tempRow = curRow + rowMods[i];
            int tempCol = curCol + colMods[i];

            if(!Piece.isValidCoord(tempRow, tempCol)){
                continue;
            }

            if(Main.board[tempRow][tempCol].getName().equals("empty")){
                Main.gui.setBlue(tempRow, tempCol);
            }
            else if(Main.board[tempRow][tempCol].getSide() != this.getSide()){
                Main.gui.setRed(tempRow, tempCol);
            }
        }
    }
}
