package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;
import be.stivizu.projects.hashcode.util.ArthurUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import static be.stivizu.projects.hashcode.util.ArthurUtil.*;

public class ArthurAlgo extends Algorithm {

    @Override protected void doAlgorithm() {

        List<Photo> photos = new ArrayList<>(inputData.photos);

        List<Slide> slides = new ArrayList<>();

        getWithOrientation(photos, Orientation.HORIZONTAL);
        getWithOrientation(photos, Orientation.VERTICAL);

        int numSlides = 0;

        //Do algo
        ListIterator<Photo> horizontalIter = photos.listIterator();
        ListIterator<Photo> verticalIter = photos.listIterator();
        while (horizontalIter.hasNext()){
            Photo next = horizontalIter.next();
            slides.add(new Slide(next.getId()));
            numSlides++;
        }

        while (verticalIter.hasNext()){
            Photo next = verticalIter.next();
            slides.add(new Slide(next.getId(), verticalIter.next().getId()));
            numSlides++;
        }


        outputData.setNumberOfSlides(numSlides);
        outputData.setSlides(slides);

    }
}
