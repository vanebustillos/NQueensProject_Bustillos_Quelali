package com.company;

import java.util.*;

public class Board {

    char [][] board;
    int size;
    
    Set<Coord> notAvailablePositions = new HashSet<>();
    List<Coord> queens;
    public Board(int size){
         board = new char [size][size];
         this.size = size;
     }

     public void initBoard(){
        Random random = new Random();
        //Board startBoard = new Board(size);
        int xRandom  = random.nextInt((size - 1));
        int yRandom = random.nextInt(size - 1);
        if (size >= 4){
            for(int i = 0; i < size; i++){
                for(int j= 0; j < size; j++){
                    board[i][j] = ' ';
                }
            }
            board[xRandom][yRandom] = 'Q'; // board[column][file]
            Coord queen = new Coord(xRandom,yRandom);
            saveNotAvailablePosition(queen);
            printBoard();
        }
         else{
             throw new NullPointerException("La dimensión debe ser mayor o igual a 4.");
        }
     }

     public void printBoard(){
         for(int i = 0; i < size; i++){
             System.out.println("+-----".repeat(size) + "+" );
             for(int j= 0; j < size; j++){
                 System.out.print("+  " + board[i][j]+ "  " );
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

}
