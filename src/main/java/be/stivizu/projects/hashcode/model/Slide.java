package be.stivizu.projects.hashcode.model;

import be.stivizu.projects.hashcode.util.ArthurUtil;
import be.stivizu.projects.hashcode.util.ThomasUtil;

import java.util.*;

public class Slide {

    public List<Integer> photoIds;
    public List<Photo> photos;

    public Set<String> allTags;
    public int[] allTagsInt;

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
        setAllTags(photos);
    }

    public Slide(Photo... photos) {
        List<Photo> photos1 = Arrays.asList(photos);
        this.photos = photos1;
        this.photoIds = new ArrayList<>();
        for (Photo photo : photos) {
            photoIds.add(photo.getId());
        }
        setAllTags(photos1);
    }

    public int getInterestFactor(Slide other) {
        return ThomasUtil.getInterestFactor(getAllTags(), other.getAllTags());
    }

    public int getInterestFactorOptimised(Slide other) {
        return ArthurUtil.getInterestFactor(this, other);
    }

    private void setAllTags(List<Photo> photos) {
        Set<String> tags = new HashSet<>();
        for (Photo photo : photos) {
            tags.addAll(photo.getTags());
        }
        allTags = tags;
        allTagsInt = tags.stream().mapToInt(i->ArthurUtil.tags.get(i)).toArray();

    }

    public Set<String> getAllTags() {
        return allTags;
    }
}
