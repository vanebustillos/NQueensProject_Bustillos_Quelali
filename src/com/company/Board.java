package com.company;

import java.util.*;

public class Board {

    char[][] board;
    int size;
    int nQueens = 0;
    Board parent;
    List<Coord> queensPossition = new ArrayList<>(size);

    public Board(int size) {
        board = new char[size][size];
        this.size = size;
    }

    public void initBoard(){
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

    private Board setQueen(Coord possiblePosition) {
        Board nextStep = new Board(size);
        if (isValidPosition(possiblePosition)) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == possiblePosition.row && j == possiblePosition.column) {
                        nextStep.board[i][j] = 'Q';
                        nextStep.queensPossition.add(new Coord(i,j));/////
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
                    children.add(setQueen(new Coord(i, j)));
                }
            }
        }
        return children;
    }

    //DFS (1) algorithm
    public void dfs1(Board initialBoard) {
        LinkedList<Board> boards  = new LinkedList<>();
        boards.add(initialBoard);
        while (!boards.isEmpty()) {
            Board lastBoard = boards.removeFirst();
            if (lastBoard.nQueens == size) {
                System.out.println("Solution was found!!");
               /* for (Coord queen: lastBoard.queensPossition) {
                    lastBoard.board[queen.row][queen.column] = 'Q';
                    lastBoard.printBoard();
                    System.out.println("Queen at: row:"+ queen.row +"  column: "+queen.column );
                }*/
                lastBoard.printBoard();
                break;
            }
            LinkedList<Board> newSuccessors = new LinkedList<>(lastBoard.successors());
            boards.addAll(0, newSuccessors);
        }
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
        return position;
    }

    //DFS (2) algorithm
    public void dfs2(Coord possiblePosition) {
        if (nQueens == size) {
            System.out.println("SOLUCIÓN ENCONTRADA!...");
            printBoard();
            return;
        } else {
            if (isValidPosition(possiblePosition)) {
                board[possiblePosition.row][possiblePosition.column] = 'Q';
                nQueens++;
                printBoard();
                dfs2(new Coord(possiblePosition.row + 1, possiblePosition.column));

            } else {
                if(possiblePosition.column < size - 1) {
                    dfs2(new Coord(possiblePosition.row, possiblePosition.column + 1));
                } else {
                    dfs2(new Coord(possiblePosition.row, 0));
                }
            }
            if(possiblePosition.column == size - 1 && nQueens < size){
                while (possiblePosition.column <= size - 1 ) {
                    Coord lastPosition = backtrack(new Coord(possiblePosition.row, possiblePosition.column));
                    //System.out.println("BACKTRACKING...");
                    nQueens--;
                    possiblePosition.row = lastPosition.row;
                    possiblePosition.column = lastPosition.column;
                }
                dfs2(new Coord(possiblePosition.row, possiblePosition.column + 1 ));
            }

        }
    }

}