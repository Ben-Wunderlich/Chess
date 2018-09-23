package com.company;
import java.awt.*;
import java.awt.event.*;

class GuiBase extends Frame implements ActionListener{

    Frame cf;
    GuiBase() {
        Piece[][] board = Main.board;
        cf = new Frame();

        Button[][] GuiBoard = new Button[8][8];
        for(int col = 0; col < 8; col++) {
            for(int row = 0; row < 8; row++) {

                GuiBoard[row][col] = new Button(board[row][col].getName());
                GuiBoard[row][col].setBounds(15 + 50 * col, 30 + row*50, 50, 50);
                GuiBoard[row][col].addActionListener(this);
                cf.add(GuiBoard[row][col]);
            }
        }

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
        Coordinate selSquare = getCoordinates(ae.getSource().toString());
        //System.out.println(ae.getSource().toString());
    }

    private static Coordinate getCoordinates(String source){
        String[] componenents = source.split(",", 4);
        int row = Integer.parseInt(componenents[2]);
        int col = Integer.parseInt(componenents[1]);

        row = (row - 15) / 50;
        col = (col - 30) / 50;
        System.out.println("this should be row"+row);
        System.out.println("this should be column"+col);
        System.out.println();
        return new Coordinate(row,col);
    }
}


//encapsulation
//+ = visible outside class
// - = hidden outside of class
