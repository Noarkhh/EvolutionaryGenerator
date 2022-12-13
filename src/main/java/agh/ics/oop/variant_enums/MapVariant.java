package agh.ics.oop.variant_enums;

public enum MapVariant {
    GLOBE, HELLISH_PORTAL;


    public static MapVariant fromString(String string) throws IllegalArgumentException{
        return switch (string) {
            case "Globe" -> GLOBE;
            case "Hellish Portal" -> HELLISH_PORTAL;
            default -> throw new IllegalArgumentException(string + ": illegal argument");
        };
    }
}


