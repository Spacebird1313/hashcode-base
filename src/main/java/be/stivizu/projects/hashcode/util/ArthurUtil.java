package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ArthurUtil {

    public static List<Photo> getWithOrientation(List<Photo> photos, Orientation o){
        return photos.parallelStream().filter(p -> p.getOrientation().equals(o)).collect(Collectors.toList());
    }

    public static Optional<Photo> getFirstWithOrientation(List<Photo> photos, Orientation o){
        return photos.stream().filter(p -> p.getOrientation().equals(o)).findFirst();
    }

    public static Slide[] findBestMatch(List<Slide> slides){
        Slide[] bestMatch = new Slide[2];
        int bestMatchInt = -1;

        for (Slide slideA : slides) {
            for (Slide slideB : slides) {
                int interestFactor = slideA.getInterestFactor(slideB);
                if(bestMatchInt < interestFactor){
                    bestMatch = new Slide[]{slideA, slideB};
                    bestMatchInt = interestFactor;
                }
            }
        }
        return bestMatch;
    }

    public static long getNumCommonTags(Slide a, Slide b){
        Set<String> allTagsA = a.getAllTags();
        Set<String> allTagsB = b.getAllTags();
        return allTagsA.stream().filter(allTagsB::contains).count();
    }


    public static long getNumNotInOther(Slide a, Slide b){
        Set<String> allTagsA = a.getAllTags();
        Set<String> allTagsB = b.getAllTags();
        return allTagsA.stream().filter(tags -> !allTagsB.contains(tags)).count();
    }
}
