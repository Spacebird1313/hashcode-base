package be.stivizu.projects.hashcode.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InputData {

    /*
        TODO[REQUIRED]: Add fields (primitive types or custom POJO's) to this class for all input data you want made
            available in the algorithm(s).
     */

    public List<Photo> photosList = new ArrayList<>();

    public List<Photo> photosSortNoTags = new ArrayList<>();

    public List<Photo> photosHorSortNoTags = new ArrayList<>();

    public List<Photo> photosVerSortNoTags = new ArrayList<>();

    public InputData(final List<String> fileData) {
        int numberOfPhotos = Integer.parseInt(fileData.get(0));
        for (int i = 1; i <= numberOfPhotos; i++) {
            String[] dataLine = fileData.get(i).split(" ");
            String orientation = dataLine[0];
            Photo photo = new Photo(i - 1, orientation);
            for (int tagIndex = 2; tagIndex < dataLine.length; tagIndex++) {
                photo.addTag(dataLine[tagIndex]);
            }
            photosList.add(photo);
        }
        photosSortNoTags = photosList.stream()
                .sorted(Comparator.comparing(photo -> photo.getTags().size()))
                .collect(Collectors.toList());
        for (Photo photo : photosSortNoTags) {
            if (photo.getOrientation() == Orientation.HORIZONTAL) {
                photosHorSortNoTags.add(photo);
            } else {
                photosVerSortNoTags.add(photo);
            }
        }
    }

}
