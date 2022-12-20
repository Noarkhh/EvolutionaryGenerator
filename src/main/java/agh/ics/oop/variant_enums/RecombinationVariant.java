package agh.ics.oop.variant_enums;

public enum RecombinationVariant {
    NON_DARWINIAN, DARWINIAN;

    public static RecombinationVariant fromString(String string) throws IllegalArgumentException{
        return switch (string) {
            case "Non-Darwinian" -> NON_DARWINIAN;
            case "Darwinian" -> DARWINIAN;
            default -> throw new IllegalArgumentException(string + ": illegal argument");
        };
    }
}
