package com.company;

public class Queen extends Piece{
    public Queen(boolean side) {
        super(side);
        this.setName("queen");


        int[] rowFacts = new int[]{1,-1,0,0,-1,1,-1,1};
        int[] colFacts = new int[]{0,0,-1,1,-1,-1,1,1};
        this.setFactors(rowFacts, colFacts);
    }
}
