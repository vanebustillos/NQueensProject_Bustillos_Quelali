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
            for ( Coord notAvailablePos : notAvailablePositions) {
                if(!queen.equals(notAvailablePos)) {
                    board[notAvailablePos.row][notAvailablePos.column] = 'x';
                }
            }
            printBoard();
            //System.out.println( "x :" + xRandom + "y:" +yRandom );


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
    /*private void searchAvailablePositions() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; i < size; j++) {
               if( board[i][j] == ' ') {

               }
            }
        }
    }
    private void searchSolution() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; i < size; j++) {
            }
        }

     }*/

     private void saveNotAvailablePosition(Coord queen) {
         int column = queen.column;
         int row = queen.row; //3
         //int columnInf = queen.column;
         //int rowInf = queen.row;

         for (int i = 0; i < size; i++) {
             notAvailablePositions.add(new Coord(queen.row, i)); //vertical
             notAvailablePositions.add(new Coord(i, queen.column)); // horizontal

         }
         while (column >= 0 && row >= 0) { //Superior izquierdo
             notAvailablePositions.add(new Coord(row, column));
             column--;
             row--;
         }
         column = queen.column;
         row = queen.row;

         while (column < size - 1 && row <= size - 1) { // Inferior derecho
             notAvailablePositions.add(new Coord(row, column));
             column++;
             row++;
         }

         column = queen.column;
         row = queen.row;

         while (column >= 0 && row <= size -1) { // inferior izquierdo
             notAvailablePositions.add(new Coord(row, column));
             column--;
             row++;
         }

         column = queen.column;
         row = queen.row;

         while (column <= size - 1 && row >= 0) { //superior derecho
             System.out.println("columna: " + column + "fila: " + row);
             notAvailablePositions.add(new Coord(row, column));
             column++;
             row--;

         }
     }

}
