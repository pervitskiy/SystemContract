package repository;

public class MyRepository<T> implements IRepository<T>{
    private final int INIT_SIZE = 20;
    private final int CUT_RATE = 5;
    private Object[] array = new Object[INIT_SIZE];
    private int count_element = 0;

    @Override
    public void add(T item) {
        if(count_element == array.length-1)
            resize(array.length*2);
        array[count_element++] = item;
    }

    @Override
    public T getId(int id) {
        return (T) array[id];
    }

    @Override
    public void remove(int id) {
        for (int i = id; i<count_element; i++)
            array[i] = array[i+1];
        array[count_element] = null;
        count_element--;
        if (array.length > INIT_SIZE && count_element < array.length / CUT_RATE)
            resize(array.length/2);
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, count_element);
        array = newArray;
    }
}
