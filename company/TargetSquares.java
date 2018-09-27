package com.company;

public class TargetSquares {

    private int[][] squareLocation;
    private int currentSpot;


    /*
    [0] = row
    [1] = column
    [2] = type (1 = red, 0 = blue)
     */
    public TargetSquares(){
        squareLocation = new int[1][3];
        currentSpot = 0;
    }

    public int[][] getTarget(){
        int[][] temp = squareLocation;
        squareLocation = new int[1][3];
        currentSpot = 0;
        return temp;
    }

    public void addTarget(int row, int col, boolean colour){
        if(squareLocation.length == currentSpot){
            expandList();
        }
        int [] addedThing = new int[3];
        addedThing[0] = row;
        addedThing[1] = col;

        if(colour) { addedThing[2] = 1;}
        else{addedThing[2] = 0;}

        squareLocation[currentSpot] = addedThing;
    }



    private void expandList(){
        currentSpot = 0;
        int[][] tempList = new int[squareLocation.length+1][4];
        for(int[] a: squareLocation){
            tempList[currentSpot] = a;
            currentSpot++;
        }
    }
}
