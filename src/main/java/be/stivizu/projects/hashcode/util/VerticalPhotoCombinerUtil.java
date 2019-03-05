package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalPhotoCombinerUtil {

    public static Set<Slide> combineBigStream(List<Photo> photos){
        List<Photo> collect = photos.stream().sorted(Comparator.comparingInt(p -> p.getTags().size())).collect(Collectors.toList());
        Set<Slide> slides = new HashSet<>();
        //Combine in sets of 2
        for (int i = 0; i < collect.size(); i += 2) {
            slides.add(new Slide(collect.get(i), collect.get(i+1)));
        }
        return slides;
    }

    public static Set<Slide> combineBig(Set<Photo> photos) {
        Set<Slide> slides = new HashSet<>();
        List<Photo> photos1 = new ArrayList<>(photos);
        ListIterator listIterator = photos1.listIterator();
        while(listIterator.hasNext()) {
            int amountOfTags = 0;
            List<Photo> comboPhoto = new ArrayList<>();
            Photo winner = null;
            Photo photo = (Photo) listIterator.next();
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
            comboPhoto.add(winner);
            Slide slide = new Slide(comboPhoto, photo.getId(), winner.getId());
            slides.add(slide);
            winner = null;
            listIterator = photos1.listIterator();
        }
        return slides;
    }

    public Set<Slide> combineBigEven(Set<Photo> photos) {
        Set<Slide> slides = new HashSet<>();
        List<Photo> photos1 = new ArrayList<>(photos);
        ListIterator listIterator = photos1.listIterator();
        while(listIterator.hasNext()) {
            int amountOfTags = 0;
            int amountOfTagsEven = 0;
            List<Photo> comboPhoto = new ArrayList<>();
            Photo winner = null;
            Photo winnerEven = null;
            Photo photo = (Photo) listIterator.next();
            comboPhoto.add(photo);
            listIterator.remove();
            while (listIterator.hasNext()) {
                Photo photo1 = (Photo) listIterator.next();
                Set<String> union = new HashSet<>(photo.getTags());
                union.addAll(photo1.getTags());
                if(union.size() > amountOfTagsEven && union.size()%2==0) {
                    winnerEven = photo1;
                    amountOfTagsEven = union.size();
                }
                if(union.size() > amountOfTags) {
                    winner = photo1;
                    amountOfTags = union.size();
                }
            }
            Slide slide = null;
            if(winnerEven == null) {
                photos1.remove(winner);
                comboPhoto.add(winner);
                slide = new Slide(comboPhoto, photo.getId(), winner.getId());
                slides.add(slide);
            } else {
                photos1.remove(winnerEven);
                comboPhoto.add(winnerEven);
                slide = new Slide(comboPhoto, photo.getId(), winnerEven.getId());
                slides.add(slide);
            }
            winner = null;
            winnerEven = null;
            listIterator = photos1.listIterator();
        }
        return slides;
    }

}
