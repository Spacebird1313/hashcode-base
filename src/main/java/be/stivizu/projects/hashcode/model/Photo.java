package be.stivizu.projects.hashcode.model;

import java.util.HashSet;
import java.util.Set;

public class Photo {

    private int id;

    private Orientation orientation;

    private Set<String> tags;

    public Photo(int id, String orientation) {
        this.id = id;
        this.orientation = orientation.equals("H") ? Orientation.HORIZONTAL : Orientation.VERTICAL;
        this.tags = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

}
