package wortWithFiles;

import customers.Person;
import junit.framework.TestCase;
import org.junit.Assert;
import repository.IRepository;
import repository.MyRepository;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import wortWithFiles.MyСSVReader;

import java.io.IOException;
import java.time.LocalDate;

public class MyСSVReaderTest extends TestCase {

    public void testCreateContactByFile() throws IOException {
        IRepository<Contract> repository_by_file = new MyСSVReader().createContactByFile("/data.csv");
        Assert.assertEquals(repository_by_file.getContact(0).getId(), 1);
        Assert.assertEquals(repository_by_file.getContact(1).getId(), 2);
        Assert.assertEquals(repository_by_file.getContact(2).getId(), 3);

        Assert.assertEquals(repository_by_file.getContact(0).getOwner().getId(), 1);
        Assert.assertEquals(repository_by_file.getContact(1).getOwner().getId(), 1);
        Assert.assertEquals(repository_by_file.getContact(2).getOwner().getId(), 2);
    }
}