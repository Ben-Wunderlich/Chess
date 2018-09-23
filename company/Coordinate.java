package com.company;

public class Coordinate {
    private int ROW;
    private int COL;

    public Coordinate(int row, int col){
        ROW = row;
        COL = col;
    }
    public int getRow(){
       return ROW;
    }
    public int getCol(){
        return COL;
    }
    public void setLocation(int row, int col){
        ROW = row;
        COL = col;
    }
}
