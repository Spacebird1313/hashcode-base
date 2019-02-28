package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Photo;
import be.stivizu.projects.hashcode.model.Slide;

import java.util.ArrayList;
import java.util.List;

public class TestAlgorithm extends Algorithm {

    @Override
    protected void doAlgorithm() {
        List<Slide> slides = new ArrayList<>();

        for (Photo photo : inputData.horPhotos) {
            slides.add(new Slide(photo.getId()));
        }

        for (int photoIndex = 0; photoIndex < inputData.verPhotos.size() - 2; photoIndex += 2) {
            Photo photo1 = inputData.verPhotos.get(photoIndex);
            Photo photo2 = inputData.verPhotos.get(photoIndex + 1);
            slides.add(new Slide(photo1.getId(), photo2.getId()));
        }

        outputData.setNumberOfSlides(slides.size());
        outputData.setSlides(slides);
    }

}
