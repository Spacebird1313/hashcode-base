package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.InputData;
import be.stivizu.projects.hashcode.model.OutputData;
import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.ArrayList;
import java.util.List;

public class ScoreUtil {

    /*
        TODO[OPTIONAL]: provide logic to approximate / calculate the score for algorithm output, so the application
            can automatically choose the optimal algorithm for each data set.
            If multiple algorithms are provided, this becomes required.
     */

    public static long calculateScore(final InputData inputData, final OutputData outputData) {
        int score = 0;
        for (int slideIndex = 0; slideIndex < outputData.getSlides().size() - 1; slideIndex++) {
            Slide slide1 = outputData.getSlides().get(slideIndex);
            Slide slide2 = outputData.getSlides().get(slideIndex + 1);

            List<String> slide1Tags = getTagsForSlide(inputData, slide1);
            List<String> slide2Tags = getTagsForSlide(inputData, slide2);

            int commonTags = getNumberOfCommonTags(slide1Tags, slide2Tags);
            int exclusiveTagsSlide1 = getNumberOfExclusiveTags(slide1Tags, slide2Tags);
            int exclusiveTagsSlide2 = getNumberOfExclusiveTags(slide2Tags, slide1Tags);

            int min = commonTags < exclusiveTagsSlide1 ? commonTags : exclusiveTagsSlide1;
            min = min < exclusiveTagsSlide2 ? min : exclusiveTagsSlide2;

            score += min;

        }
        return score;
    }

    private static List<String> getTagsForSlide(InputData inputData, Slide slide) {
        List<String> tags = new ArrayList<>();
        for (Integer photoId : slide.photoIds) {
            Photo photo = inputData.photosList.get(photoId);
            if (photo.getId() != photoId) {
                throw new RuntimeException("incompat id");
            }
            tags.addAll(photo.getTags());
        }
        return tags;
    }

    private static int getNumberOfCommonTags(List<String> s1Tags, List<String> s2tags) {
        int commonCount = 0;
        for (String s1Tag : s1Tags) {
            for (String s2Tag : s2tags) {
                if (s1Tag.equals(s2Tag)) {
                    commonCount++;
                }
            }
        }
        return commonCount;
    }

    private static int getNumberOfExclusiveTags(List<String> s1Tags, List<String> s2tags) {
        int exclusiveCount = 0;
        for (String s1Tag : s1Tags) {
            boolean s2HasTagFromS1 = false;
            for (String s2Tag : s2tags) {
                if (s1Tag.equals(s2Tag)) {
                    s2HasTagFromS1 = true;
                    break;
                }
            }
            if (s2HasTagFromS1) {
                exclusiveCount++;
            }
        }
        return exclusiveCount;
    }

}
