//package be.stivizu.projects.hashcode.algorithm;
//
//import be.stivizu.projects.hashcode.model.Orientation;
//import be.stivizu.projects.hashcode.model.Photo;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//public class AlgorithmT1 extends Algorithm
//{
//    @Override
//    protected void doAlgorithm()
//    {
//        Set<Photo> photos = inputData.photos;
//
//        List<Photo> horizontal = getOrientedPhotos(photos, Orientation.HORIZONTAL);
//        List<Photo> vertical = getOrientedPhotos(photos, Orientation.VERTICAL);
//
//
//    }
//
//    private List<Photo> getOrientedPhotos(Set<Photo> photos, Orientation orientation)
//    {
//        List<Photo> orientedPhotos = new ArrayList<Photo>();
//
//        for(Photo photo : photos)
//        {
//            if(photo.getOrientation() == orientation)
//            {
//                orientedPhotos.add(photo);
//            }
//        }
//
//        return orientedPhotos;
//    }
//
//    private int getMatchFactor(Photo photo1, Photo photo2)
//    {
//        int matchScore = 0;
//        int photo1Ex = photo1.getTags().size();
//        int photo2Ex = photo2.getTags().size();
//
//        for(String tagPhoto1 : photo1.getTags())
//        {
//            for(String tagPhoto2 : photo2.getTags())
//            {
//                if(tagPhoto1.equals(tagPhoto2))
//                {
//                    matchScore++;
//                    photo1Ex--;
//                    photo2Ex--;
//
//                    break;
//                }
//            }
//        }
//
//        return Math.min(Math.min(photo1Ex, photo2Ex), matchScore);
//    }
//}
