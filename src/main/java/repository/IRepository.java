package repository;

public interface IRepository<Contract> {
     void add(Contract item);
     Contract getId(int id);
     void remove(int id);
}
