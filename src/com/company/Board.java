package com.company;

import java.util.*;

public class Board {

    char [][] board;
    int size;
    Set<Coord> notAvailablePositions = new HashSet<>();
    int nQueens = 0;

    public Board(int size){
         board = new char [size][size];
         this.size = size;
     }

     public void initBoard(){
        if (size >= 4){
            for(int i = 0; i < size; i++){
                for(int j= 0; j < size; j++){
                    board[i][j] = ' ';
                }
            }
            System.out.println("Init game!");
            printBoard();
        }
         else{
             throw new NullPointerException("The dimension must be at least 4.");
        }
     }

     private void printBoard(){
         for(int i = 0; i < size; i++){
             System.out.println("+-----".repeat(size) + "|" );
             for(int j= 0; j < size; j++){
                 System.out.print("|  " + board[i][j]+ "  " );
             }
             System.out.println("+" );
         }
         System.out.println("+-----".repeat(size) + "+" );
     }

     private void saveNotAvailablePosition(Coord queen) {
         int column = queen.column;
         int row = queen.row;

         for (int i = 0; i < size; i++) {
             Coord cordRow = new Coord(queen.row, i);
             Coord cordColumn = new Coord(i, queen.column);
             notAvailablePositions.add(cordRow); //vertical
             notAvailablePositions.add(cordColumn); // horizontal

             if(!queen.equals(cordRow)){
                 board[queen.row][i] = 'X';
             }
             if(!queen.equals(cordColumn)){
                 board[i][queen.column] = 'X';
             }
         }
         while (column >= 0 && row >= 0) { //Superior izquierdo
             Coord topLeft = new Coord(row,column);
             notAvailablePositions.add(topLeft);
             if(!queen.equals(topLeft)) {
                 board[row][column] = 'X';
             }
             column--;
             row--;
         }
         column = queen.column;
         row = queen.row;

         while (column <= size - 1 && row <= size - 1) { // Inferior derecho
             Coord lowerRight = new Coord(row,column);
             notAvailablePositions.add(lowerRight);

             if(!queen.equals(lowerRight)) {
                 board[row][column] = 'X';
             }
             column++;
             row++;
         }

         column = queen.column;
         row = queen.row;

         while (column >= 0 && row <= size -1) { // inferior izquierdo
             Coord lowerLeft = new Coord(row,column);
             notAvailablePositions.add(lowerLeft);
             if(!queen.equals(lowerLeft)) {
                 board[row][column] = 'X';
             }
             column--;
             row++;
         }

         column = queen.column;
         row = queen.row;

         while (column <= size - 1 && row >= 0) { //superior derecho
             Coord topRight = new Coord(row,column);
             notAvailablePositions.add(new Coord(row, column));
             if(!queen.equals(topRight)) {
                 board[row][column] = 'X';
             }
             column++;
             row--;

         }
     }

     public void setQueens2(){
        Random random = new Random();
        List<Integer> possibleChildRow = new ArrayList(size);
        List<Integer> possibleChildColumn = new ArrayList(size);

        for (int i = 0; i < size; i++){
            possibleChildRow.add(i);
            possibleChildColumn.add(i);
        }

        while(nQueens <= size){
            Collections.shuffle(possibleChildRow);
            Collections.shuffle(possibleChildColumn);

            if(!notAvailablePositions.contains(new Coord(possibleChildRow.get(0),possibleChildColumn.get(0)))){
               Coord child = new Coord(possibleChildRow.remove(0),possibleChildColumn.remove(0));
               saveNotAvailablePosition(child);
               board[child.row][child.column] = 'Q';
               nQueens++;
               System.out.println(nQueens + "° queen inserted.");
               System.out.println(notAvailablePositions.size() + " positions are not available.");
               printBoard();
            }
        }

     }
    public void setQueens(){
        Random random = new Random();
        int rowChild, columnChild;
        List occupiedColumns = new ArrayList(size);

        while(nQueens <= size){

           rowChild = random.nextInt(size - 1);
           columnChild = random.nextInt(size - 1);


           Coord child = new Coord(rowChild,columnChild);
              if(!notAvailablePositions.contains(child)){
                    saveNotAvailablePosition(child);
                    board[child.row][child.column] = 'Q';
                    nQueens++;
                    occupiedColumns.add(child.column);
                    System.out.println(nQueens + "° queen inserted.");
                    System.out.println(notAvailablePositions.size() + " positions are not available.");
                    printBoard();
                }
        }

    }
}
