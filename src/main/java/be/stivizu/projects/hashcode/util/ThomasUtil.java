package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ThomasUtil
{
    public static int getInterestScore(Set<String> tags1, Set<String> tags2)
    {
        int union = 0;

        for(String tag1 : tags1)
        {
            for(String tag2 : tags2)
            {
                if(tag1.equals(tag2))
                {
                    union++;

                    break;
                }
            }
        }

        int tags1Ex = tags1.size() - union;
        int tags2Ex = tags2.size() - union;

        return Math.min(Math.min(tags1Ex, tags2Ex), union);
    }

    public static int getInterestScore(Photo photo1, Photo photo2)
    {
        return getInterestScore(photo1.getTags(), photo2.getTags());
    }

    public List<Photo> getOrientedPhotos(Set<Photo> photos, Orientation orientation)
    {
        List<Photo> orientedPhotos = new ArrayList<Photo>();

        for(Photo photo : photos)
        {
            if(photo.getOrientation() == orientation)
            {
                orientedPhotos.add(photo);
            }
        }

        return orientedPhotos;
    }
}
