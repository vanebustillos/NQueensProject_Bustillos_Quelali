package com.company;

public class Board {

    int [][] board;
    int size;
    public Board(int size){
         board = new int [size][size];
         this.size = size;
     }

     public void initBoard(){
        if (size >= 4){
            for(int i = 0; i < size; i++){
                for(int j= 0; j < size; j++){
                    board[i][j] = 0;
                }
            }
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
}
