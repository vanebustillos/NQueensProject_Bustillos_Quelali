package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    char [][] board;
    int size;
    //boolean [] vertical;
    //boolean [] horizontal;
    //boolean [] diagonalInf;
    //boolean [] diagonalSup;
    //char queen =
    List<Coord> notAvailablePositions;
    List<Coord> queens;
    public Board(int size){
         board = new char [size][size];
         this.size = size;
     }

     public void initBoard(){
        Random random = new Random();
        int initial = random.nextInt(size-1);
        if (size >= 4){
            for(int i = 0; i < size; i++){
                for(int j= 0; j < size; j++){
                    board[i][j] = ' ';
                }
            }
            board[initial][initial] = 'Q'; // board[column][file]
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
    private void searchAvailablePositions() {
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

     }

     private void saveNotAvailablePosition(Coord queen) {

     }

}
