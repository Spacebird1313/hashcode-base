package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Photo;

import java.util.Set;

public class ThomasUtil
{
    public static int getInterestFactor(Set<String> tags1, Set<String> tags2)
    {
        int union = 0;

        for(String tagThis : tags1)
        {
            for(String tagOther : tags2)
            {
                if(tagThis.equals(tagOther))
                {
                    union++;

                    break;
                }
            }
        }

        int photo1Ex = tags1.size() - union;
        int photo2Ex = tags2.size() - union;

        return Math.min(Math.min(photo1Ex, photo2Ex), union);
    }

    public static int getInterestFactor(Photo photo1, Photo photo2)
    {
        return getInterestFactor(photo1.getTags(), photo2.getTags());
    }
}
