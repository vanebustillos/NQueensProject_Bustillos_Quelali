package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tamaño del tablero : ");
        int size = scanner.nextInt();

        if(size >= 4){
            Board board = new Board(size);
            board.initBoard();
            //Coord initialQueen = new Coord(0,1);
            board.board[0][1] = 'Q';
            board.board[2][2] = 'Q';
            board.printBoard();
            Coord coordBT = board.backtrack(new Coord(2,2));
            System.out.println(board.isValidPosition(coordBT));
            System.out.println("Row:" + coordBT.row + " Column: " + coordBT.column);
            board.printBoard();

        }
    }
}
