package gr.panosziogas.sortingalgorithmscomparator.algorithms;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.MERGE_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.SELECTION_SORT;

/**
 *
 * @author panosziogas
 */
public class SelectionSort implements AlgorithmsInterface {

    @Override
    public Integer[] sortArray(Integer[] unsortedArray) {
        for (int i = 0; i < unsortedArray.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < unsortedArray.length; j++) {
                if (unsortedArray[j] < unsortedArray[index]) {
                    index = j;
                }
            }

            int smallerNumber = unsortedArray[index];
            unsortedArray[index] = unsortedArray[i];
            unsortedArray[i] = smallerNumber;
        }
        return unsortedArray;
    }

    @Override
    public String getAlgorithm() {
        return SELECTION_SORT;
    }

}
