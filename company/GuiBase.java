package com.company;
import java.awt.*;
import java.awt.event.*;

class GuiBase extends Frame implements ActionListener{

    Frame cf;
    private Button[][] GuiBoard;
    private Coordinate lastMove;
    GuiBase() {
        Piece[][] board = Main.board;
        cf = new Frame();

        GuiBoard = new Button[8][8];
        for(int col = 0; col < 8; col++) {
            for(int row = 0; row < 8; row++) {

                GuiBoard[row][col] = new Button(boardName(row, col));
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
        Coordinate s = getCoordinates(ae.getSource().toString());
        int row = s.getRow();
        int col = s.getCol();

        int colour = GuiBoard[row][col].getBackground().getRGB();
       if(colour == -16711681 || colour == -65536){//if it is blue or red
           Coordinate target = new Coordinate(row, col);
            Main.board = BoardChange.movePiece(lastMove, target);
            resetNames();
            resetColours();
            Main.rotateSide();
            return;
        }

        lastMove = new Coordinate(row, col);
        String squareName = Main.board[row][col].getName();
        if(squareName.equals("empty")){
            resetColours();
            cf.repaint();
            return;
        }
        if(Main.board[row][col].getSide() == Main.getPlaySide()){//if it is their turn
            Main.board[row][col].CheckValidSquares(s);
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
    public Coordinate getLastMove(){
        return this.lastMove;
    }

    public int getColour(int row, int col){
        return GuiBoard[row][col].getBackground().getRGB();
    }

    private void resetNames(){
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

    private String boardName(int row, int col){
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

}

//encapsulation
//+ = visible outside class
// - = hidden outside of class
