package repository;

import typeOfContracts.Contract;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface IRepository<Contract> {
     void add(Contract item);
     Contract getId(int id);
     void remove(int id);
     List<Contract> findBy(Predicate<Contract> predicate);
     void sort(Comparator<Contract> comparator);
     Contract[] getContact();
}
