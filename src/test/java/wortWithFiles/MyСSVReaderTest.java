package wortWithFiles;

import customers.Person;
import junit.framework.TestCase;
import org.junit.Assert;
import repository.IRepository;
import repository.MyRepository;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.TVContract;
import wortWithFiles.MyСSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class MyСSVReaderTest extends TestCase {
    IRepository<Contract> repository_by_file;

    public MyСSVReaderTest() throws IOException {
        repository_by_file = new MyRepository();
        InputStream is = MyСSVReaderTest.class.getResourceAsStream("/data.csv");
        MyСSVReader myСSVReader = new MyСSVReader(new InputStreamReader(is),repository_by_file);
        myСSVReader.createContactByFile();
    }

    public void testCreateContactByFile() throws IOException {
        //check at the index contact
        Assert.assertEquals(repository_by_file.getContact(0).getId(), 1);
        Assert.assertEquals(repository_by_file.getContact(1).getId(), 2);
        Assert.assertEquals(repository_by_file.getContact(2).getId(), 3);

        //check at the index owner
        Assert.assertEquals(repository_by_file.getContact(0).getOwner().getId(), 1);
        Assert.assertEquals(repository_by_file.getContact(1).getOwner().getId(), 1);
        Assert.assertEquals(repository_by_file.getContact(2).getOwner().getId(), 2);

    }
}