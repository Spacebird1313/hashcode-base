package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Photo;

import java.util.List;

public class SortPhotosUtil {
    protected List<Photo> photosV;
    protected List<Photo> photosH;

    public List<Photo> getPhotosV() {
        return photosV;
    }

    public List<Photo> getPhotosH() {
        return photosH;
    }

    public void sortPhotos(List<Photo> photos) {
        for(Photo photo : photos) {
            if("H".equals(photo.getOrientation())) {
                photosH.add(photo);
            } else {
                photosV.add(photo);
            }
        }
    }
}
