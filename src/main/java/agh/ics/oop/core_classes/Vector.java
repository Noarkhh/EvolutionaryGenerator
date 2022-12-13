package agh.ics.oop.core_classes;

import java.util.Objects;


public class Vector {
    public final int x;
    public final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector subtract(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector opposite() {
        return new Vector(-x, -y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Vector other_v)) return false;
        return other_v.x == x && other_v.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
