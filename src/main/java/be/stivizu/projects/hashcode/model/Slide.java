package be.stivizu.projects.hashcode.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Slide {

    List<Integer> photoIds;
    List<Photo> photos;


    public Slide(int... ids) {
        this.photoIds = new ArrayList<>();
        for (Integer integer : ids) {
            photoIds.add(integer);
        }
    }

    public Slide(List<Photo> photos, int... ids) {
        this.photos = photos;
        this.photoIds = new ArrayList<>();
        for (Integer integer : ids) {
            photoIds.add(integer);
        }
    }

    public int getInterestFactor(Slide other){
        Set<String> tags = getAllTags();
        Set<String> otherTags = getAllTags(other);

        int matchScore = 0;
        int slide1Ex = tags.size();
        int slide2Ex = otherTags.size();

        for(String tagThis : tags)
        {
            for(String tagOther : otherTags)
            {
                if(tagThis.equals(tagOther))
                {
                    matchScore++;
                    slide1Ex--;
                    slide2Ex--;

                    break;
                }
            }
        }

        return Math.min(Math.min(slide1Ex, slide2Ex), matchScore);
    }

    public Set<String> getAllTags(Slide slide){
        Set<String> tags = new HashSet<>();
        for (Photo photo : slide.photos) {
            tags.addAll(photo.getTags());
        }
        return tags;
    }

    public Set<String> getAllTags(){
        return getAllTags(this);
    }
}
