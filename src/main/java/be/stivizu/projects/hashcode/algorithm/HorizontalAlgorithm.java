package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.ArrayList;
import java.util.List;

import static be.stivizu.projects.hashcode.util.ThomasUtil.getInterestFactor;

public class HorizontalAlgorithm extends Algorithm
{
    @Override
    protected void doAlgorithm()
    {
        List<Slide> slides = new ArrayList<>();

        System.out.println(">>>>> Iterations");

        if (inputData.photosSortHorizontalNoTags.size() >= 3) {

            int noOfIterations = 100;
            for (int iterationCount = 0; iterationCount < noOfIterations; iterationCount++) {

                System.out.println(">>>>> Iteration " + iterationCount);

                Photo photoToAnalyze = inputData.photosSortHorizontalNoTags.get(inputData.photosSortHorizontalNoTags.size() - 2);

                Photo photoAfter = inputData.photosSortHorizontalNoTags.get(inputData.photosSortHorizontalNoTags.size() - 1);
                Photo photoBefore = inputData.photosSortHorizontalNoTags.get(inputData.photosSortHorizontalNoTags.size() - 3);

                int bestMinScore = getInterestFactor(photoAfter, photoToAnalyze)
                        + getInterestFactor(photoBefore, photoToAnalyze);
                int indexToPlace = -1;

                for (int photoIndex = 0; photoIndex < inputData.photosSortHorizontalNoTags.size() - 4; photoIndex++) {

                    Photo photoToCompare1 = inputData.photosSortHorizontalNoTags.get(photoIndex);
                    Photo photoToCompare2 = inputData.photosSortHorizontalNoTags.get(photoIndex + 1);

                    int interestFactor = getInterestFactor(photoToCompare1, photoToAnalyze)
                            + getInterestFactor(photoToCompare2, photoToAnalyze);

                    if (interestFactor > bestMinScore) {
                        bestMinScore = interestFactor;
                        indexToPlace = photoIndex + 1;
                    }

                }

                if (indexToPlace != -1) {
                    inputData.photosSortHorizontalNoTags.add(indexToPlace, photoToAnalyze);
                    inputData.photosSortHorizontalNoTags.remove(inputData.photosSortHorizontalNoTags.size() - 2);
                }

            }

        }

        System.out.println(">>>>> Setting horizontal slices");

        for (Photo photo : inputData.photosSortHorizontalNoTags) {
            slides.add(new Slide(photo.getId()));
        }

        System.out.println(">>>>> Setting vertical slices");

        for (int photoIndex = 0; photoIndex < inputData.photosSortHVerticalNoTags.size() - 2; photoIndex += 2) {
            Photo photo1 = inputData.photosSortHVerticalNoTags.get(photoIndex);
            Photo photo2 = inputData.photosSortHVerticalNoTags.get(photoIndex + 1);
            slides.add(new Slide(photo1.getId(), photo2.getId()));
        }

        System.out.println(">>>>> Doing output");

        outputData.setNumberOfSlides(slides.size());
        outputData.setSlides(slides);
    }
}
