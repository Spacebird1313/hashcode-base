package be.stivizu.projects.hashcode.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputData {

    /*
        TODO[REQUIRED]: Add fields (primitive types or custom POJO's) to this class for output generation or validation.
     */

    int numberOfSlides;

    List<Slide> slides;

    public OutputData() {
        slides = new ArrayList<>();
    }

    public void setNumberOfSlides(int numberOfSlides) {
        this.numberOfSlides = numberOfSlides;
    }

    public void setSlides(List<Slide> slides) {
        this.slides = slides;
    }

    /*
        TODO[REQUIRED]: Generate a list of String values that translates the solution to the required output format.
     */

    public List<String> generateOutput() {
        List<String> output = new ArrayList<>();
        output.add(String.valueOf(numberOfSlides));
        slides.forEach(slide -> output.add(slide.photoIds.stream().map(String::valueOf).collect(Collectors.joining(" "))));
        return output;
    }

}
