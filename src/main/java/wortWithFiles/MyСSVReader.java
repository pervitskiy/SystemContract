package wortWithFiles;

import au.com.bytecode.opencsv.CSVReader;
import customers.Person;
import repository.IRepository;
import repository.MyRepository;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.MobileContract;
import typeOfContracts.Rate.Rate;
import typeOfContracts.TVContract;

import java.io.*;
import java.time.LocalDate;

public class MyСSVReader {
    private IRepository<Contract> repository = new MyRepository();

    public IRepository<Contract> createContactByFile(String file_name) throws IOException {
        InputStream is = MyСSVReader.class.getResourceAsStream(file_name);
        CSVReader reader = new CSVReader(new InputStreamReader(is), ';' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            passing(nextLine);
        }
        return repository;
    }

    private void passing(String[] nextLine) {
        String[] startDate = nextLine[3].split("-");
        String[] endDate = nextLine[4].split("-");
        int id = Integer.parseInt(nextLine[1].trim());
        int number = Integer.parseInt(nextLine[2].trim());
        LocalDate start = LocalDate.of(Integer.parseInt(startDate[0].trim()), Integer.parseInt(startDate[1].trim()), Integer.parseInt(startDate[2].trim()));
        LocalDate end = LocalDate.of(Integer.parseInt(endDate[0].trim()), Integer.parseInt(endDate[1].trim()), Integer.parseInt(endDate[2].trim()));
        Person person = createNewPeron(nextLine);
        switch (nextLine[0].trim().toLowerCase()){
            case "internetcontract": createInternetContract(nextLine, id, number, start, end, person);break;
            case "tvcontract" : createTVContract(nextLine, id, number, start, end, person);break;
            case  "mobilecontract" : createMobileContract(nextLine, id, number, start, end, person);break;
        }

    }

    private Person createNewPeron(String[] nextLine) {
        int id  = Integer.parseInt(nextLine[5].trim());
        String first_name = nextLine[6].trim();
        String last_name = nextLine[7].trim();
        String middle_name = nextLine[8].trim();
        String[] birt = nextLine[9].split("-");
        LocalDate bithday = LocalDate.of(Integer.parseInt(birt[0].trim()), Integer.parseInt(birt[1].trim()), Integer.parseInt(birt[2].trim()));
        Person.Gender gender;
        switch (nextLine[10].trim().toLowerCase()){
            case "woman" : gender = Person.Gender.WOMAN;break;
            default:       gender=Person.Gender.MALE;break;
        }
        int passportSeries = Integer.parseInt(nextLine[11].trim());
        int passportNumber = Integer.parseInt(nextLine[12].trim());
        Person person = new Person(id, first_name, last_name, middle_name, bithday, gender, passportSeries, passportNumber);

        for (Person person_from_repository :repository.getListPerson()){
            if (person.equals(person_from_repository)) {
                person = null;
                return person_from_repository;
            }
        }
        return person;
    }

    private void createMobileContract(String[] nextLine, int id, int number, LocalDate startDate, LocalDate endDate, Person person) {
        String[] rate = nextLine[13].split(",");
        int numberOfMinutes = Integer.parseInt(rate[0].trim());
        int numberOfSms = Integer.parseInt(rate[1].trim());
        int numberOfGb = Integer.parseInt(rate[2].trim());
        Contract contract = new MobileContract(id, number, startDate, endDate, person, new Rate(numberOfMinutes, numberOfSms, numberOfGb));
        this.repository.add(contract);
    }

    private void createTVContract(String[] nextLine, int id, int number, LocalDate startDate, LocalDate endDate, Person person) {
        TVContract.ChannelPacage channelPacage;
        switch (nextLine[13].trim().toLowerCase()){
            case "cinema": channelPacage = TVContract.ChannelPacage.CINEMA; break;
            case "sport": channelPacage = TVContract.ChannelPacage.SPORT; break;
            default:
                channelPacage = TVContract.ChannelPacage.STANDARD; break;
        }
        Contract contract = new TVContract(id, number, startDate, endDate, person, channelPacage);
        this.repository.add(contract);
    }

    private void createInternetContract(String[] nextLine, int id, int number, LocalDate startDate, LocalDate endDate, Person person) {
        Contract contract = new InternetContract(id, number, startDate, endDate, person, Double.parseDouble(nextLine[13].trim()));
        this.repository.add(contract);
    }
}