package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.*;
import java.util.stream.Collectors;

public class ArthurUtil {

    public static Map<String, Integer> tags = new HashMap<>();
    private static int i = 0;

    public static int encodeTag(String tag){
        if(!tags.containsKey(tag)) {
            tags.put(tag, i);
            return i++;
        }
        return tags.get(tag);
    }

    public static Photo encodeTags(Photo photo){
        int[] ints = photo.getTags().stream().map(ArthurUtil::encodeTag).mapToInt(value -> value).toArray();
        photo.setTagsInt(ints);
        return photo;
    }

    public static int getInterestFactor(int[] tags1, int[] tags2)
    {
        int union = 0;

        for (int tagThis : tags1) {
            for (int tagOther : tags2) {
                if(tagThis == tagOther)
                {
                    union++;
                    break;
                }
            }
        }

        int photo1Ex = tags1.length - union;
        int photo2Ex = tags2.length - union;

        return Math.min(Math.min(photo1Ex, photo2Ex), union);
    }

    public static int getInterestFactor(Slide slide, Slide other)
    {
        return getInterestFactor(slide.allTagsInt, other.allTagsInt);
    }

    public static List<Photo> getWithOrientation(List<Photo> photos, Orientation o) {
        return photos.parallelStream().filter(p -> p.getOrientation().equals(o)).collect(Collectors.toList());
    }

    public static List<Slide> makeHorizontalSlides(List<Photo> horizontalPhotos) {
        return horizontalPhotos.stream().map(p -> new Slide(Collections.singletonList(p), p.getId()))
                .collect(Collectors.toList());
    }

    public static Slide findBestMatch(Slide slideA, List<Slide> slides) {
        Slide slide = slides.parallelStream().map(s -> new Object[]{s, s.getInterestFactorOptimised(slideA)}).max(
                Comparator.comparing(s -> ((Integer) s[1]))).map(s -> ((Slide) s[0])).get();
        return slide;
    }


}
