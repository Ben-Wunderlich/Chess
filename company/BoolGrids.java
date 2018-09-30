package com.company;

public class BoolGrids {

    public static boolean[][] makeBoolBoard(boolean side) {
        Piece[][] board = Main.board;
        boolean[][] boolBoard = new boolean[8][8];

        activateSquares(board, side);

        int[][] targetSquares = Main.getTargets();
        for (int[] targ : targetSquares) {
            if (targ[3] == 0) {
                boolBoard[targ[0]][targ[1]] = true;
            }

            //BoardChange.showGrids(boolBoard);
        }
        return boolBoard;
    }

    public static boolean[][] manualBoolBoard(Piece[][] board, boolean side) {
        boolean[][] boolBoard = new boolean[8][8];

        activateSquares(board, side);

        int[][] targetSquares = Main.getTargets();
        for (int[] targ : targetSquares) {
            if (targ[3] == 0) {
                boolBoard[targ[0]][targ[1]] = true;
            }

        }
        return boolBoard;
    }


    public static boolean doesRemoveCheck(Coordinate start, boolean side){
        Piece[][] legacyBoard = copyBoard();//hopefully won't need

        if(!Main.hasTarget()){
            return false;
        }

        Coordinate end;
        boolean removesCheck = false;
        Piece[][] futureBoard;
        boolean[][] theoBoard;

        TargetSquares workingMoves = new TargetSquares();

        int[][] targets = Main.getTargets();
        for(int[] targ: targets){
            end = new Coordinate(targ[0], targ[1]);

            futureBoard = BoardChange.movePiece(legacyBoard, start, end);

            theoBoard = manualBoolBoard(futureBoard, !side);
            removesCheck = !Main.gui.kingInCheck(futureBoard, theoBoard, side);

            if(removesCheck){
                workingMoves.addTarget(targ);
            }
        }
        if(workingMoves.hasATarget()) {
            addTargets(workingMoves.getTarget());
        }

        //Main.board = legacyBoard; // this makes no difference
        //Main.gui.resetNames();
        return removesCheck;
    }

    public static void activateSquares(Piece[][] board, boolean side){
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col].getName().equals("empty")) {
                    continue;
                }
                if (board[row][col].getSide() != side) {
                    continue;
                }
                board[row][col].CheckValidSquares(board, new Coordinate(row, col));

            }
        }
    }

    private static void addTargets(int[][] collection){
        Main.clearTargets();
        for(int[] targ: collection){
            Main.addTarget(targ);
        }
    }

    private static Piece[][] copyBoard(){
        Piece[][] newVersion = new Piece[8][8];
        Piece[][] mainBoard = Main.board;
        for(int row = 0; row < 8; ++row){
            for(int col = 0; col < 8; ++col){
                String name = mainBoard[row][col].getName();
                boolean side = mainBoard[row][col].getSide();
                Piece newPiece;
                switch(name) {
                    case "pawn":
                        newPiece = new Pawn(side);
                        if(mainBoard[row][col].hasMoved()){ newPiece.pieceMoved();
                        }break;

                    case "rook":
                        newPiece = new Rook(side);
                        break;

                    case "bishop":
                        newPiece = new Bishop(side);
                        break;

                    case "knight":
                        newPiece = new Knight(side);
                        break;

                    case "king":
                        newPiece = new King(side);
                        break;
                    case "queen":
                        newPiece = new Queen(side);
                        break;
                    case "empty":
                        newPiece = new Empty(false);
                        break;
                    default:
                        newPiece = new Piece(side);
                }
                newVersion[row][col] = newPiece;
            }
        }
        return newVersion;
    }
}
