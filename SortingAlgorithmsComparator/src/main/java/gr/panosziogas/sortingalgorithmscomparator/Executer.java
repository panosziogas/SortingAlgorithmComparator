package gr.panosziogas.sortingalgorithmscomparator;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.BUBBLE_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.INSERTION_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.MERGE_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.SELECTION_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.algorithmsResults;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.AlgorithmsInterface;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.BubbleSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.InsertionSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.MergeSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.SelectionSort;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author panosziogas
 */
public class Executer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> availableAlgorithms = AlgorithmsUtil.availableAlgorithms;
        System.out.println("################################################################################");
        System.out.println("###################### Sorting Algorithms Comparator v 1.0 #####################");
        System.out.println("################################################################################");
        System.out.println("");
        System.out.println("Running availabe algorithms " + availableAlgorithms.toString());
        System.out.println("");
        System.out.println("################################################################################");
        System.out.println("");
        for (String algorithm : availableAlgorithms) {
            AlgorithmsInterface resolver = getAlgorithmResolver(algorithm);
            runSortAlgorithm(resolver, algorithm);
        }
        analyzeData();

    }

    private static AlgorithmsInterface getAlgorithmResolver(final String algorithm) {
        switch (algorithm) {
            case BUBBLE_SORT:
                return new BubbleSort();
            case MERGE_SORT:
                return new MergeSort();
            case INSERTION_SORT:
                return new InsertionSort();
            case SELECTION_SORT:
                return new SelectionSort();
            default:
                throw new UnsupportedOperationException(algorithm + " algorithm provided is not supported yet");
        }
    }

    private static void runSortAlgorithm(final AlgorithmsInterface resolver, final String algorithName) {
        Integer[] unsortedArray = AlgorithmsUtil.getUnsortedArray();
        long startTime = System.currentTimeMillis();
        Integer[] sortedArray = resolver.sortArray(unsortedArray);
        long endTime = System.currentTimeMillis();
        Double executeSeconds = (double) (endTime - startTime) / (1000);
        algorithmsResults.put(algorithName, executeSeconds);
    }

    private static void analyzeData() {
        System.out.println("Generating results...");
        System.out.println("");

        Iterator it1 = algorithmsResults.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair = (Map.Entry) it1.next();
            System.out.println(pair.getKey() + " = " + pair.getValue() + " seconds to execute");
        }
        System.out.println("");
        System.out.println("################################################################################");
        System.out.println("");
        System.out.println("Evaluating results...");
        System.out.println("");

        Iterator it2 = algorithmsResults.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pair = (Map.Entry) it2.next();
            Iterator it3 = algorithmsResults.entrySet().iterator();
            while (it3.hasNext()) {
                Map.Entry internalPair = (Map.Entry) it3.next();
                if (!pair.getKey().equals(internalPair.getKey())) {
                    String ratio;
                    Double current = (Double) pair.getValue();
                    Double toCheck = (Double) internalPair.getValue();
                    Double larger;
                    Double smaller;
                    if (current > toCheck) {
                        ratio = "slower";
                        larger = current;
                        smaller = toCheck;
                    } else {
                        ratio = "faster";
                        larger = toCheck;
                        smaller = current;
                    }
                    Double secondsComparison = (larger - smaller);
                    System.out.println(pair.getKey() + " algorithm is " + ratio + " than " + internalPair.getKey() + " algorithm for " + String.format("%.2f", secondsComparison) + " seconds");
                    System.out.println("");
                }
            }
        }
        System.out.println("################################################################################");
        System.out.println("");
        Map<String, Double> mapResults = AlgorithmsUtil.sortResults(algorithmsResults, true);
        Map.Entry<String, Double> entry = mapResults.entrySet().iterator().next();
        System.out.println("Fastest algorithm found is " + entry.getKey() + " running in " + (double) entry.getValue() + " seconds");
        System.out.println("");
        System.out.println("################################################################################");
        System.out.println("End of process");
        System.out.println("################################################################################");

    }

}
