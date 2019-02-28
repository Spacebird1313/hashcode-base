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

        System.out.println(">>>>> Iterations horizontal");

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

                for (int photoIndex = 1; photoIndex < inputData.photosSortHorizontalNoTags.size() - 4; photoIndex++) {

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

        System.out.println(">>>>> Iterations vertical");

        if (inputData.photosSortHVerticalNoTags.size() >= 3) {

            int noOfIterations = 100;
            for (int iterationCount = 0; iterationCount < noOfIterations; iterationCount++) {

                System.out.println(">>>>> Iteration " + iterationCount);

                Photo photoToAnalyze1 = inputData.photosSortHVerticalNoTags.get(inputData.photosSortHVerticalNoTags.size() - 3);
                Photo photoToAnalyze2 = inputData.photosSortHVerticalNoTags.get(inputData.photosSortHVerticalNoTags.size() - 4);

                Photo photoAfter1 = inputData.photosSortHVerticalNoTags.get(inputData.photosSortHVerticalNoTags.size() - 2);
                Photo photoAfter2 = inputData.photosSortHVerticalNoTags.get(inputData.photosSortHVerticalNoTags.size() - 1);

                Photo photoBefore1 = inputData.photosSortHVerticalNoTags.get(inputData.photosSortHVerticalNoTags.size() - 5);
                Photo photoBefore2 = inputData.photosSortHVerticalNoTags.get(inputData.photosSortHVerticalNoTags.size() - 6);

                Set<String> photoToAnalyzeTags = photoToAnalyze1.getTags();
                photoToAnalyzeTags.addAll(photoToAnalyze2.getTags());

                Set<String> photoAfterTags = photoAfter1.getTags();
                photoAfterTags.addAll(photoAfter2.getTags());

                Set<String> photoBeforeTags = photoBefore1.getTags();
                photoBeforeTags.addAll(photoBefore2.getTags());

                int bestMinScore = getInterestFactor(photoAfterTags, photoToAnalyzeTags)
                        + getInterestFactor(photoBeforeTags, photoToAnalyzeTags);
                int indexToPlace = -1;

                for (int photoIndex = 3; photoIndex < inputData.photosSortHVerticalNoTags.size() - 6; photoIndex += 2) {

                    Photo photoToCompare1a = inputData.photosSortHVerticalNoTags.get(photoIndex);
                    Photo photoToCompare1b = inputData.photosSortHVerticalNoTags.get(photoIndex + 1);

                    Photo photoToCompare2a = inputData.photosSortHVerticalNoTags.get(photoIndex + 2);
                    Photo photoToCompare2b = inputData.photosSortHVerticalNoTags.get(photoIndex + 3);

                    Set<String> photoToCompare1Tags = photoToCompare1a.getTags();
                    photoToCompare1Tags.addAll(photoToCompare1b.getTags());

                    Set<String> photoToCompare2Tags = photoToCompare2a.getTags();
                    photoToCompare2Tags.addAll(photoToCompare2b.getTags());

                    int interestFactor = getInterestFactor(photoToCompare1Tags, photoToAnalyzeTags)
                            + getInterestFactor(photoToCompare2Tags, photoToAnalyzeTags);

                    if (interestFactor > bestMinScore) {
                        bestMinScore = interestFactor;
                        indexToPlace = photoIndex + 1;
                    }

                }

                if (indexToPlace != -1) {
                    inputData.photosSortHVerticalNoTags.add(indexToPlace, photoToAnalyze1);
                    inputData.photosSortHVerticalNoTags.add(indexToPlace + 1, photoToAnalyze2);
                    inputData.photosSortHVerticalNoTags.remove(inputData.photosSortHVerticalNoTags.size() - 4);
                    inputData.photosSortHVerticalNoTags.remove(inputData.photosSortHVerticalNoTags.size() - 3);
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

    public int getInterestFactor(Set<String> tags1, Set<String> tags2)
    {

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
