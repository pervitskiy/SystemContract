package workWithDB;

import repository.IRepository;

public interface WorkWithDatabase {
    void save(IRepository repository);
    IRepository dump();
}
