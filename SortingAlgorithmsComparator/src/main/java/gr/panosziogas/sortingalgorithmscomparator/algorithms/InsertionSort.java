package gr.panosziogas.sortingalgorithmscomparator.algorithms;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.HEAP_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.INSERTION_SORT;

/**
 *
 * @author panosziogas
 */
public class InsertionSort implements AlgorithmsInterface {

    @Override
    public Integer[] sortArray(Integer[] unsortedArray) {
        int temp;
        for (int i = 1; i < unsortedArray.length; i++) {
            for (int j = i; j > 0; j--) {
                if (unsortedArray[j] < unsortedArray[j - 1]) {
                    temp = unsortedArray[j];
                    unsortedArray[j] = unsortedArray[j - 1];
                    unsortedArray[j - 1] = temp;
                }
            }
        }
        return unsortedArray;
    }

    @Override
    public String getAlgorithm() {
        return INSERTION_SORT;
    }

}
