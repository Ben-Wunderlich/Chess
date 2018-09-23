package com.company;

public class Bishop extends Piece{
    public Bishop(boolean side) {
        super(side);

        int[] rowFacts = new int[]{-1,1,-1,1};
        int[] colFacts = new int[]{-1,-1,1,1};
        this.setFactors(rowFacts, colFacts);
        this.setName("bishop");
    }

}
