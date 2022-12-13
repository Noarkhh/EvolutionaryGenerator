package agh.ics.oop.variant_enums;

public enum BehaviorVariant {
    FULL_PREDESTINATION, A_TAD_OF_TOMFOOLERY;


    public static BehaviorVariant fromString(String string) throws IllegalArgumentException{
        return switch (string) {
            case "Full Predestination" -> FULL_PREDESTINATION;
            case "A Tad Of Tomfoolery" -> A_TAD_OF_TOMFOOLERY;
            default -> throw new IllegalArgumentException(string + ": illegal argument");
        };
    }

}
