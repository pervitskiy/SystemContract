package wortWithFiles;

import au.com.bytecode.opencsv.CSVReader;
import customers.Person;
import repository.IRepository;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.MobileContract;
import typeOfContracts.Rate.Rate;
import typeOfContracts.TVContract;
import validators.Message;
import validators.Validate;
import validators.ValidationStatus;
import validators.validator.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that adds data from this file to your repository
 */
public class MyСSVReader {

    private IRepository<Contract> repository;
    private FileReader fileReader;
    private static List<Validate> validators = new ArrayList<>();

    /**
     * Adding the required validators
     */
    static {
        validators.add(new AdditionValidate());
        validators.add(new DateContractValidate());
        validators.add(new AgeOwnerValidate());
        validators.add(new FIOValidate());
        validators.add(new PassportValidate());
    }

    public MyСSVReader(FileReader file, IRepository<Contract> repository){
        this.fileReader = file;
        this.repository = repository;
    }

    /** Create contacts by file and add them to the repository
     * @throws IOException
     * @throws ParseException - if a parsing program fails.
     */
    public  void createContactByFile() throws IOException, ParseException {
        CSVReader reader = new CSVReader(fileReader, ';' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            Contract contract = passing(nextLine);
            if (doValidation(contract))
                repository.add(contract);
            else {
                System.out.println(contract + "не был добавлен в репозиторий");
            }
        }
    }

    /**
     * Parse a line and,
     * depending on the type of contract,
     * create a contract
     * @param nextLine - Line with information about the Сontract
     * @throws ParseException - if a parsing program fails.
     */
    private Contract passing(String[] nextLine) throws ParseException {
        try {
            String[] startDate = nextLine[2].split("-");
            String[] endDate = nextLine[3].split("-");
            int id = Integer.parseInt(nextLine[0].trim());
            int number = Integer.parseInt(nextLine[1].trim());
            LocalDate start = LocalDate.of(Integer.parseInt(startDate[0].trim()), Integer.parseInt(startDate[1].trim()), Integer.parseInt(startDate[2].trim()));
            LocalDate end = LocalDate.of(Integer.parseInt(endDate[0].trim()), Integer.parseInt(endDate[1].trim()), Integer.parseInt(endDate[2].trim()));
            Person person = createNewPeron(nextLine);
            switch (nextLine[12].trim().toLowerCase()) {
                case "internetcontract":
                    return createInternetContract(nextLine[13], id, number, start, end, person);
                case "tvcontract":
                    return createTVContract(nextLine[13], id, number, start, end, person);
                case "mobilecontract":
                    return createMobileContract(nextLine[13], id, number, start, end, person);
            }
            return null;
        }
        catch (ParseException e){
            throw e;
        }
        catch (Exception e){
            throw new ParseException(e);
        }
    }

    /**Create a new owner if it is not in the repository
     * @param nextLine - Line with information about the Owner
     * @return Person
     * @throws ParseException - if a parsing program fails.
     */
    private Person createNewPeron(String[] nextLine) throws ParseException {
        try {
            int id = Integer.parseInt(nextLine[4].trim());
            String first_name = nextLine[5].trim();
            String last_name = nextLine[6].trim();
            String middle_name = nextLine[7].trim();
            String[] birt = nextLine[8].split("-");
            LocalDate bithday = LocalDate.of(Integer.parseInt(birt[0].trim()), Integer.parseInt(birt[1].trim()), Integer.parseInt(birt[2].trim()));
            Person.Gender gender;
            switch (nextLine[9].trim().toLowerCase()) {
                case "woman":
                    gender = Person.Gender.WOMAN;
                    break;
                case "male":
                    gender = Person.Gender.MALE;
                    break;
                default:
                    throw new ParseException("cant parse client gender");
            }
            int passportSeries = Integer.parseInt(nextLine[10].trim());
            int passportNumber = Integer.parseInt(nextLine[11].trim());
            Person person = new Person(id, first_name, last_name, middle_name, bithday, gender, passportSeries, passportNumber);

            for (Person person_from_repository : repository.getListSetPerson()) {
                if (person.equals(person_from_repository)) {
                    person = null;
                    return person_from_repository;
                }
            }
            return person;
        }
        catch (ParseException e){
            throw e;
        }
        catch (Exception e){
            throw new ParseException(e);
        }
    }

    /**Creating A Mobile Contract and adding to the repository
     * @param stringRate - Line with numbers Rate
     * @param id - id Contract
     * @param number - number Contract
     * @param startDate -  start date of the contract
     * @param endDate - end date of the contract
     * @param person - owner Contract
     * @throws ParseException - if a parsing program fails.
     */
    private Contract createMobileContract(String stringRate, int id, int number, LocalDate startDate, LocalDate endDate, Person person) throws ParseException {
        try {
            String[] rate = stringRate.split(",");
            int numberOfMinutes = Integer.parseInt(rate[0].trim());
            int numberOfSms = Integer.parseInt(rate[1].trim());
            int numberOfGb = Integer.parseInt(rate[2].trim());
            return new MobileContract(id, number, startDate, endDate, person, new Rate(numberOfMinutes, numberOfSms, numberOfGb));
        }
        catch (Exception e){
            throw new ParseException(e);
        }
    }

    /**
     * Creating A ТV Contract and adding to the repository
     * @param channelPac - channel package
     * @param id - id Contract
     * @param number - number Contract
     * @param startDate -  start date of the contract
     * @param endDate - end date of the contract
     * @param person - owner Contract
     * @throws ParseException - if a parsing program fails.
     */
    private Contract createTVContract(String channelPac, int id, int number, LocalDate startDate, LocalDate endDate, Person person) throws ParseException {
        try {
            TVContract.ChannelPackage channelPackage;
            switch (channelPac.trim().toLowerCase()) {
                case "cinema":
                    channelPackage = TVContract.ChannelPackage.CINEMA;
                    break;
                case "sport":
                    channelPackage = TVContract.ChannelPackage.SPORT;
                    break;
                case "standard":
                    channelPackage = TVContract.ChannelPackage.STANDARD;
                    break;
                default:
                    throw new ParseException("cant parse channel package");
            }
            return new TVContract(id, number, startDate, endDate, person, channelPackage);
        }
        catch (ParseException e){
            throw e;
        }
        catch (Exception e){
            throw new ParseException(e);
        }
    }

    /**Creating A Internet Contract and adding to the repository
     *
     * @param internetSpeed - internet speed
     * @param id - id Contract
     * @param number - number Contract
     * @param startDate - start date of the contract
     * @param endDate - end date of the contract
     * @param person - owner Contract
     * @throws ParseException - if a parsing program fails.
     */
    private Contract createInternetContract(String internetSpeed, int id, int number, LocalDate startDate, LocalDate endDate, Person person) throws ParseException {
        try {
            return new InternetContract(id, number, startDate, endDate, person, Integer.parseInt(internetSpeed.trim()));
        }
        catch (Exception e){
            throw new ParseException(e);
        }
    }

    /**
     * @param contract - Contract for validation check
     * @return true - if all messages with status @ValidationStatus.ERROR or  @ValidationStatus.WARNING
     */
    private boolean doValidation(Contract contract){
        List<Message> messages = validators.stream().map(validate -> validate.doValidate(contract)).collect(Collectors.toList());
        for(Message message : messages){
            if (message.getStatus() == ValidationStatus.ERROR || message.getStatus() == ValidationStatus.WARNING)
                return false;
        }
        return true;
    }
}