package gr.panosziogas.sortingalgorithmscomparator;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.BUBBLE_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.MERGE_SORT;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.AlgorithmsInterface;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.BubbleSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.MergeSort;
import java.util.Arrays;

/**
 *
 * @author panosziogas
 */
public class Executer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for(String algorithm : AlgorithmsUtil.availableAlgorithms){
            AlgorithmsInterface resolver = getAlgorithmResolver(algorithm);
            runSortAlgorithm(resolver , algorithm);
        }
       
    }

    private static AlgorithmsInterface getAlgorithmResolver(final String algorithm) {
        switch (algorithm) {
            case BUBBLE_SORT:                
                return new BubbleSort();
            case MERGE_SORT:                
                return new MergeSort();
            default:
                throw new UnsupportedOperationException(algorithm + " algorithm provided is not supported yet");
        }
    }

    private static void runSortAlgorithm(final AlgorithmsInterface resolver,final String algorithName) {
        Integer[] unsortedArray = AlgorithmsUtil.getUnsortedArray();     
        long startTime = System.currentTimeMillis();
        Integer[] sortedArray = resolver.sortArray(unsortedArray);
        long endTime = System.currentTimeMillis();       
        System.out.println(algorithName + " algorithm took " + (double) (endTime - startTime) / (1000) + " seconds to execute");
    }

}
