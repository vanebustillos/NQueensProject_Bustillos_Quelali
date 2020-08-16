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
            printBoard();
            System.out.println( "x :" + xRandom + "y:" +yRandom );
            Coord queen = new Coord(xRandom,yRandom);
            saveNotAvailablePosition(queen);
            for (Coord notAvailable: notAvailablePositions) {
                System.out.println(notAvailable);
            }
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
        int columnSup = queen.column;
        int rowSup = queen.row; //3
        int columnInf = queen.column;
        int rowInf = queen.row;

        for(int i = 0; i < size ; i++){
            notAvailablePositions.add(new Coord(queen.row, i)); //vertical
            notAvailablePositions.add(new Coord(i, queen.column)); // horizontal

        }
        while((columnSup >= 0 && rowSup >= 0) || (columnInf < size -1 && rowInf < size - 1)) {
            notAvailablePositions.add(new Coord(rowSup, columnSup));
            notAvailablePositions.add(new Coord(rowInf, columnInf));
            columnSup--;
            rowSup--;
            columnInf++;
            rowInf++;

           // System.out.println("Sub:¨" + columnSup + " " + rowSup);
           // System.out.println("Inf:¨" + columnInf + " " + rowInf);

        }

        columnSup = queen.column;
        rowSup = queen.row;
        columnInf = queen.column;
        rowInf = queen.row;

         while ((columnSup < size -1 && rowSup >=0) || (columnInf >=0  && rowInf < size -1 )) {

             notAvailablePositions.add(new Coord(rowInf, columnInf));
             notAvailablePositions.add(new Coord(rowSup, columnSup));
             columnSup++;
             rowSup--;
             columnInf--;
             rowInf++;

            System.out.println("Sub:¨" + columnSup + " " + rowSup);
            System.out.println("Inf:¨" + columnInf + " " + rowInf);
        }
     }

}
