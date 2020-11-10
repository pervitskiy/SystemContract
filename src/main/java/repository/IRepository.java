package repository;

import customers.Person;
import typeOfContracts.Contract;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface IRepository<Contract> {
     void add(Contract item);
     Contract getById(int id);
     void removeById(int id);
     IRepository<typeOfContracts.Contract> findBy(Predicate<Contract> predicate);
     void sort(Comparator<Contract> comparator);
     Contract getContact(int i);
     List<Person> getListPerson();
     int size();
}
