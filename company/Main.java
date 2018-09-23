package com.company;

//import java.util.Random;
//uvic buisness technology club for startup
//I should use interfaces for classes but am lazy
public class Main{
//learn visual basic
    public static void showBoard(Piece[][] board){

    final int tot_rows = board.length;
    final int tot_cols = board[0].length;

    for(int row = 0; row < tot_rows; ++row) {
        for (int col = 0; col < tot_cols; ++col) {
            System.out.printf("%-8s",board[row][col].getName());
        }
        System.out.println();
    }
    }

    //System.out.println(board.length); is the num of rows
    static Piece[][] board;
    public static void main(String[] args) {
        ///Random rand = new Random();
        //System.out.println(rand.nextInt(6)); // between 0-5

        boolean currSide = true;
        board = BoardChange.makeBoard();
        showBoard(board);
        //board = Piece.movePiece(board, new Coordinate(0, 0), new Coordinate(4, 7));
        System.out.println("\n");
        showBoard(board);

        /*GuiBase gary =*/ new GuiBase();

    }



}
