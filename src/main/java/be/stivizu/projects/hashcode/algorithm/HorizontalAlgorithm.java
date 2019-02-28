package be.stivizu.projects.hashcode.algorithm;

import be.stivizu.projects.hashcode.model.Photo;

import java.util.Set;

public class HorizontalAlgorithm extends Algorithm
{
    @Override
    protected void doAlgorithm()
    {
        int[][] norm = new int[inputData.photos.size()][inputData.photos.size()];

        /*System.out.println("START NORM");
        for(int row = 0; row < inputData.photos.size(); row++)
        {
            for(int col = row + 1; col < inputData.photos.size(); col++)
            {
                norm[row][col] = getInterestFactor(inputData.photosList.get(row), inputData.photosList.get(col));
            }
        }*/

        System.out.println("END NORM");
    }

    public int getInterestFactor(Photo photo1, Photo photo2)
    {
        Set<String> tags1 = photo1.getTags();
        Set<String> tags2 = photo2.getTags();

        int union = 0;
        int photo1Ex = tags1.size();
        int photo2Ex = tags2.size();

        for(String tagThis : tags1)
        {
            for(String tagOther : tags2)
            {
                if(tagThis.equals(tagOther))
                {
                    union++;
                    photo1Ex--;
                    photo2Ex--;

                    break;
                }
            }
        }

        return Math.min(Math.min(photo1Ex, photo2Ex), union);
    }
}
