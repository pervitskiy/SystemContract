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
import java.sql.SQLOutput;
import java.time.LocalDate;

/**
 * A class that adds data from this file to your repository
 */
public class MyСSVReader {

    private IRepository<Contract> repository;
    private FileReader fileReader;

    public MyСSVReader(FileReader file, IRepository<Contract> repository){
        this.fileReader = file;
        this.repository = repository;
    }


    /** Create contacts by file and add them to the repository
     * @throws IOException
     */
    public  void createContactByFile() throws IOException {
        CSVReader reader = new CSVReader(fileReader, ';' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            passing(nextLine);
        }
    }

    /**
     * Parse a line and,
     * depending on the type of contract,
     * create a contract
     * @param nextLine - Line with information about the Сontract
     */
    private void passing(String[] nextLine) {
        String[] startDate = nextLine[2].split("-");
        String[] endDate = nextLine[3].split("-");
        int id = Integer.parseInt(nextLine[0].trim());
        int number = Integer.parseInt(nextLine[1].trim());
        LocalDate start = LocalDate.of(Integer.parseInt(startDate[0].trim()), Integer.parseInt(startDate[1].trim()), Integer.parseInt(startDate[2].trim()));
        LocalDate end = LocalDate.of(Integer.parseInt(endDate[0].trim()), Integer.parseInt(endDate[1].trim()), Integer.parseInt(endDate[2].trim()));
        Person person = createNewPeron(nextLine);
        switch (nextLine[12].trim().toLowerCase()){
            case "internetcontract": createInternetContract(nextLine[13], id, number, start, end, person);break;
            case "tvcontract" : createTVContract(nextLine[13], id, number, start, end, person);break;
            case  "mobilecontract" : createMobileContract(nextLine[13], id, number, start, end, person);break;
        }

    }

    /**Create a new owner if it is not in the repository
     * @param nextLine - Line with information about the Owner
     * @return Person
     */
    private Person createNewPeron(String[] nextLine) {
        int id  = Integer.parseInt(nextLine[4].trim());
        String first_name = nextLine[5].trim();
        String last_name = nextLine[6].trim();
        String middle_name = nextLine[7].trim();
        String[] birt = nextLine[8].split("-");
        LocalDate bithday = LocalDate.of(Integer.parseInt(birt[0].trim()), Integer.parseInt(birt[1].trim()), Integer.parseInt(birt[2].trim()));
        Person.Gender gender;
        switch (nextLine[9].trim().toLowerCase()){
            case "woman" : gender = Person.Gender.WOMAN;break;
            default:       gender=Person.Gender.MALE;break;
        }
        int passportSeries = Integer.parseInt(nextLine[10].trim());
        int passportNumber = Integer.parseInt(nextLine[11].trim());
        Person person = new Person(id, first_name, last_name, middle_name, bithday, gender, passportSeries, passportNumber);

        for (Person person_from_repository :repository.getListPerson()){
            if (person.equals(person_from_repository)) {
                person = null;
                return person_from_repository;
            }
        }
        return person;
    }

    /**Creating A Mobile Contract and adding to the repository
     * @param stringRate - Line with numbers Rate
     * @param id - id Contract
     * @param number - number Contract
     * @param startDate -  start date of the contract
     * @param endDate - end date of the contract
     * @param person - owner Contract
     */
    private void createMobileContract(String stringRate, int id, int number, LocalDate startDate, LocalDate endDate, Person person) {
        String[] rate = stringRate.split(",");
        int numberOfMinutes = Integer.parseInt(rate[0].trim());
        int numberOfSms = Integer.parseInt(rate[1].trim());
        int numberOfGb = Integer.parseInt(rate[2].trim());
        Contract contract = new MobileContract(id, number, startDate, endDate, person, new Rate(numberOfMinutes, numberOfSms, numberOfGb));
        this.repository.add(contract);
    }

    /**
     * Creating A ТV Contract and adding to the repository
     * @param channelPac - channel package
     * @param id - id Contract
     * @param number - number Contract
     * @param startDate -  start date of the contract
     * @param endDate - end date of the contract
     * @param person - owner Contract
     */
    private void createTVContract(String channelPac, int id, int number, LocalDate startDate, LocalDate endDate, Person person) {
        TVContract.ChannelPacage channelPackage;
        switch (channelPac.trim().toLowerCase()){
            case "cinema": channelPackage = TVContract.ChannelPacage.CINEMA; break;
            case "sport": channelPackage = TVContract.ChannelPacage.SPORT; break;
            default:
                channelPackage = TVContract.ChannelPacage.STANDARD; break;
        }
        Contract contract = new TVContract(id, number, startDate, endDate, person, channelPackage);
        this.repository.add(contract);
    }

    /**Creating A Internet Contract and adding to the repository
     *
     * @param internetSpeed - internet speed
     * @param id - id Contract
     * @param number - number Contract
     * @param startDate - start date of the contract
     * @param endDate - end date of the contract
     * @param person - owner Contract
     */
    private void createInternetContract(String internetSpeed, int id, int number, LocalDate startDate, LocalDate endDate, Person person) {
        Contract contract = new InternetContract(id, number, startDate, endDate, person, Double.parseDouble(internetSpeed.trim()));
        this.repository.add(contract);
    }
}