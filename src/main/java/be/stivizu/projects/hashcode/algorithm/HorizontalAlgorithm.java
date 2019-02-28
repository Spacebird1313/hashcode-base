package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

            int bestMinScore = getInterestFactor(photoAfter, photoToAnalyze)
                    + getInterestFactor(photoBefore, photoToAnalyze);
            int indexToPlace = -1;

            for (int photoIndex = 0; photoIndex < inputData.photosSortNoTags.size() - 4; photoIndex++) {

                Photo photoToCompare1 = inputData.photosSortNoTags.get(photoIndex);
                Photo photoToCompare2 = inputData.photosSortNoTags.get(photoIndex + 1);

                int interestFactor = getInterestFactor(photoToCompare1, photoToAnalyze)
                        + getInterestFactor(photoToCompare2, photoToAnalyze);

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
            slides.add(new Slide(photo.getId()));
        }

        System.out.println(">>>>> Doing output");

        outputData.setNumberOfSlides(slides.size());
        outputData.setSlides(slides);
    }

    public int getInterestFactor(Photo photo1, Photo photo2)
    {
        Set<String> tags1 = photo1.getTags();
        Set<String> tags2 = photo2.getTags();

        int union = 0;
        int photo1Ex = tags1.size();
        int photo2Ex = tags2.size();

        for(String tagThis : tags1)
        {
            for(String tagOther : tags2)
            {
                if(tagThis.equals(tagOther))
                {
                    union++;
                    photo1Ex--;
                    photo2Ex--;

                    break;
                }
            }
        }

        return Math.min(Math.min(photo1Ex, photo2Ex), union);
    }

}
