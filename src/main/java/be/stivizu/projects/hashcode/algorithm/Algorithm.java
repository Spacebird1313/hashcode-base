package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.InputData;
import be.stivizu.projects.hashcode.model.OutputData;
import be.stivizu.projects.hashcode.util.ScoreUtil;

/**
 * TODO[REQUIRED]: Extend this class with custom algorithm classes. They will automatically be run with the proper
 *  data initialized. The application will run all the provided algorithms and take the one with the best output for
 *  each data-set to generate the optimal output.
 */
public abstract class Algorithm {

    protected InputData inputData;

    protected OutputData outputData = new OutputData();

    public OutputData solve(final InputData inputData) {
        this.inputData = inputData;
        doAlgorithm();
        logScore();
        return outputData;
    }

    /*
        TODO[REQUIRED]: Abstract algorithm that needs to be overriden to implement an algorithm. Insert output data
            in the outputData object.
     */

    protected abstract void doAlgorithm();

    private void logScore() {
        System.out.println(this.getClass().getSimpleName() + ": " + ScoreUtil.calculateScore(inputData, outputData));
    }

}
