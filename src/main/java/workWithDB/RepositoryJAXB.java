package workWithDB;

import repository.IRepository;
import repository.MyRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class for converting a repository to xml and vice versa
 */
public class RepositoryJAXB implements WorkWithDatabase {

    /**
     * The path to the file where the repository is saved in xml format
     */
    private static final String PATH_TO_XML_FILE = "repository.xml";
    private final JAXBContext jc;


    public RepositoryJAXB() throws JAXBException {
        this.jc = JAXBContext.newInstance(MyRepository.class);
    }

    /**
     * Saves the repository in xml format
     *
     * @param repository repository to save
     */
    @Override
    public void save(IRepository repository) {
        try {
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(repository, new File(PATH_TO_XML_FILE));
        }
        catch (JAXBException e) {
            System.err.println(e);
        }
    }

    /**
     * Restores repository from xml format
     *
     * @return restored repository
     */
    @Override
    public IRepository dump() {
        try {
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (IRepository) unmarshaller.unmarshal(new File(PATH_TO_XML_FILE));
        } catch (JAXBException e) {
            System.err.println(e);
        }
        return null;
    }
}
