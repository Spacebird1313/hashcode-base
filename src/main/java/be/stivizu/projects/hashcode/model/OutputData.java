package be.stivizu.projects.hashcode.model;

import java.util.ArrayList;
import java.util.List;

public class OutputData {

    /*
        TODO[REQUIRED]: Add fields (primitive types or custom POJO's) to this class for output generation or validation.
     */

    private List<Slice> slices;

    public OutputData() {}

    public List<Slice> getSlices() {
        return slices;
    }

    public void setSlices(List<Slice> slices) {
        this.slices = slices;
    }

    /*
        TODO[REQUIRED]: Generate a list of String values that translates the solution to the required output format.
     */

    public List<String> generateOutput() {
        final List<String> output = new ArrayList<>();
        output.add(String.valueOf(slices.size()));
        slices.forEach(slice -> output.add(slice.toString()));
        return output;
    }

}
