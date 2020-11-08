package sorter;

import java.util.Comparator;

public interface ISorter<T> {
     /**
      * Sort Ascending
      *
      * @param array - the array to sort
      * @param comparator - comparison criterion
      * @param start - start index of the array
      * @param end - end index of the array
      */
     void sort(T[] array, Comparator<T> comparator, int start, int end);

     /**
      * Sort Ascending
      *
      * @param array - the array to sort
      * @param comparator - comparison criterion
      */
     default void sort(T[] array, Comparator<T> comparator){
          if (array.length > 1)
               sort(array, comparator, 0, array.length-1);
     }
}
