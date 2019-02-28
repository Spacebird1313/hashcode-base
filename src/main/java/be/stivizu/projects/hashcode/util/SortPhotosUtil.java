package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;

import java.util.Set;

public class SortPhotosUtil {
    protected Set<Photo> photosV;
    protected Set<Photo> photosH;

    public Set<Photo> getPhotosV() {
        return photosV;
    }

    public Set<Photo> getPhotosH() {
        return photosH;
    }

    public void sortPhotos(Set<Photo> photos) {
        for(Photo photo : photos) {
            if(Orientation.HORIZONTAL.equals(photo.getOrientation())) {
                photosH.add(photo);
            } else {
                photosV.add(photo);
            }
        }
    }
}
