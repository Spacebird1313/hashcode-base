package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Orientation;
import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;
import be.stivizu.projects.hashcode.util.ArthurUtil;
import be.stivizu.projects.hashcode.util.VerticalPhotoCombinerUtil;

import java.util.*;
import java.util.stream.Collectors;

import static be.stivizu.projects.hashcode.util.ArthurUtil.*;

public class ArthurAlgo extends Algorithm {

    @Override protected void doAlgorithm() {

        List<Photo> photos = new ArrayList<>(inputData.photos);

        List<Photo> horizontal = getWithOrientation(photos, Orientation.HORIZONTAL);
        List<Photo> vertical = getWithOrientation(photos, Orientation.VERTICAL);

        int numSlides = 0;

        //Do algo
        //Create slides from horizontals
        List<Slide> horizontalSlides = makeHorizontalSlides(horizontal);
//        List<Slide> verticalSlides = new ArrayList<>();

        //Random vertical slides
        ListIterator<Photo> verticalIter = vertical.listIterator();

        Set<Slide> verticalSlides = VerticalPhotoCombinerUtil.combineBig(new HashSet<>(vertical));

        //Combine slides
        ArrayList<Slide> allSlides = new ArrayList<>();
        allSlides.addAll(horizontalSlides);
        allSlides.addAll(verticalSlides);

        //Map Slides
        ArrayList<Slide> finalSlides = new ArrayList<>();

        //Startup
        Slide startSlide = allSlides.get(0);
        allSlides.remove(startSlide);
        Slide bestStartMatch = findBestMatch(startSlide, allSlides);
        allSlides.remove(bestStartMatch);
        finalSlides.addAll(Arrays.asList(startSlide, bestStartMatch));
        numSlides+=2;

        Slide currentSlide = bestStartMatch;
        while (!allSlides.isEmpty()){
            System.out.println("Finding Match" + allSlides.size());
            Slide bestMatch = findBestMatch(currentSlide, allSlides);
            allSlides.remove(bestMatch);
            finalSlides.add(bestMatch);
            numSlides++;
        }

        outputData.setNumberOfSlides(numSlides);
        outputData.setSlides(finalSlides);

    }
}
