package gr.panosziogas.sortingalgorithmscomparator.algorithms;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.SHELL_SORT;

/**
 *
 * @author pziogas
 */
public class ShellSort implements AlgorithmsInterface {

    @Override
    public Integer[] sortArray(Integer[] unsortedArray) {
        int inner, outer;
        int temp;

        int h = 1;
        while (h <= unsortedArray.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (outer = h; outer < unsortedArray.length; outer++) {
                temp = unsortedArray[outer];
                inner = outer;

                while (inner > h - 1 && unsortedArray[inner - h] >= temp) {
                    unsortedArray[inner] = unsortedArray[inner - h];
                    inner -= h;
                }
                unsortedArray[inner] = temp;
            }
            h = (h - 1) / 3;
        }
        return unsortedArray;
    }

    @Override
    public String getAlgorithm() {
        return SHELL_SORT;
    }

}
