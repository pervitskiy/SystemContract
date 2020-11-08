package sorter;

import java.util.Comparator;

public class QuickSort<T> implements ISorter<T> {

    /**
     * Sort Ascending method QuickSort
     *
     * @param array - the array to sort
     * @param comparator - comparison criterion
     * @param start - start index of the array
     * @param end - end index of the array
     */
    @Override
    public void sort(T[] array, Comparator<T> comparator, int start, int end) {
        if (start < end) {
        /* pi is partitioning index, arr[pi] is now
           at right place */
            int pi = partition(array, comparator, start, end);
            sort(array, comparator, start, pi - 1);  // Before pi
            sort(array, comparator, pi + 1, end); // After pi
        }
    }

    private int partition(T arr[],Comparator<T> comparator, int low, int high)
    {
        T pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (comparator.compare(arr[j],pivot) < 0)
            {
                i++;

                // swap arr[i] and arr[j]
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        T temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

}
