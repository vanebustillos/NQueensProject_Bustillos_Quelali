package com.company;

import java.util.Objects;

public class Coord {
        int row;
        int column;

    public Coord(int row, int column) {
        this.row = row;
        this.column = column;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coord)) return false;
        Coord queen = (Coord) o;
        return row == queen.row &&
                column == queen.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }


}
