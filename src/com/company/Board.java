package com.company;

import java.util.*;

public class Board {

    char[][] board;
    int size;
    int nQueens = 0;

    public Board(int size) {
        board = new char[size][size];
        this.size = size;
    }

    public void initBoard() {
        if (size >= 4) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = ' ';
                }
            }
            System.out.println("Init game!");
            printBoard();
        } else {
            throw new NullPointerException("The dimension must be at least 4.");
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            System.out.println("+-----".repeat(size) + "|");
            for (int j = 0; j < size; j++) {
                System.out.print("|  " + board[i][j] + "  ");
            }
            System.out.println("+");
        }
        System.out.println("+-----".repeat(size) + "+");
    }

    private boolean isValidPosition(Coord queen) {
        int column = queen.column;
        int row = queen.row;

        for (int i = 0; i < size; i++) {
            Coord cordRow = new Coord(queen.row, i);
            Coord cordColumn = new Coord(i, queen.column);

            if (queen.equals(cordRow)) {
                //board[queen.row][i] = 'X';
                return false;
            }
            if (queen.equals(cordColumn)) {
                //board[i][queen.column] = 'X';
                return false;
            }
        }
        while (column >= 0 && row >= 0) { //Superior izquierdo
            Coord topLeft = new Coord(row, column);
            if (queen.equals(topLeft)) {
                //board[row][column] = 'X';
                return false;
            }
            column--;
            row--;
        }
        column = queen.column;
        row = queen.row;

        while (column <= size - 1 && row <= size - 1) { // Inferior derecho
            Coord lowerRight = new Coord(row, column);
            if (queen.equals(lowerRight)) {
                //board[row][column] = 'X';
                return false;
            }
            column++;
            row++;
        }

        column = queen.column;
        row = queen.row;

        while (column >= 0 && row <= size - 1) { // inferior izquierdo
            Coord lowerLeft = new Coord(row, column);
            if (queen.equals(lowerLeft)) {
                //board[row][column] = 'X';
                return false;
            }
            column--;
            row++;
        }

        column = queen.column;
        row = queen.row;

        while (column <= size - 1 && row >= 0) { //superior derecho
            Coord topRight = new Coord(row, column);
            if (queen.equals(topRight)) {
                //board[row][column] = 'X';
                return false;
            }
            column++;
            row--;

        }
        return true;
    }

    //backtrack method
    private Coord backtrack(Coord position){
        Coord lastPosition;
        int lastRow = position.row;
        int lastColumn = position.column;
        for(int i = lastRow; i >=0 ; i--){
            for(int j = lastColumn; j >= 0; j--){
                if(board[i][j] != ' '){ // if there is a queen, delete the last one
                    board[i][j] = ' ';
                    lastPosition = new Coord(i,j);
                    return lastPosition;
                }
            }
        }
        return null;
    }

}