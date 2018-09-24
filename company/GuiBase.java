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

                GuiBoard[row][col] = new Button(board[row][col].getName());
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
       if(colour == -16711681){//if it is blue
           Coordinate target = new Coordinate(row, col);
            Main.board = BoardChange.movePiece(lastMove, target);
            resetColours();
        }

        lastMove = new Coordinate(row, col);

        if(Main.board[row][col].getName().equals("empty")){
            resetColours();
            cf.repaint();
            return;
        }
        Main.board[row][col].CheckValidSquares(s);
    }

    private static Coordinate getCoordinates(String source){
        String[] componenents = source.split(",", 4);
        int row = Integer.parseInt(componenents[2]);
        int col = Integer.parseInt(componenents[1]);

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
        cf.repaint();
    }

    public Coordinate getLastMove(){
        return this.lastMove;
    }

    public int getColour(int row, int col){
        return GuiBoard[row][col].getBackground().getRGB();
    }
}


//encapsulation
//+ = visible outside class
// - = hidden outside of class
