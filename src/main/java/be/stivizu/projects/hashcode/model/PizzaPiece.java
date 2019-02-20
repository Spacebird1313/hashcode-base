package be.stivizu.projects.hashcode.model;

import java.util.Arrays;

public class PizzaPiece {

    private final int rowIndex;

    private final int colIndex;

    private final Ingredient ingredient;

    public PizzaPiece(final int rowIndex, final int colIndex, final char ingredientIdentifier) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.ingredient = getIngredientFromIdentifier(ingredientIdentifier);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    private Ingredient getIngredientFromIdentifier(final char ingredientIdentifier) {
        return Arrays.stream(Ingredient.values())
                .filter(ingredientIdentifierRef -> ingredientIdentifierRef.identifier() == ingredientIdentifier)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find a matching ingredient for identifier <" + ingredientIdentifier + ">"));
    }

}
