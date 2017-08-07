package gr.panosziogas.sortingalgorithmscomparator.algorithms;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.COMB_SORT;

/**
 *
 * @author pziogas
 */
public class CombSort implements AlgorithmsInterface {

    @Override
    public Integer[] sortArray(Integer[] unsortedArray) {
        int n = unsortedArray.length;

        // initialize gap
        int gap = n;

        // Initialize swapped as true to make sure that
        // loop runs
        boolean swapped = true;

        // Keep running while gap is more than 1 and last
        // iteration caused a swap
        while (gap != 1 || swapped == true) {
            // Find next gap
            gap = getNextGap(gap);

            // Initialize swapped as false so that we can
            // check if swap happened or not
            swapped = false;

            // Compare all elements with current gap
            for (int i = 0; i < n - gap; i++) {
                if (unsortedArray[i] > unsortedArray[i + gap]) {
                    // Swap arr[i] and arr[i+gap]
                    int temp = unsortedArray[i];
                    unsortedArray[i] = unsortedArray[i + gap];
                    unsortedArray[i + gap] = temp;

                    // Set swapped
                    swapped = true;
                }
            }
        }
        return unsortedArray;
    }

    private int getNextGap(int gap) {
        // Shrink gap by Shrink factor
        gap = (gap * 10) / 13;
        if (gap < 1) {
            return 1;
        }
        return gap;
    }

    @Override
    public String getAlgorithm() {
        return COMB_SORT;
    }

}
