package gr.panosziogas.sortingalgorithmscomparator;

import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.BACKET_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.BUBBLE_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.COMB_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.COUNTING_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.EMPTY_LINE;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.HEAP_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.INSERTION_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.MERGE_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.QUICK_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.RADIX_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.SELECTION_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.SEPERATOR;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.SHELL_SORT;
import static gr.panosziogas.sortingalgorithmscomparator.AlgorithmsUtil.algorithmsResults;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.AlgorithmsInterface;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.BacketSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.BubbleSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.CombSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.CountingSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.InsertionSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.MergeSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.QuickSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.RadixSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.SelectionSort;
import gr.panosziogas.sortingalgorithmscomparator.algorithms.ShellSort;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    public static List<Integer> dataList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> availableAlgorithms = AlgorithmsUtil.availableAlgorithms;
        System.out.println(SEPERATOR);
        System.out.println("###################### Sorting Algorithms Comparator v 1.0 #####################");
        System.out.println(SEPERATOR);
        System.out.println(EMPTY_LINE);
        String filePathToWrite = null;
        if (args.length != 0) {
            String fileLocation = args[0];
            System.out.println("Reading file with data to analyze on location: " + fileLocation);
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        dataList.add(Integer.parseInt(line));
                    } catch (NumberFormatException e) {
                    }
                }
            }
            System.out.println(EMPTY_LINE);
            System.out.println("Parsed " + dataList.size() + " lines successfully");
            System.out.println(EMPTY_LINE);
            System.out.println(SEPERATOR);
            System.out.println(EMPTY_LINE);
            filePathToWrite = fileLocation.substring(0, fileLocation.lastIndexOf("/"));
        }
        System.out.println("Ready to execute availabe algorithms:");
        System.out.println(EMPTY_LINE);
        for (String algo : availableAlgorithms) {
            System.out.println(algo);
        }
        System.out.println(EMPTY_LINE);
        System.out.println(SEPERATOR);
        System.out.println(EMPTY_LINE);
        boolean useEmebededData = true;
        if (!dataList.isEmpty()) {
            useEmebededData = false;
        }
        int count = 1;
        for (String algorithm : availableAlgorithms) {
            AlgorithmsInterface resolver = getAlgorithmResolver(algorithm);
            runSortAlgorithm(resolver, algorithm, useEmebededData, count, filePathToWrite);
            count++;
        }
        System.out.println(EMPTY_LINE);
        System.out.println(SEPERATOR);
        System.out.println(EMPTY_LINE);
        analyzeData(filePathToWrite);
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
            case HEAP_SORT:
                return new SelectionSort();
            case QUICK_SORT:
                return new QuickSort();
            case SHELL_SORT:
                return new ShellSort();
            case COMB_SORT:
                return new CombSort();
            case COUNTING_SORT:
                return new CountingSort();
            case BACKET_SORT:
                return new BacketSort();
            case RADIX_SORT:
                return new RadixSort();
            default:
                throw new UnsupportedOperationException(algorithm + " algorithm provided is not supported yet");
        }
    }

    private static void runSortAlgorithm(final AlgorithmsInterface resolver, final String algorithName, final boolean useEmebededData,
            final int count, final String filePathToWrite) {
        System.out.println(count + ") Executing algorithm " + resolver.getAlgorithm());
        Integer[] unsortedArray;
        if (useEmebededData) {
            unsortedArray = AlgorithmsUtil.getUnsortedArray();
        } else {
            unsortedArray = AlgorithmsUtil.convertListToArray(dataList);
        }
        long startTime = System.currentTimeMillis();
        Integer[] sortedArray = resolver.sortArray(unsortedArray);
        long endTime = System.currentTimeMillis();
        Double executeSeconds = (double) (endTime - startTime) / (1000);
        algorithmsResults.put(algorithName, executeSeconds);
        if (filePathToWrite != null) {
            AlgorithmsUtil.writeSortingResultToFile(sortedArray, filePathToWrite.concat("/" + algorithName.concat("sorted.txt")));
        }

    }

    private static void analyzeData(final String filePathToWrite) {
        System.out.println("Generating results...");
        System.out.println(EMPTY_LINE);

        Iterator it1 = algorithmsResults.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair = (Map.Entry) it1.next();
            System.out.println(pair.getKey() + " = " + pair.getValue() + " seconds to execute");
        }
        System.out.println(EMPTY_LINE);
        System.out.println(SEPERATOR);
        System.out.println(EMPTY_LINE);
        System.out.println("Evaluating results...");
        System.out.println(EMPTY_LINE);

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
                    System.out.println(EMPTY_LINE);
                }
            }
        }
        System.out.println(SEPERATOR);
        System.out.println(EMPTY_LINE);
        System.out.println("Algorithms ranking by execution time:");
        System.out.println(EMPTY_LINE);
        Map<String, Double> mapResultsFast = AlgorithmsUtil.sortResults(algorithmsResults, true);
        Iterator rankIterator = mapResultsFast.entrySet().iterator();
        List<String> rankResults = new ArrayList<>();
        rankResults.add("Ranking results: " + new Date());
        int rankCounter = 1;
        while (rankIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) rankIterator.next();
            String rankResult = rankCounter + ") " + pair.getKey() + " in " + pair.getValue() + " seconds";
            System.out.println(rankResult);
            rankResults.add(rankResult);
            rankCounter++;
        }
        rankResults.add(EMPTY_LINE);
        if (filePathToWrite != null) {
            String fullPath = filePathToWrite.concat("/Ranking_Results.txt");
            System.out.println(EMPTY_LINE);
            System.out.println(SEPERATOR);
            System.out.println(EMPTY_LINE);
            System.out.println("Exporting ranking results to: ".concat(fullPath));
            AlgorithmsUtil.writeSortingRankinInFile(rankResults, fullPath);
        }
        System.out.println(EMPTY_LINE);
        System.out.println(SEPERATOR);
        System.out.println("End of comparisons");
        System.out.println(SEPERATOR);
    }

}
