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
                    board[row][col] = new Empty(false);
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
                else if(col == 1 || col == 6){
                    board[row][col] = new Bishop(side);
                }
                else if(col == 2 || col == 5){
                    board[row][col] = new Knight(side);
                }
                else if(col == 3) {
                    if (side) {
                        board[row][col] = new Queen(true);
                    } else {
                        board[row][col] = new King(false);
                    }
                }
                else if(col==4){
                    if(side){
                        board[row][col] = new King(true);
                    }
                    else{
                        board[row][col] = new Queen(false);
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

    public static Piece[][] movePiece(Coordinate start, Coordinate end){
        Main.incrRound();

        Piece[][] board = Main.board;
        Piece[][] modBoard = board;

        int endRow = end.getRow();
        int endCol = end.getCol();
        int strtRow = start.getRow();
        int strtCol = start.getCol();

        if(board[strtRow][strtCol].getName().equals("pawn")){
            board[strtRow][strtCol].pieceMoved();
        }

        Piece saveEnd = new Empty(false);

        modBoard[endRow][endCol] = board[strtRow][strtCol];//makes destination be start

        modBoard[strtRow][strtCol] = saveEnd;

        return modBoard;
    }

    public static Piece[][] movePiece(Piece[][] board, Coordinate start, Coordinate end){

        Piece[][] modBoard = board;

        int endRow = end.getRow();
        int endCol = end.getCol();
        int strtRow = start.getRow();
        int strtCol = start.getCol();

        Piece saveEnd = new Empty(false);

        modBoard[endRow][endCol] = board[strtRow][strtCol];//makes destination be start

        modBoard[strtRow][strtCol] = saveEnd;

        return modBoard;
    }



    public static void showGrids(boolean[][] board){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col<board[0].length; col++){
                System.out.printf("%-6s", board[row][col]);
            }
        }
    }
}
