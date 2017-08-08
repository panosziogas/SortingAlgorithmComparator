package gr.panosziogas.sortingalgorithmscomparator.algorithms;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.RADIX_SORT;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pziogas
 */
public class RadixSort implements AlgorithmsInterface {

    @Override
    public Integer[] sortArray(Integer[] unsortedArray) {
        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        // sort
        boolean flag = false;
        int tmp = -1, divisor = 1;
        while (!flag) {
            flag = true;
            // split input between lists
            for (Integer i : unsortedArray) {
                tmp = i / divisor;
                buckets[tmp % 10].add(i);
                if (flag && tmp > 0) {
                    flag = false;
                }
            }
            // empty lists into input array
            int a = 0;
            for (int b = 0; b < 10; b++) {
                for (Integer i : buckets[b]) {
                    unsortedArray[a++] = i;
                }
                buckets[b].clear();
            }
            // move to next digit
            divisor *= 10;
        }
        return unsortedArray;

    }

    @Override
    public String getAlgorithm() {
        return RADIX_SORT;
    }

}
