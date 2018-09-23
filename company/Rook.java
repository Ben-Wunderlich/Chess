package com.company;

public class Rook extends Piece {
    public Rook(boolean side) {
        super(side);
        this.setName("rook");

        int[] rowFacts = new int[]{1,-1,0,0};
        int[] colFacts = new int[]{0,0,-1,1};
        this.setFactors(rowFacts, colFacts);
    }

    //make static version for non infinite pieces
}
