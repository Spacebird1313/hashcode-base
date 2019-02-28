package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;
import be.stivizu.projects.hashcode.util.ArthurUtil;

import java.util.*;

import static be.stivizu.projects.hashcode.util.ArthurUtil.*;

public class ArthurAlgo extends Algorithm {

    @Override protected void doAlgorithm() {

        List<Photo> photos = new ArrayList<>(inputData.photos);

        List<Slide> slides = new ArrayList<>();

        List<Photo> horizontal = getWithOrientation(photos, Orientation.HORIZONTAL);
        List<Photo> vertical = getWithOrientation(photos, Orientation.VERTICAL);

        int numSlides = 0;

        //Do algo
        ListIterator<Photo> horizontalIter = horizontal.listIterator();
        ListIterator<Photo> verticalIter = vertical.listIterator();
        while (horizontalIter.hasNext()){
            Photo next = horizontalIter.next();
            slides.add(new Slide(Collections.singletonList(next), next.getId()));
            numSlides++;
        }

        while (verticalIter.hasNext()){
            Photo next = verticalIter.next();
            Photo next2 = verticalIter.next();
            slides.add(new Slide(Arrays.asList(next, next2), next.getId(), next2.getId()));
            numSlides++;
        }


        outputData.setNumberOfSlides(numSlides);
        outputData.setSlides(slides);

    }
}
