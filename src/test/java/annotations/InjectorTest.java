package annotations;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import repository.IRepository;
import repository.MyRepository;
import typeOfContracts.Contract;
import wortWithFiles.MyСSVReader;
import wortWithFiles.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InjectorTest extends TestCase {

    IRepository<Contract> repository_by_file;

    public void testInject() throws IllegalAccessException, IOException, InstantiationException, ParseException {
        repository_by_file = new MyRepository();
        MyСSVReader myСSVReader = new MyСSVReader(new FileReader("src/main/resources/data.csv"), repository_by_file);
        Injector.inject(myСSVReader);
        myСSVReader.createContactByFile();
        Injector.inject(repository_by_file);
        assertTrue(repository_by_file.size() == 3);

    }
}