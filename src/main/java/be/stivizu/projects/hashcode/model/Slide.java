package be.stivizu.projects.hashcode.model;

import java.util.ArrayList;
import java.util.List;

public class Slide {

    List<Integer> photoIds;

    public Slide(int... ids) {
        this.photoIds = new ArrayList<>();
        for (Integer integer : ids) {
            photoIds.add(integer);
        }
    }

}
