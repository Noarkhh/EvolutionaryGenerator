package agh.ics.oop.variant_enums;

public enum MutationsVariant {
    FULL_RANDOM, SLIGHT_CORRECTION;

    public static MutationsVariant fromString(String string) throws IllegalArgumentException{
        return switch (string) {
            case "Full Random" -> FULL_RANDOM;
            case "Slight Correction" -> SLIGHT_CORRECTION;
            default -> throw new IllegalArgumentException(string + ": illegal argument");
        };
    }
}
