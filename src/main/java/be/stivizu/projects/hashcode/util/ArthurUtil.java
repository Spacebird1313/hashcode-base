package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;

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
}
