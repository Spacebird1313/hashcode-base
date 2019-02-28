//package be.stivizu.projects.hashcode.algorithm;
//
//import be.stivizu.projects.hashcode.model.Photo;
//import be.stivizu.projects.hashcode.model.Slide;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SteveGorithm1 extends Algorithm {
//
//    @Override
//    protected void doAlgorithm() {
//        List<Slide> slides = new ArrayList<>();
//
//        for (Photo photo : inputData.photosHorSortNoTags) {
//            slides.add(new Slide(photo.getId()));
//        }
//        for (int photoId = 0; photoId < inputData.photosVerSortNoTags.size() - 2; photoId += 2) {
//            Photo photo1 = inputData.photosVerSortNoTags.get(photoId);
//            Photo photo2 = inputData.photosVerSortNoTags.get(photoId + 1);
//            slides.add(new Slide(photo1.getId(), photo2.getId()));
//        }
//
//        outputData.setNumberOfSlides(slides.size());
//        outputData.setSlides(slides);
//    }
//
//}
