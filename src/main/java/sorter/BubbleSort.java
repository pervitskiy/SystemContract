package sorter;

import java.util.Comparator;

public class BubbleSort<T> implements ISorter<T> {
    @Override
    public void sort(T[] array, Comparator<T> comparator, int start, int end) {
        boolean sorted = false;
        while (!sorted){
            sorted = true;
            for (int i = start; i<end; i++){
                if(comparator.compare(array[i], array[i+1]) > 0) {
                    T tmp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = tmp;
                    sorted = false;
                }
            }
        }
    }
}
