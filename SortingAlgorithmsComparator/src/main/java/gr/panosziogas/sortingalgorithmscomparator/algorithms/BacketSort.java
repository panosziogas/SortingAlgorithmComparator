package gr.panosziogas.sortingalgorithmscomparator.algorithms;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.BACKET_SORT;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pziogas
 */
public class BacketSort implements AlgorithmsInterface {

    private static final int DEFAULT_BUCKET_SIZE = 5;

    @Override
    public Integer[] sortArray(Integer[] unsortedArray) {
        // Determine minimum and maximum values
        Integer minValue = unsortedArray[0];
        Integer maxValue = unsortedArray[0];
        for (int i = 1; i < unsortedArray.length; i++) {
            if (unsortedArray[i] < minValue) {
                minValue = unsortedArray[i];
            } else if (unsortedArray[i] > maxValue) {
                maxValue = unsortedArray[i];
            }
        }

        // Initialise buckets
        int bucketCount = (maxValue - minValue) / DEFAULT_BUCKET_SIZE + 1;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // Distribute input array values into buckets
        for (int i = 0; i < unsortedArray.length; i++) {
            buckets.get((unsortedArray[i] - minValue) / DEFAULT_BUCKET_SIZE).add(unsortedArray[i]);
        }

        // Sort buckets and place back into input array
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
//            InsertionSort.sort(bucketArray);
            for (int j = 0; j < bucketArray.length; j++) {
                unsortedArray[currentIndex++] = bucketArray[j];
            }
        }
        return unsortedArray;
    }

    @Override
    public String getAlgorithm() {
        return BACKET_SORT;
    }

}
