package be.stivizu.projects.hashcode.model;

import be.stivizu.projects.hashcode.util.ArthurUtil;

import java.util.*;

public class Slide {

    public List<Integer> photoIds;
    public List<Photo> photos;

    public Set<String> allTags;


    public Slide(List<Photo> photos, int... ids) {
        this.photos = photos;
        this.photoIds = new ArrayList<>();
        for (Integer integer : ids) {
            photoIds.add(integer);
        }
        setAllTags(photos);
    }

    public int getInterestFactor(Slide other) {
        Set<String> tags = getAllTags();
        Set<String> otherTags = other.getAllTags();

        int matchScore = 0;
        int slide1Ex = tags.size();
        int slide2Ex = otherTags.size();

        for (String tagThis : tags) {
            for (String tagOther : otherTags) {
                if (tagThis.hashCode() == tagOther.hashCode()) {
                    matchScore++;
                    break;
                }
            }
        }

        slide1Ex -= matchScore;
        slide2Ex -= matchScore;

        return Math.min(Math.min(slide1Ex, slide2Ex), matchScore);
    }

    public int getInterestFactor(Slide other, int best) {
        Set<String> tags = getAllTags();
        Set<String> otherTags = other.getAllTags();

        int matchScore = 0;
        int slide1Ex = tags.size();
        int slide2Ex = otherTags.size();

        int bestCase = slide1Ex < slide2Ex ? slide1Ex : slide2Ex;
        bestCase /= 2;

        if (bestCase > best) {
            for (String tagThis : tags) {
                for (String tagOther : otherTags) {
                    if (tagThis.equals(tagOther)) {
                        matchScore++;
                        slide1Ex--;
                        slide2Ex--;

                        break;
                    }
                }
            }


            return Math.min(Math.min(slide1Ex, slide2Ex), matchScore);
        } else {
            return 0;
        }
    }

    public int getInterestFactorStreams(Slide other) {
        long numCommonTags = ArthurUtil.getNumCommonTags(this, other);
        long notInB = ArthurUtil.getNumNotInOther(this, other);
        long notInA = ArthurUtil.getNumNotInOther(other, this);
        return Math.toIntExact(Arrays.asList(numCommonTags, notInA, notInB).stream().min(Long::compareTo).get());
    }

    private void setAllTags(List<Photo> photos) {
        Set<String> tags = new HashSet<>();
        for (Photo photo : photos) {
            tags.addAll(photo.getTags());
        }
        allTags = tags;
    }

    public Set<String> getAllTags() {
        return allTags;
    }
}
