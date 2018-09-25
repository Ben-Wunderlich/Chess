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
    }//not used anymore

    //System.out.println(board.length); is the num of rows
    public static Piece[][] board;
    public static GuiBase gui;
    private static Boolean playSide = true;
    private static Boolean isDryRun = false;

    private static boolean[][] checkedSquaresWhite;
    private static boolean[][] checkedSquaresBlack;
    public static void main(String[] args) {
        ///Random rand = new Random();
        //System.out.println(rand.nextInt(6)); // between 0-5

        boolean currSide = true;
        board = BoardChange.makeBoard();


        //checkedSquaresBlack = BoolGrids.makeBoolBoard(false);
        //checkedSquaresWhite = BoolGrids.makeBoolBoard(true);

        gui = new GuiBase();
        showBoard(board);
        System.out.println("\n");

    }

     public static boolean getPlaySide(){
        return playSide;
    }
    public static void rotateSide(){
        playSide = !playSide;
    }

    public static void setBoolBoard(boolean[][] newBoard, boolean side){
        if(side){
            checkedSquaresWhite = newBoard;
        }
        else{
            checkedSquaresBlack = newBoard;
        }
    }
    public static boolean getIsDryRun(){
        return isDryRun;
    }
    public static void setIsDryRun(boolean isUnlocked){
        isDryRun = isUnlocked;
    }
}
