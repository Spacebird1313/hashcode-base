package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static be.stivizu.projects.hashcode.util.ThomasUtil.getInterestScore;

public class HorizontalAlgorithm extends Algorithm
{
    @Override
    protected void doAlgorithm()
    {
        List<Slide> slides = new ArrayList<>();

        System.out.println(">>>>> Iterations");

        int noOfIterations = 100;
        for (int iterationCount = 0; iterationCount < noOfIterations; iterationCount++) {

            System.out.println(">>>>> Iteration " + iterationCount);

            Photo photoToAnalyze = inputData.photosSortNoTags.get(inputData.photosSortNoTags.size() - 2);

            Photo photoAfter = inputData.photosSortNoTags.get(inputData.photosSortNoTags.size() - 1);
            Photo photoBefore = inputData.photosSortNoTags.get(inputData.photosSortNoTags.size() - 3);

            int bestMinScore = getInterestScore(photoAfter, photoToAnalyze)
                    + getInterestScore(photoBefore, photoToAnalyze);
            int indexToPlace = -1;

            for (int photoIndex = 0; photoIndex < inputData.photosSortNoTags.size() - 4; photoIndex++) {

                Photo photoToCompare1 = inputData.photosSortNoTags.get(photoIndex);
                Photo photoToCompare2 = inputData.photosSortNoTags.get(photoIndex + 1);

                int interestFactor = getInterestScore(photoToCompare1, photoToAnalyze)
                        + getInterestScore(photoToCompare2, photoToAnalyze);

                if (interestFactor > bestMinScore) {
                    bestMinScore = interestFactor;
                    indexToPlace = photoIndex + 1;
                }

            }

            if (indexToPlace != -1) {
                inputData.photosSortNoTags.add(indexToPlace, photoToAnalyze);
                inputData.photosSortNoTags.remove(inputData.photosSortNoTags.size() - 2);
            }

        }

        System.out.println(">>>>> Setting slices");

        for (Photo photo : inputData.photosSortNoTags) {
            slides.add(new Slide(Collections.singletonList(photo)));
        }

        System.out.println(">>>>> Doing output");

        outputData.setNumberOfSlides(slides.size());
        outputData.setSlides(slides);
    }

}
