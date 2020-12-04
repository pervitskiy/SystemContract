package repository;

import customers.Person;
import typeOfContracts.Contract;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public interface IRepository<Contract> {
     /**Adding  a contract to the repository
      * @param item - contract
      */
     void add(Contract item);

     /**Getting a contact by id
      * @param id - id Contract
      * @return Contact
      */
     Contract getById(int id);

     /**
      * Deleting a contact by id
      * @param id - id Contact
      */
     void removeById(int id);

     /**
      * Find contracts by criteria and create a new repository with them
      * @param predicate - search criterion
      * @return new IRepository<typeOfContracts.Contract>
      */
     IRepository<typeOfContracts.Contract> findBy(Predicate<Contract> predicate);

     /**
      *
      * Sorting contracts according to a given criterion.
      * @param comparator - comparator for contract comparison
      */
     void sort(Comparator<Contract> comparator);

     /**
      * @param i - index of the repository
      * @return Contact
      */
     Contract getContact(int i);

     /** get list of Person from the repository
      * @return ArrayList<Person>
      */
     ArrayList<Person> getListSetPerson();

     /**
      * @return number of contracts in the repository
      */
     int size();
}
