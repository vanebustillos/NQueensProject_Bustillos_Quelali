package com.company;

public class State {
    Coord queen;
    Board currentBoard;
    Board lastBoard;

    public State(Coord queen, Board currentBoard, Board lastBoard) {
        this.queen = queen;
        this.currentBoard = currentBoard;
        this.lastBoard = lastBoard;
    }

}