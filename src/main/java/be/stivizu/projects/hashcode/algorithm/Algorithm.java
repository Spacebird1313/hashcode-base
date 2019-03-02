package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.InputData;
import be.stivizu.projects.hashcode.model.OutputData;
import be.stivizu.projects.hashcode.util.ScoreUtil;

import java.nio.file.Path;

/**
 * TODO[REQUIRED]: Extend this class with custom algorithm classes. They will automatically be run with the proper
 *  data initialized. The application will run all the provided algorithms and take the one with the best output for
 *  each data-set to generate the optimal output.
 */
public abstract class Algorithm implements Runnable{

    protected InputData inputData;

    protected OutputData outputData = new OutputData();

    protected Path fileName;

    @Override
    public void run() {
        solve();
    }

    public void solve() {
        doAlgorithm();
        logScore();
    }

    /*
        TODO[REQUIRED]: Abstract algorithm that needs to be overriden to implement an algorithm. Insert output data
            in the outputData object.
     */

    protected abstract void doAlgorithm();

    private void logScore() {
        System.out.println(this.getClass().getSimpleName() + ": " + ScoreUtil.calculateScore(inputData, outputData));
    }

    public void setInputData(InputData inputData) {
        this.inputData = inputData;
    }

    public InputData getInputData() {
        return inputData;
    }

    public OutputData getOutputData() {
        return outputData;
    }

    public Path getFileName() {
        return fileName;
    }

    public void setFileName(Path fileName) {
        this.fileName = fileName;
    }
}
