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
    public boolean isValidPosition(Coord possibleQueen) { //Buscar espacio disponible
        int column = possibleQueen.column;
        int row = possibleQueen.row;

        for (int i = 0; i <= size - 1; i++) {
           // Coord cordRow = new Coord(possibleQueen.row, i);
           // Coord cordColumn = new Coord(i, possibleQueen.column);

            if (board[row][i] == 'Q'){
                return false;
            }
            if (board[i][column] == 'Q') {
                return false;
            }
        }
        while (column >= 0 && row >= 0) { //Superior izquierdo
            // Coord topLeft = new Coord(row, column);
            if (board[row][column] == 'Q') {
                return false;
            }
            column--;
            row--;
        }
        column = possibleQueen.column;
        row = possibleQueen.row;

        while (column <= size - 1 && row <= size - 1) { // Inferior derecho
            // Coord lowerRight = new Coord(row, column);
            if (board[row][column] == 'Q') {
                return false;
            }
            column++;
            row++;
        }

        column = possibleQueen.column;
        row = possibleQueen.row;

        while (column >= 0 && row <= size - 1) { // inferior izquierdo
            // Coord lowerLeft = new Coord(row, column);
            if (board[row][column] == 'Q') {
                return false;
            }
            column--;
            row++;
        }

        column = possibleQueen.column;
        row = possibleQueen.row;

        while (column <= size - 1 && row >= 0) { //superior derecho
            //Coord topRight = new Coord(row, column);
            if (board[row][column] == 'Q') {
                return false;
            }
            column++;
            row--;
        }
        return true;
    }

    //backtrack method
    public Coord backtrack(Coord position){
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