package com.company;

public class BoardChange {

    public static Piece[][] makeBoard() {
        final int ROWS = 8;
        final int COLS = 8;

        Piece board[][] = new Piece[ROWS][COLS];

        boolean side;
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (row > 1 && row < 6) {
                    board[row][col] = new Empty();
                    continue;
                }
                else {
                    side = determineSide(row);
                }
                if(row == 1 || row == 6){
                    board[row][col] = new Pawn(side);
                }

                else if(col==0 || col==7){
                    board[row][col] = new Rook(side);
                }//biotech
                //application for due by friday ecs 204 //yellow form
                else if(col == 1 || col == 6){
                    board[row][col] = new Bishop(side);
                }
                else if(col == 2 || col == 5){
                    board[row][col] = new Knight(side);
                }
                else if(col == 3) {
                    if (side) {
                        board[row][col] = new Queen(side);
                    } else {
                        board[row][col] = new King(side);
                    }
                }
                else if(col==4){
                    if(side){
                        board[row][col] = new King(side);
                    }
                    else{
                        board[row][col] = new Queen(side);
                    }
                }
            }
        }
        return board;
    }

    public static boolean determineSide(int row){
        if(row < 3){
            return false;
        }
        else{
            return true;
        }
    }
}
