package agh.ics.oop.variant_enums;

public enum PlantGrowthVariant {
    OVERGROWN_EQUATORS, TOXIC_CARCASSES;

    public static PlantGrowthVariant fromString(String string) throws IllegalArgumentException{
        return switch (string) {
            case "Overgrown Equators" -> OVERGROWN_EQUATORS;
            case "Toxic Carcasses" -> TOXIC_CARCASSES;
            default -> throw new IllegalArgumentException(string + ": illegal argument");
        };
    }
}
