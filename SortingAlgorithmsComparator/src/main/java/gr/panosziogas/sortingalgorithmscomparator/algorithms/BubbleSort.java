package gr.panosziogas.sortingalgorithmscomparator.algorithms;

/**
 *
 * @author panosziogas
 */
public class BubbleSort implements AlgorithmsInterface {

 @Override
 public Integer[] sortArray(Integer[] unsortedArray) {
  int n = unsortedArray.length;
  int temp = 0;
  for (int i = 0; i < n; i++) {
   for (int j = 1; j < (n - i); j++) {
    if (unsortedArray[j - 1] > unsortedArray[j]) {
     //swap the elements!
     temp = unsortedArray[j - 1];
     unsortedArray[j - 1] = unsortedArray[j];
     unsortedArray[j] = temp;
    }
   }
  }
  return unsortedArray;
 }

}