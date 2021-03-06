package repository;

import customers.Person;
import junit.framework.TestCase;
import org.junit.Assert;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.MobileContract;
import typeOfContracts.Rate.Rate;
import typeOfContracts.TVContract;

import java.time.LocalDate;

public class MyRepositoryTest extends TestCase {
    Person person1 = new Person(1, "Dima", "Per", "EB", LocalDate.of(2000, 10, 26), Person.Gender.MALE, 2014, 800457);
    Person person2 = new Person(2, "Max", "Lis", "HJ", LocalDate.of(2002, 11, 26), Person.Gender.MALE, 2014, 800123);
    Person person3 = new Person(3, "Danil", "Petrov", "EB", LocalDate.of(2000, 11, 13), Person.Gender.MALE, 2014, 803148);

    IRepository<Contract> repository = new MyRepository();
    Contract contract1 = new InternetContract(1,1, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person1, 100);
    Contract contract2 = new InternetContract(2,2, LocalDate.of(2010, 2, 2),LocalDate.of(2011, 12, 01), person1, 111);
    Contract contract3 = new InternetContract(3,3, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person2, 110);
    Contract contract4 = new TVContract(4,4, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person2, TVContract.ChannelPackage.CINEMA);
    Contract contract5 = new MobileContract(6,5, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person3, new Rate(120, 100, 8 ));

    public void testAdd() {
        Assert.assertEquals(null, repository.getById(1));
        repository.add(contract1);
        Assert.assertEquals(contract1, repository.getById(1));
    }

    public void testGetId() {
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract5);
        Assert.assertEquals(contract1, repository.getById(1));
        Assert.assertEquals(contract5, repository.getById(6));
    }

    public void testRemove() {
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract5);
        repository.add(contract4);
        repository.add(contract3);
        Assert.assertEquals(contract1, repository.getById(1));
        Assert.assertEquals(contract5, repository.getById(6));
        repository.removeById(1);
        Assert.assertEquals(null, repository.getById(1));
        Assert.assertEquals(contract4, repository.getById(4));
        repository.removeById(4);
        Assert.assertEquals(null, repository.getById(4));
    }

    public void testSort() {
        repository.add(contract5);
        repository.add(contract2);
        repository.add(contract1);
        repository.add(contract4);
        repository.add(contract3);

        System.out.println();
        repository.sort((contract1, contract2) -> {
            if (contract1.getId() == contract2.getId()) return 0;
            return contract1.getId() > contract2.getId() ? 1 : -1;
        });

        Assert.assertEquals(repository.getContact(0), contract1);
        Assert.assertEquals(repository.getContact(1), contract2);
        Assert.assertEquals(repository.getContact(2), contract3);
        Assert.assertEquals(repository.getContact(3), contract4);
        Assert.assertEquals(repository.getContact(4), contract5);

    }

    public void testFindBy() {
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract5);
        repository.add(contract4);
        repository.add(contract3);
        IRepository<Contract> result_find_id = repository.findBy((c) -> c.getId() == contract1.getId());
        Assert.assertEquals(contract1, result_find_id.getContact(0));

        IRepository<Contract> result_find_person = repository.findBy((c) -> c.getOwner().getAge() == 20);
        Assert.assertEquals(contract1, result_find_person.getContact(0));
    }

}