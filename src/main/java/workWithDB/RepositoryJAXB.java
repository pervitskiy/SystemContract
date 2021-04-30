package workWithDB;

import repository.IRepository;
import repository.MyRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class RepositoryJAXB implements WorkWithDatabase {

    private static final String PATH_TO_XML_FILE = "repository.xml";
    private final JAXBContext jc;


    public RepositoryJAXB() throws JAXBException {
        this.jc = JAXBContext.newInstance(MyRepository.class);
    }

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
