package workDB;

import customers.Person;
import junit.framework.TestCase;
import repository.IRepository;
import repository.MyRepository;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.MobileContract;
import typeOfContracts.Rate.Rate;
import typeOfContracts.TVContract;
import workWithDB.RepositoryJAXB;
import workWithDB.RepositoryJDBC;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;

public class RepositoryJAXBTest extends TestCase {
    Person person1 = new Person(231343, "Dima", "Per", "EB", LocalDate.of(2000, 10, 26), Person.Gender.MALE, 2014, 82047);
    Person person2 = new Person(2, "Max", "Lis", "HJ", LocalDate.of(2002, 11, 26), Person.Gender.MALE, 2014, 800123);
    Person person3 = new Person(3, "Danil", "Petrov", "EB", LocalDate.of(2000, 11, 13), Person.Gender.MALE, 2014, 803148);

    IRepository<Contract> repository = new MyRepository();
    Contract contract1 = new InternetContract(11,1, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person1, 100);
    Contract contract2 = new InternetContract(2,2, LocalDate.of(2010, 2, 2),LocalDate.of(2011, 12, 01), person1, 111);
    Contract contract3 = new InternetContract(3,3, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person2, 110);
    Contract contract4 = new TVContract(4,4, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person2, TVContract.ChannelPackage.CINEMA);
    Contract contract5 = new MobileContract(6,5, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person3, new Rate(120, 100, 8 ));

    public RepositoryJAXBTest(){
        this.repository.add(contract1);
        this.repository.add(contract2);
        this.repository.add(contract3);
        this.repository.add(contract4);
        this.repository.add(contract5);
    }

    public void testSave() throws JAXBException {
        RepositoryJAXB repositoryJAXB = new RepositoryJAXB();
        repositoryJAXB.save(this.repository);
        IRepository restoreRepository = repositoryJAXB.dump();
        assertEquals(repository.size(), restoreRepository.size());
    }
}
