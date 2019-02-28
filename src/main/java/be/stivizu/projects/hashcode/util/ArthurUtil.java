package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.*;
import java.util.stream.Collectors;

public class ArthurUtil {

    public static List<Photo> getWithOrientation(List<Photo> photos, Orientation o) {
        return photos.parallelStream().filter(p -> p.getOrientation().equals(o)).collect(Collectors.toList());
    }

    public static Optional<Photo> getFirstWithOrientation(List<Photo> photos, Orientation o) {
        return photos.stream().filter(p -> p.getOrientation().equals(o)).findFirst();
    }

    public static List<Slide> makeHorizontalSlides(List<Photo> horizontalPhotos) {
        return horizontalPhotos.stream().map(p -> new Slide(Collections.singletonList(p), p.getId()))
                .collect(Collectors.toList());
    }

    public static Slide findBestMatch(Slide slideA, List<Slide> slides) {
        Slide slide = slides.stream().map(s -> new Object[]{s, s.getInterestFactor(slideA)}).max(
                Comparator.comparing(s -> ((Integer) s[1]))).map(s -> ((Slide) s[0])).get();
        return slide;
    }

    public static long getNumCommonTags(Slide a, Slide b) {
        Set<String> allTagsA = a.getAllTags();
        Set<String> allTagsB = b.getAllTags();
        return allTagsA.stream().filter(allTagsB::contains).count();
    }


    public static long getNumNotInOther(Slide a, Slide b) {
        Set<String> allTagsA = a.getAllTags();
        Set<String> allTagsB = b.getAllTags();
        return allTagsA.stream().filter(tags -> !allTagsB.contains(tags)).count();
    }
}
