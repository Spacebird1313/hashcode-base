package be.stivizu.projects.hashcode.model;

public enum Ingredient {

    TOMATO('T'),
    MUSHROOM('M');

    private char ingredientIdentifier;

    Ingredient(final char ingredientIdentifier) {
        this.ingredientIdentifier = ingredientIdentifier;
    }

    public char identifier() {
        return ingredientIdentifier;
    }

}
