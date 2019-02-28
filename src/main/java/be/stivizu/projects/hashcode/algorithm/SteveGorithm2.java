package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.ArrayList;
import java.util.List;

public class SteveGorithm2 extends Algorithm {

    @Override
    protected void doAlgorithm() {
        List<Slide> slides = new ArrayList<>();

        List<Photo> customSortHor = new ArrayList<>();

        Photo firstPhoto = inputData.photosSortNoTags.get(0);
        customSortHor.add(firstPhoto);
        inputData.photosSortNoTags.remove(firstPhoto);

        while (!inputData.photosSortNoTags.isEmpty()) {

            Photo refPhoto = customSortHor.get(customSortHor.size() - 1);

            int bestScore = Integer.MAX_VALUE;
            Photo bestPhoto = null;

            for (int photoIndex = 0; photoIndex < inputData.photosSortNoTags.size(); photoIndex++) {

                Photo photoToAnalyze = inputData.photosSortNoTags.get(photoIndex);

                int numberOfMatchingTags = getNumberOfMatchingTags(photoToAnalyze, refPhoto);
                int exclusiveTagsP1 = getNumberOfExclusiveTags(photoToAnalyze, refPhoto);
                int exclusiveTagsP2 = getNumberOfExclusiveTags(refPhoto, photoToAnalyze);

                int min = getSmallestValue(numberOfMatchingTags, exclusiveTagsP1, exclusiveTagsP2);

                if (min == 0) {
                    bestScore = min;
                    bestPhoto = photoToAnalyze;
                    break;
                }

                if (min < bestScore) {
                    bestScore = min;
                    bestPhoto = photoToAnalyze;
                }

            }

            customSortHor.add(bestPhoto);
            inputData.photosSortNoTags.remove(bestPhoto);

        }

        for (Photo photo : customSortHor) {
            slides.add(new Slide(photo.getId()));
        }
//        for (int photoId = 0; photoId < inputData.photosVerSortNoTags.size() - 2; photoId += 2) {
//            Photo photo1 = inputData.photosVerSortNoTags.get(photoId);
//            Photo photo2 = inputData.photosVerSortNoTags.get(photoId + 1);
//            slides.add(new Slide(photo1.getId(), photo2.getId()));
//        }

        outputData.setNumberOfSlides(slides.size());
        outputData.setSlides(slides);
    }

    private int getNumberOfMatchingTags(Photo photo1, Photo photo2) {
        int matchingTags = 0;
        for (String p1Tag : photo1.getTags()) {
            for (String p2Tag : photo2.getTags()) {
                if (p1Tag.equals(p2Tag)) {
                    matchingTags++;
                }
            }
        }
        return matchingTags;
    }

    private int getNumberOfExclusiveTags(Photo photo1, Photo photo2) {
        int exclusiveCount = 0;
        for (String p1Tag : photo1.getTags()) {
            boolean p2HasTagFromP1 = false;
            for (String p2Tag : photo2.getTags()) {
                if (p1Tag.equals(p2Tag)) {
                    p2HasTagFromP1 = true;
                    break;
                }
            }
            if (p2HasTagFromP1) {
                exclusiveCount++;
            }
        }
        return exclusiveCount;
    }

    private int getSmallestValue(int value1, int value2, int value3) {
        int smallest = value1 < value2 ? value1 : value2;
        return smallest < value3 ? smallest : value3;
    }

}
