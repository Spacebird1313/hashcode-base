package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.*;

public class AlgorithmT1 extends Algorithm
{
    @Override
    protected void doAlgorithm()
    {

    }

    private int mergedTagSize(Photo photo1, Photo photo2)
    {
        Set<String> tags = new HashSet<String>();

        tags.addAll(photo1.getTags());
        tags.addAll(photo2.getTags());

        return tags.size();
    }
}
