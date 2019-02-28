package be.stivizu.projects.hashcode;

import be.stivizu.projects.hashcode.algorithm.Algorithm;
import be.stivizu.projects.hashcode.model.InputData;
import be.stivizu.projects.hashcode.model.OutputData;
import be.stivizu.projects.hashcode.util.ScoreUtil;

import java.nio.file.Path;
import java.util.Comparator;

import static be.stivizu.projects.hashcode.util.FileUtil.*;
import static be.stivizu.projects.hashcode.util.ReflectionUtil.getClassReferencesForClassesExtendingGivenBaseClass;
import static be.stivizu.projects.hashcode.util.ValidationUtil.validateSolution;

public class Solver {

    private static final String INPUT_DATA_PATH_FROM_RESOURCES = "input";

    private static final String OUTPUT_DATA_PATH_FROM_RESOURCES = "output";

    private void solve() {
        clearResourceFolderOrCreateIt(OUTPUT_DATA_PATH_FROM_RESOURCES);
        streamFolderContents(loadResource(INPUT_DATA_PATH_FROM_RESOURCES))
                .peek(inputFile -> System.out.println(inputFile.getFileName()))
                .forEach(inputFile -> solve(inputFile.getFileName(), new InputData(readFileContents(inputFile))));
    }

    private void solve(final Path fileName, final InputData inputData) {
        doOutPut(changeFileExtension(fileName, "out"),
                 getClassReferencesForClassesExtendingGivenBaseClass(Algorithm.class).stream()
                         .map(algorithmObject -> (Algorithm) algorithmObject)
                         .map(algorithm -> algorithm.solve(inputData))
                         .filter(outputData -> validateSolution(inputData, outputData))
                         .max(Comparator.comparing(outputData -> ScoreUtil.calculateScore(inputData, outputData)))
                         .orElseThrow(() -> new RuntimeException("Was unable to deduce an optimal solution."))
        );
    }

    private void doOutPut(final Path fileName, final OutputData outputData) {
        createAndWriteToFile(loadResource(OUTPUT_DATA_PATH_FROM_RESOURCES), fileName, outputData.generateOutput());
    }

    public static void main(final String... args) {
        new Solver().solve();
    }
}
