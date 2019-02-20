package be.stivizu.projects.hashcode.model;

import java.util.List;
import java.util.stream.IntStream;

public class InputData {

    /*
        TODO[REQUIRED]: Add fields (primitive types or custom POJO's) to this class for all input data you want made
            available in the algorithm(s).
     */

    private static final String SPACE_SEPARATOR = " ";

    private int numberOfRows;

    private int numberOfCols;

    private int minNoOfEachIngredientPerSlice;

    private int maxNoOfCellsPerSlice;

    private PizzaPiece[][] pizza;

    private InputData() {}

    public InputData(final List<String> fileData) {
        final String[] generalProperties = fileData.get(0).split(SPACE_SEPARATOR);
        this.numberOfRows = Integer.parseInt(generalProperties[0]);
        this.numberOfCols = Integer.parseInt(generalProperties[1]);
        this.minNoOfEachIngredientPerSlice = Integer.parseInt(generalProperties[2]);
        this.maxNoOfCellsPerSlice = Integer.parseInt(generalProperties[3]);
        this.pizza = new PizzaPiece[numberOfRows][numberOfCols];
        IntStream.range(0, numberOfRows).forEach(rowIndex ->
                IntStream.range(0, numberOfCols).forEach(colIndex ->
                        pizza[rowIndex][colIndex] = new PizzaPiece(rowIndex, colIndex, fileData.get(rowIndex + 1).charAt(colIndex))
                ));
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfCols() {
        return numberOfCols;
    }

    public int getMinNoOfEachIngredientPerSlice() {
        return minNoOfEachIngredientPerSlice;
    }

    public int getMaxNoOfCellsPerSlice() {
        return maxNoOfCellsPerSlice;
    }

    public PizzaPiece getPizzaPiece(final int rowIndex, final int colIndex) {
        return pizza[rowIndex][colIndex];
    }

    public PizzaPiece[][] getPizza() {
        return pizza;
    }

}
