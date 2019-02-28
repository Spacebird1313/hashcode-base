package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.*;

public class VerticalPhotoCombinerUtil {

    public Set<Slide> combineBig(Set<Photo> photos) {
        Set<Slide> slides = new HashSet<>();
        List<Photo> photos1 = new ArrayList<>(photos);
        ListIterator listIterator = photos1.listIterator();
        while(listIterator.hasNext()) {
            int amountOfTags = 0;
            List<Integer> comboId = new ArrayList<>();
            List<Photo> comboPhoto = new ArrayList<>();
            Photo winner = null;
            Photo photo = (Photo) listIterator.next();
            comboId.add(photo.getId());
            comboPhoto.add(photo);
            listIterator.remove();
            while (listIterator.hasNext()) {
                Photo photo1 = (Photo) listIterator.next();
                Set<String> union = new HashSet<>(photo.getTags());
                union.addAll(photo1.getTags());
                if(union.size() > amountOfTags) {
                    winner = photo1;
                    amountOfTags = union.size();
                }
            }
            photos1.remove(winner);
            comboId.add(winner.getId());
            comboPhoto.add(winner);
            Slide slide = new Slide(comboPhoto, comboId.get(0), comboId.get(1));
            slides.add(slide);
            winner = null;
            listIterator = photos1.listIterator();
        }
        return slides;
    }
}
