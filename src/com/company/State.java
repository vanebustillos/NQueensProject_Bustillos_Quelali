package com.company;

public class State {
    public State(Coord queen, Board actualBoard, Board lastBoard) {
        this.queen = queen;
        this.actualBoard = actualBoard;
        this.lastBoard = lastBoard;
    }

    Coord queen;
    Board actualBoard;
    Board lastBoard;

}
