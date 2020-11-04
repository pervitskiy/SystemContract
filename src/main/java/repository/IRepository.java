package repository;

public interface IRepository<T> {
     void add(T item);
     T getId(int id);
     void remove(int id);
}
