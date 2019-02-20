package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.InputData;
import be.stivizu.projects.hashcode.model.OutputData;
import be.stivizu.projects.hashcode.model.Slice;

public class ScoreUtil {

    /*
        TODO[OPTIONAL]: provide logic to approximate / calculate the score for algorithm output, so the application
            can automatically choose the optimal algorithm for each data set.
            If multiple algorithms are provided, this becomes required.
     */
    public static long calculateScore(final InputData inputData, final OutputData outputData) {
        return outputData.getSlices().stream()
                .mapToLong(ScoreUtil::calculateScore)
                .sum();
    }

    private static long calculateScore(final Slice slice) {
        return (slice.getEndRow() - slice.getStartRow() + 1) * (slice.getEndCol() - slice.getStartCol() + 1);
    }

}
