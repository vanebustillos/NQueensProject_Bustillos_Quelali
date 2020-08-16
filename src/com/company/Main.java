package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tamaño del tablero : ");
        int size = scanner.nextInt();

       Board board = new Board(size);
       board.initBoard();

    }
}
