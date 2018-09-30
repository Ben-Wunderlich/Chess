package com.company;

public class TargetSquares {

    private int[][] squareLocation;
    private int currentSpot;


    /*
    [0] = row
    [1] = column
    [2] = type (1 = red, 0 = blue)
    [3] = is pawn vertical move
     */
    public TargetSquares(){
        squareLocation = new int[1][4];
        currentSpot = 0;
    }
    public TargetSquares(int[][] othBoard){
        squareLocation = othBoard;
        currentSpot = othBoard.length;
    }

    public int[][] getTarget(){
        int[][] temp = squareLocation;
        squareLocation = new int[1][4];
        currentSpot = 0;
        return temp;
    }

    public void addTarget(int row, int col, int colour, int isPawnVert){
        if(squareLocation.length == currentSpot){
            expandList();
        }
        int [] addedThing = new int[4];
        addedThing[0] = row;
        addedThing[1] = col;
        addedThing[2] = colour;
        addedThing[3] = isPawnVert;

        squareLocation[currentSpot] = addedThing;
        ++currentSpot;
    }

    public void addTarget(int[] target){
        if(squareLocation.length == currentSpot){
            expandList();
        }
        squareLocation[currentSpot] = target;
        ++currentSpot;
    }

    public boolean hasATarget(){
        return currentSpot != 0;
    }



    private void expandList(){
        int placeholder = 0;
        int[][] tempList = new int[squareLocation.length+1][4];
        for(int[] a: squareLocation){
            tempList[placeholder] = a;
            ++placeholder;
        }
        squareLocation = tempList;
    }

    public void clearList(){
        squareLocation = new int[1][4];
        currentSpot = 0;
    }
}
