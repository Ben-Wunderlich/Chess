package com.company;

public class Coordinate {
    private int ROW;
    private int COL;

    public Coordinate(int row, int col) throws CoordinateException{
        if(row < 0 || col < 0){
            throw new CoordinateException("Coordinate cannot be less than 0");
        }
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

    public boolean equals(Coordinate c){
        boolean rowEqual = this.getRow() == c.getRow();
        boolean colEqual = this.getCol() == c.getCol();
        return rowEqual && colEqual;
    }
}
