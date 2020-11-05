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

    IRepository repository = new MyRepository();
    Contract contract1 = new InternetContract(1,1, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person1, 100.1);
    Contract contract2 = new InternetContract(2,2, LocalDate.of(2010, 2, 2),LocalDate.of(2011, 12, 01), person1, 11.1);
    Contract contract3 = new InternetContract(3,3, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person2, 110.1);
    Contract contract4 = new TVContract(4,4, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person2, new String[]{"первый", "второй"});
    Contract contract5 = new MobileContract(6,5, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person3, new Rate(120, 100, 8 ));

    public void testAdd() {
        Assert.assertEquals(null, repository.getId(1));
        repository.add(contract1);
        Assert.assertEquals(contract1, repository.getId(1));
    }

    public void testGetId() {
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract5);
        Assert.assertEquals(contract1, repository.getId(1));
        Assert.assertEquals(contract5, repository.getId(6));
    }

    public void testRemove() {
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract5);
        repository.add(contract4);
        repository.add(contract3);
        Assert.assertEquals(contract1, repository.getId(1));
        Assert.assertEquals(contract5, repository.getId(6));
        repository.remove(1);
        Assert.assertEquals(null, repository.getId(1));
        Assert.assertEquals(contract4, repository.getId(4));
        repository.remove(4);
        Assert.assertEquals(null, repository.getId(4));
    }
}