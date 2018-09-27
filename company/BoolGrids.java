package com.company;

public class BoolGrids {

    private static Coordinate[] checkedCoords;

    public static boolean[][] makeBoolBoard(boolean side){
        Piece[][] board = Main.board;
        boolean[][] boolBoard = new boolean[8][8];

        Main.setIsDryRun(true);
        for(int row = 0; row < 8; row++){
            for(int col = 0; col <8; col++){
                if(board[row][col].getName().equals("empty")){
                    continue;
                }
                if(board[row][col].getSide() != side){
                    continue;
                }
                board[row][col].CheckValidSquares(new Coordinate(row, col));

                for(int i = 0; i < checkedCoords.length; i++){
                    if(checkedCoords[i] == null){
                        continue;
                    }
                    Coordinate c = checkedCoords[i];
                    boolBoard[c.getRow()][c.getCol()] = true;
                }

            }
        }
        Main.setIsDryRun(false);
        //BoardChange.showGrids(boolBoard);
        return boolBoard;
    }
    public static void setCoords(Coordinate[] coords){
        checkedCoords = coords;
    }

    public static boolean doesRemoveCheck(Coordinate start, Coordinate end,  boolean side){
        Piece[][] legacyBoard = Main.board;//hopefully won't need
        boolean[][] legacyBoolBoard = Main.getBoolGrid(!side);//!side or side?

        Piece[][] futureBoard = BoardChange.movePiece(start, end);
        boolean[][] theoBoard = makeBoolBoard(!side);

        Main.setBoolBoard(theoBoard, !side);
        Main.board = futureBoard;

        boolean removesCheck = !Main.gui.kingInCheck(side);

        Main.setBoolBoard(legacyBoolBoard, !side);
        Main.board = legacyBoard;
        Main.gui.resetNames();

        return removesCheck;
    }


   // public static boolean isInFutureCheck()

}
