package gr.panosziogas.sortingalgorithmscomparator.algorithms;

/**
 *
 * @author panosziogas
 */
public class HeapSort implements AlgorithmsInterface{

    @Override
    public Integer[] sortArray(Integer[] unsortedArray) {
        int n = unsortedArray.length;
 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(unsortedArray, n, i);
 
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = unsortedArray[0];
            unsortedArray[0] = unsortedArray[i];
            unsortedArray[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(unsortedArray, i, 0);
        }
        return unsortedArray;
    }
    
     void heapify(Integer arr[], int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    } 
    
}
