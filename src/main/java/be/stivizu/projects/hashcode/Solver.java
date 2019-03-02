package be.stivizu.projects.hashcode;

import be.stivizu.projects.hashcode.algorithm.Algorithm;
import be.stivizu.projects.hashcode.model.InputData;
import be.stivizu.projects.hashcode.model.OutputData;
import be.stivizu.projects.hashcode.util.ScoreUtil;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static be.stivizu.projects.hashcode.util.FileUtil.*;
import static be.stivizu.projects.hashcode.util.ReflectionUtil.getClassReferencesForClassesExtendingGivenBaseClass;
import static be.stivizu.projects.hashcode.util.ValidationUtil.validateSolution;

public class Solver {

    private static final String INPUT_DATA_PATH_FROM_RESOURCES = "input";

    private static final String OUTPUT_DATA_PATH_FROM_RESOURCES = "output";

    private void solve() {
        clearResourceFolderOrCreateIt(OUTPUT_DATA_PATH_FROM_RESOURCES);
        List<Path> files = streamFolderContents(loadResource(INPUT_DATA_PATH_FROM_RESOURCES))
                .peek(inputFile -> System.out.println(inputFile.getFileName()))
                .collect(Collectors.toList());
        Map<Path, Map<Algorithm, Thread>> threadsByAlgorithm = new HashMap<>();
        //Generate threads
        for (Path file : files) {
            threadsByAlgorithm.put(file, createThreads(file.getFileName(), new InputData(readFileContents(file))));
        }
        //for each file, generate output
        threadsByAlgorithm.forEach((path,  entry) -> {
            Map<Algorithm, OutputData> outputDataMap = new HashMap<>();
            entry.forEach((algorithm, thread) -> {
                outputDataMap.put(algorithm, solve(algorithm, thread));
            });
            OutputData result = outputDataMap.entrySet().stream()
                    .max(Comparator.comparing(outputDataEntry -> ScoreUtil.calculateScore(outputDataEntry.getKey().getInputData(), outputDataEntry.getValue())))
                    .map(Map.Entry::getValue)
                    .orElseThrow(() -> new RuntimeException("Was unable to deduce an optimal solution."));
            doOutPut(changeFileExtension(path, "out"), result);
        });
    }

    private Map<Algorithm, Thread> createThreads(final Path file, final InputData inputData) {
        Map<Algorithm, Thread> threads = new HashMap<>();
        for(Algorithm algorithm : getClassReferencesForClassesExtendingGivenBaseClass(Algorithm.class)){
            Thread thread = new Thread(algorithm);
            threads.put(algorithm, thread);
            algorithm.setInputData(inputData);
            algorithm.setFileName(file.getFileName());
            System.out.println("Starting threads for " + file);
            thread.start();
        }
        return threads;
    }

    private OutputData solve(Algorithm algorithm, Thread thread){
        //Join threads
        try {
            thread.join();
            OutputData outputData = algorithm.getOutputData();
            if(validateSolution(algorithm.getInputData(), outputData)) {
                return outputData;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void doOutPut(final Path fileName, final OutputData outputData) {
        createAndWriteToFile(loadResource(OUTPUT_DATA_PATH_FROM_RESOURCES), fileName, outputData.generateOutput());
    }

    public static void main(final String... args) {
        new Solver().solve();
    }
}
