package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.InputData;
import be.stivizu.projects.hashcode.model.Slice;

import java.util.ArrayList;
import java.util.List;

public class HorizontalStrips extends Algorithm {

    public void doAlgorithm() {
        final List<Slice> slices = new ArrayList<>();

        final boolean[][] pizza = new boolean[inputData.getNumberOfRows()][inputData.getNumberOfCols()];
        final int stripSize = inputData.getMaxNoOfCellsPerSlice() <= inputData.getNumberOfCols()
                ? inputData.getMaxNoOfCellsPerSlice()
                : inputData.getNumberOfCols();

        for (int rowIndex = 0; rowIndex < inputData.getNumberOfRows(); rowIndex++) {
            for (int colIndex = 0; colIndex <= inputData.getNumberOfCols() - stripSize; colIndex++) {
                if (canPlaceSlice(rowIndex, colIndex, inputData, stripSize, pizza)) {
                    slices.add(new Slice(rowIndex, rowIndex, colIndex, colIndex + stripSize - 1));
                }
            }
        }

        outputData.setSlices(slices);
    }

    private boolean canPlaceSlice(final int rowIndex, final int startColIndex, final InputData inputData,
                                  final int stripSize, boolean[][] pizza) {
        for (int colIndex = startColIndex; colIndex < startColIndex + stripSize; colIndex++) {
            if (pizza[rowIndex][colIndex]) {
                return false;
            }
        }
        int mushroomCount = 0;
        int tomatoCount = 0;
        for (int colIndex = startColIndex; colIndex < startColIndex + stripSize; colIndex++) {
            switch (inputData.getPizza()[rowIndex][colIndex].getIngredient()) {
                case MUSHROOM: mushroomCount++; break;
                case TOMATO: tomatoCount++; break;
            }
        }
        if (mushroomCount >= inputData.getMinNoOfEachIngredientPerSlice()
                && tomatoCount >= inputData.getMinNoOfEachIngredientPerSlice()) {
            for (int colIndex = startColIndex; colIndex < startColIndex + stripSize; colIndex++) {
                pizza[rowIndex][colIndex] = true;
            }
            return true;
        }
        return false;
    }

}
