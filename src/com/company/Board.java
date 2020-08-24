package com.company;

import java.util.*;

public class Board {

    char[][] board;
    int size;
    int nQueens = 0;
    Board parent;

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

    public  void printBoard() {
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
            if (board[row][i] == 'Q'){
                return false;
            }
            if (board[i][column] == 'Q') {
                return false;
            }
        }
        while (column >= 0 && row >= 0) { //Superior izquierdo
            if (board[row][column] == 'Q') {
                return false;
            }
            column--;
            row--;
        }
        column = possibleQueen.column;
        row = possibleQueen.row;

        while (column <= size - 1 && row <= size - 1) { // Inferior derecho
            if (board[row][column] == 'Q') {
                return false;
            }
            column++;
            row++;
        }

        column = possibleQueen.column;
        row = possibleQueen.row;

        while (column >= 0 && row <= size - 1) { // inferior izquierdo
            if (board[row][column] == 'Q') {
                return false;
            }
            column--;
            row++;
        }

        column = possibleQueen.column;
        row = possibleQueen.row;

        while (column <= size - 1 && row >= 0) { //superior derecho
            if (board[row][column] == 'Q') {
                return false;
            }
            column++;
            row--;
        }
        return true;
    }

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
    private Board setNQueen(Coord possiblePosition) {
        Board nextStep = new Board(size);
        if (isValidPosition(possiblePosition)) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == possiblePosition.row && j == possiblePosition.column) {
                        nextStep.board[i][j] = 'Q';
                        //nQueens ++;
                    } else {
                        nextStep.board[i][j] = this.board[i][j];
                    }
                }
            }
            nextStep.parent = this;
            nextStep.nQueens = this.nQueens + 1;
        }
        return nextStep;
    }
    private List<Board> successors(){
        List<Board> children = new LinkedList<>();
        if (nQueens >= size) {
            return Collections.emptyList();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] == ' ') {
                    children.add(setNQueen(new Coord(i, j)));
                }
            }
        }
        return children;
    }
    public void dfs(Board initialBoard) {
        LinkedList<Board> boards  = new LinkedList<>();
        boards.add(initialBoard);
        while (!boards.isEmpty()) {
            Board lastBoard = boards.removeFirst();
            if (lastBoard.nQueens == size) {
                System.out.println("Found Solution");
                lastBoard.printBoard();
                break;
            }
            LinkedList<Board> newSuccessors = new LinkedList<>(lastBoard.successors());
            boards.addAll(0, newSuccessors);
        }
    }

}