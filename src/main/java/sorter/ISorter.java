package sorter;

import java.util.Comparator;

public interface ISorter<T> {
     void sort(T[] array, Comparator<T> comparator, int start, int end);
     default void sort(T[] array, Comparator<T> comparator){
          if (array.length > 1)
               sort(array, comparator, 0, array.length-1);
     }
}
