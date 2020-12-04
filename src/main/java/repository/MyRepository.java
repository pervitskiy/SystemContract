package repository;

import customers.Person;
import sorter.BubbleSort;
import sorter.ISorter;
import sorter.QuickSort;
import typeOfContracts.Contract;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class MyRepository, that can add or delete Contract, get a contract by ID
 * @author Pervitskiy_d_e
 */
public class MyRepository implements IRepository<Contract>{

    /**
     * variable for initializing the array
     */
    private final int INIT_SIZE = 20;
    private final int CUT_RATE = 5;
    private Contract[] array_contract;
    private int count_element;
    private final ISorter<Contract> sorter = new QuickSort<>();


    /**
     * Constructor - creating a new object
     */
    public MyRepository(){
        array_contract = new Contract[INIT_SIZE];
        count_element = 0;
    }
    /**
     * Method for adding a new contract.
     * @param item - contract,
     */
    @Override
    public void add(Contract item) {
        if(count_element == array_contract.length-1)
            resize(array_contract.length*2); // create a new array
        array_contract[count_element++] = item;
    }

    /**
     * Method to get contract by ID
     * @param id - id Contract
     * @return Contact
     */
    @Override
    public Contract getById(int id) {
        for (int i=0; i<size(); i++){
            if (array_contract[i].getId() == id)
                return array_contract[i];
        }
        return null;
    }


    /**
     * Method deleting contract by id
     * @param id - id Contract
     */
    @Override
    public void removeById(int id) {
        for (int i=0; i<count_element; i++)
            if (array_contract[i].getId() == id) {
                for (int j = i; j < count_element; j++)
                    array_contract[i] = array_contract[i + 1];
                break;
            }
        array_contract[count_element] = null;
        count_element--;
        if (array_contract.length > INIT_SIZE && count_element < array_contract.length / CUT_RATE)
            resize(array_contract.length/2);
    }

    /**
     * Method expanding repository
     * @param newLength - new length of the repository
     */
    private void resize(int newLength) {
        Contract[] newArray = new Contract[newLength];
        System.arraycopy(array_contract, 0, newArray, 0, count_element);
        array_contract = newArray;
    }

    /**
     *
     * Sorting contracts according to a given criterion.
     * @param comparator - comparator for contract comparison
     */
    @Override
    public void sort(Comparator<Contract> comparator) {
        sorter.sort(array_contract, comparator, 0, size() - 1);
    }

    /**
     * Search for a contract by a given criterion
     * @param predicate - search criteria
     * @return new IRepository<Contract>
     */
    public IRepository<Contract> findBy(Predicate<Contract> predicate) {
        IRepository<Contract> result = new MyRepository();
        for (int i=0; i<size(); i++){
            if (predicate.test(array_contract[i]))
                result.add(array_contract[i]);
        }
        return result;
    }

    /**
     * @return the number of elements in the array
     */
    public int size() {
        return count_element;
    }


    /**
     * @param i - array number
     * @return Contract
     */
    public Contract getContact(int i){
        return array_contract[i];
    }

    @Override
    public ArrayList<Person> getListSetPerson() {
        List<Person> list = new ArrayList<>();
        for (int i=0; i<size(); i++){
            list.add(array_contract[i].getOwner());
        }
        return new ArrayList<Person>(new HashSet<Person>(list));
    }

    @Override
    public String toString() {
        return "MyRepository{" +
                "count_element=" + count_element +
                '}';
    }
}
