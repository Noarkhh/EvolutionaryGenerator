package agh.ics.oop.core_classes;

import java.util.Random;

public enum MapDirection {
    N, NE, E, SE, S, SW, W, NW;

    private static final Random RNG = new Random();

    public static MapDirection getRandom() {
        return values()[RNG.nextInt(MapDirection.values().length)];
    }

    @Override
    public String toString() {
        return switch (this) {
            case N  -> "N";
            case NE -> "NE";
            case E  -> "E";
            case SE -> "SE";
            case S  -> "S";
            case SW -> "SW";
            case W  -> "W";
            case NW -> "NW";
        };
    }

    public MapDirection rotateBy(int rotation) {
        return switch ((this.ordinal() + rotation) % 8) {
            case 0 -> N;
            case 1 -> NE;
            case 2 -> E;
            case 3 -> SE;
            case 4 -> S;
            case 5 -> SW;
            case 6 -> W;
            case 7 -> NW;
            default -> throw new IllegalStateException("Unexpected value");
        };
    }

    public Vector toUnitVector() {
        return switch (this) {
            case N  -> new Vector(0, -1);
            case NE -> new Vector(1, -1);
            case E  -> new Vector(1, 0);
            case SE -> new Vector(1, 1);
            case S  -> new Vector(0, 1);
            case SW -> new Vector(-1, 1);
            case W  -> new Vector(-1, 0);
            case NW -> new Vector(-1, -1);
        };
    }
}