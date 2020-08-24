package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tamaño del tablero : ");
        int size = scanner.nextInt();

        if(size >= 4){
            Board board = new Board(size);
            board.initBoard();

            //board.dfs1(board);
            board.dfs2(new Coord(0,0));  //with Backtrack method
        } else {
            throw new NullPointerException("The dimension must be at least 4.");
        }
    }
}
