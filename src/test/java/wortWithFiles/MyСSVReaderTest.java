package wortWithFiles;

import junit.framework.TestCase;
import org.junit.Assert;
import repository.IRepository;
import repository.MyRepository;
import typeOfContracts.Contract;

import java.io.FileReader;
import java.io.IOException;

public class MyСSVReaderTest extends TestCase {
    IRepository<Contract> repository_by_file;

    public MyСSVReaderTest() throws IOException, ParseException {
        repository_by_file = new MyRepository();
        MyСSVReader myСSVReader = new MyСSVReader(new FileReader("src/main/resources/data.csv"), repository_by_file);
        myСSVReader.createContactByFile();
    }

    public void testCreateContactByFile() {
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