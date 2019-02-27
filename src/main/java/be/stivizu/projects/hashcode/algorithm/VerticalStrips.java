package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.InputData;
import be.stivizu.projects.hashcode.model.Slice;

import java.util.ArrayList;
import java.util.List;

public class VerticalStrips extends Algorithm {

    public void doAlgorithm() {
        final List<Slice> slices = new ArrayList<>();

        final boolean[][] pizza = new boolean[inputData.getNumberOfRows()][inputData.getNumberOfCols()];
        final int stripSize = inputData.getMaxNoOfCellsPerSlice() <= inputData.getNumberOfRows()
                ? inputData.getMaxNoOfCellsPerSlice()
                : inputData.getNumberOfRows();

        for (int colIndex = 0; colIndex < inputData.getNumberOfCols(); colIndex++) {
            for (int rowIndex = 0; rowIndex <= inputData.getNumberOfRows() - stripSize; rowIndex++) {
                if (canPlaceSlice(colIndex, rowIndex, inputData, stripSize, pizza)) {
                    slices.add(new Slice(rowIndex, rowIndex + stripSize - 1, colIndex, colIndex));
                }
            }
        }

        outputData.setSlices(slices);
    }

    private boolean canPlaceSlice(final int colIndex, final int startRowIndex, final InputData inputData,
                                  final int stripSize, boolean[][] pizza) {
        for (int rowIndex = startRowIndex; rowIndex < startRowIndex + stripSize; rowIndex++) {
            if (pizza[rowIndex][colIndex]) {
                return false;
            }
        }
        int mushroomCount = 0;
        int tomatoCount = 0;
        for (int rowIndex = startRowIndex; rowIndex < startRowIndex + stripSize; rowIndex++) {
            switch (inputData.getPizza()[rowIndex][colIndex].getIngredient()) {
                case MUSHROOM: mushroomCount++; break;
                case TOMATO: tomatoCount++; break;
            }
        }
        if (mushroomCount >= inputData.getMinNoOfEachIngredientPerSlice()
                && tomatoCount >= inputData.getMinNoOfEachIngredientPerSlice()) {
            for (int rowIndex = startRowIndex; rowIndex < startRowIndex + stripSize; rowIndex++) {
                pizza[rowIndex][colIndex] = true;
            }
            return true;
        }
        return false;
    }
}
