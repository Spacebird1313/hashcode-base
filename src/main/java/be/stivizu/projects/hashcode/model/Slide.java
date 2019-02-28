package be.stivizu.projects.hashcode.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Slide {

    public List<Integer> photoIds;
    public List<Photo> photos;

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
//        getAllTags()
        return 0;
    }

    public Set<String> getAllTags(){
        Set<String> tags = new HashSet<>();
        for (Photo photo : photos) {
            tags.addAll(photo.getTags());
        }
        return tags;
    }
}
