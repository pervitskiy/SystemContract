package repository;

import typeOfContracts.Contract;

public class MyRepository implements IRepository<Contract>{
    private final int INIT_SIZE = 20;
    private final int CUT_RATE = 5;
    private Contract[] array;
    private int count_element;

    public MyRepository(){
        array = new Contract[INIT_SIZE];
        count_element = 0;
    }

    @Override
    public void add(Contract item) {
        if(count_element == array.length-1)
            resize(array.length*2);
        array[count_element++] = item;
    }

    @Override
    public Contract getId(int id) {
        for(int i=0; i<count_element; i++){
            if (array[i].getId() == id)
                return array[i];
        }
        return null;
    }

    @Override
    public void remove(int id) {
        for (int i=0; i<count_element; i++)
            if (array[i].getId() == id) {
                for (int j = i; j < count_element; j++)
                    array[i] = array[i + 1];
                break;
            }

        array[count_element] = null;
        count_element--;
        if (array.length > INIT_SIZE && count_element < array.length / CUT_RATE)
            resize(array.length/2);
    }

    private void resize(int newLength) {
        Contract[] newArray = new Contract[newLength];
        System.arraycopy(array, 0, newArray, 0, count_element);
        array = newArray;
    }
}
