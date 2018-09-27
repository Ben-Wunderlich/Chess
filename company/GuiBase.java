package com.company;
import org.omg.CORBA.SystemException;

import java.awt.*;
import java.awt.event.*;

class GuiBase extends Frame implements ActionListener{

    Frame cf;
    private Button[][] GuiBoard;
    private Coordinate lastMove;
    GuiBase() {
        cf = new Frame();

        GuiBoard = new Button[8][8];
        for(int col = 0; col < 8; col++) {
            for(int row = 0; row < 8; row++) {

                GuiBoard[row][col] = new Button(getName(row, col));
                GuiBoard[row][col].setBounds(15 + 50 * col, 30 + row*50, 50, 50);
                GuiBoard[row][col].addActionListener(this);
                cf.add(GuiBoard[row][col]);
            }
        }
        resetColours();

        cf.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });

        cf.setSize(50*8 + 15*2,40+8*50);
        cf.setLayout(null);
        cf.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        Coordinate targetSquare = getCoordinates(ae.getSource().toString());
        int row = targetSquare.getRow();
        int col = targetSquare.getCol();
        boolean side = Main.getPlaySide();
        Piece[][] board = Main.board;

        int colour = GuiBoard[row][col].getBackground().getRGB();

        if(colour == -16711681 || colour == -65536){//if it is blue or red
           Coordinate target = new Coordinate(row, col);
            BoardChange.movePiece(lastMove, target);
            resetNames();
            resetColours();
            Main.setBoolBoard(BoolGrids.makeBoolBoard(side), side);
            Main.rotateSide();
            return;
        }

        lastMove = new Coordinate(row, col);
        String squareName = board[row][col].getName();
        if(squareName.equals("empty")){
            resetColours();
        }
        else if(board[row][col].getSide() == side){//if it is their turn
            if(kingInCheck(side)){
                Main.setKingInCheck(side);
            }
            board[row][col].CheckValidSquares(targetSquare);
        }
        else{
            resetColours();
        }
    }

    private static Coordinate getCoordinates(String source){
        String[] components = source.split(",", 4);
        int row = Integer.parseInt(components[2]);
        int col = Integer.parseInt(components[1]);

        row = (row - 15) / 50;
        col = (col - 15) / 50;

        return new Coordinate(row,col);
    }

    public void setBlue(int row, int col){
            GuiBoard[row][col].setBackground(Color.cyan);
    }

    public void setRed(int row, int col){
        GuiBoard[row][col].setBackground(Color.red);
    }

    public void resetColours(){
        boolean isWhite = true;
        for(Button[] a: GuiBoard){
            isWhite = !isWhite;
            for(Button c: a){
                isWhite = !isWhite;
                if(isWhite){
                    c.setBackground(Color.LIGHT_GRAY);
                }
                else{
                    c.setBackground(Color.GRAY);
                }
            }
        }
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                GuiBoard[row][col].setForeground(sideColour(row, col));
            }
        }
        cf.repaint();
    }

    public void resetNames(){
        for(int row = 0; row < 8; row++){
            for(int col = 0; col <8; col++){
                String name = Main.board[row][col].getName();
                if(name.equals("empty")){
                    GuiBoard[row][col].setLabel("");
                }
                else{
                    GuiBoard[row][col].setLabel(name);
                }
            }
        }
    }

    private String getName(int row, int col){
        Piece[][] board = Main.board;
        String name = board[row][col].getName();
        if(name.equals("empty")){
            return "";
        }
        else{
            return name;
        }
    }

    private Color sideColour(int row, int col){
        boolean side = Main.board[row][col].getSide();
        if(side){ return Color.WHITE; }
        else{ return Color.black; }
    }

    public boolean kingInCheck(boolean side){
        if(Main.getRound() < 3){
            return false;
        }
        Coordinate kingPos = whereKing(side);
        int row = kingPos.getRow();
        int col = kingPos.getCol();
        boolean[][] boolGrid = Main.getBoolGrid(!side);
        //BoardChange.showGrids(boolGrid);
        return(boolGrid[row][col]);
        //if kings position is checked by opposing team
    }

    private Coordinate whereKing(boolean side){
        boolean pieceSide;
        String name;
        Piece[][] board = Main.board;
        for(int row = 0; row < 8; ++row){
            for(int col = 0; col < 8; ++col){
                name = board[row][col].getName();
                pieceSide = board[row][col].getSide();
                if(name.equals("king") && (pieceSide == side)){
                    return new Coordinate(row, col);
                }
            }
        }
        throw new IndexOutOfBoundsException("Error 2315: Something went very wrong");
    }

    private boolean canMoveThere(int row, int col){
        if(Main.isSomeoneChecked()){
            Coordinate end = new Coordinate(row, col);
            Coordinate start = lastMove;

            if(BoolGrids.doesRemoveCheck(start, end, Main.getPlaySide())){
                Main.kingsUnchecked();
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }

}

//encapsulation
//+ = visible outside class
// - = hidden outside of class
