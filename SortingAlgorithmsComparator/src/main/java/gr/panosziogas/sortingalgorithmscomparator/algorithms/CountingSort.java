package gr.panosziogas.sortingalgorithmscomparator.algorithms;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.COUNTING_SORT;

/**
 *
 * @author pziogas
 */
public class CountingSort implements AlgorithmsInterface {

    @Override
    public Integer[] sortArray(Integer[] unsortedArray) {
        Integer n = unsortedArray.length;
        int min = 0, max = 0;
        for (int i = 1; i < n; i++) {
            if (unsortedArray[i] > max) {
                max = unsortedArray[i];
            }
            if (unsortedArray[i] < min) {
                min = unsortedArray[i];
            }
        }
        int range = max - min + 1;
        int[] count = new int[range];
        //counts frequencies for each element
        for (int i = 0; i < n; i++) {
            count[unsortedArray[i] - min]++;
        }
        // getting positions in final array
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        //copy to output array, preserving order of inputs with equal keys
        int j = 0;
        for (int i = 0; i < range; i++) {
            while (j < count[i]) {
                unsortedArray[j++] = i + min;
            }
        }
        return unsortedArray;
    }

    @Override
    public String getAlgorithm() {
        return COUNTING_SORT;
    }

}
