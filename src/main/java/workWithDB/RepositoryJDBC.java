package workWithDB;

import customers.Person;
import repository.IRepository;
import repository.MyRepository;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.MobileContract;
import typeOfContracts.Rate.Rate;
import typeOfContracts.TVContract;
import wortWithFiles.MyСSVReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static java.sql.Types.NULL;

public class RepositoryJDBC implements WorkWithDatabase{

    private static final String INSERT_PERSON = "INSERT INTO PERSON (person_id,first_name,last_name,middle_name,birthday,gender,passport_series,passport_number) VALUES (?,?,?,?,?,?,?,?) ON CONFLICT(person_id) DO UPDATE SET first_name=excluded.first_name";
    private static final String INSERT_RATE = "INSERT INTO RATE (id_rate,numberOfMinutes,numberOfSms,numberOfGb) VALUES (?,?,?,?) ON CONFLICT(id_rate) DO UPDATE SET numberOfMinutes=excluded.numberOfMinutes";
    private static final String INSERT_CONTRACT = "INSERT INTO CONTRACT (id_contract,start_date,end_date,max_speed,package_channel,rate,owner_id) VALUES (?,?,?,?,?,?,?) ON CONFLICT(id_contract) DO UPDATE SET start_date=excluded.start_date";
    private static final String SELECT_CONTRACT = "SELECT * FROM CONTRACT join Person ON owner_id = person_id left join Rate  on rate = id_rate";



    private static RepositoryJDBC repositoryJDBC;
    private String url;
    private String driver;
    private String user;
    private String password;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



    private RepositoryJDBC(){
        try {
            InputStream dbp = RepositoryJDBC.class.getClassLoader().getResourceAsStream("DBConnection.properties");
            Properties props = new Properties();
            props.load(dbp);
            this.url = props.getProperty("Database.DataURL");
            this.driver = props.getProperty("Database.Driver");
            this.user = props.getProperty("Database.User");
            this.password = props.getProperty("Database.Password");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized RepositoryJDBC getRepositoryJDBC(){
        if (repositoryJDBC == null)
            repositoryJDBC = new RepositoryJDBC();
        return repositoryJDBC;
    }

    @Override
    public void save(IRepository repository) {
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement personStatement = connection.prepareStatement(INSERT_PERSON);
            PreparedStatement rateStatement = connection.prepareStatement(INSERT_RATE);
            PreparedStatement contractStatement = connection.prepareStatement(INSERT_CONTRACT);

            Person person;
            Rate rate;
            for (int i=0; i<repository.size(); i++){
                Contract contract = (Contract) repository.getContact(i);
                person = contract.getOwner();
                personStatement.setInt(1, person.getId());
                personStatement.setString(2, person.getFirst_name());
                personStatement.setString(3, person.getLast_name());
                personStatement.setString(4, person.getMiddle_name());
                personStatement.setDate(5, Date.valueOf(person.getBirthday()));
                personStatement.setString(6, person.getGender().name());
                personStatement.setInt(7, person.getPassportSeries());
                personStatement.setInt(8, person.getPassportNumber());
                personStatement.executeUpdate();

                contractStatement.setInt(1, contract.getId());
                contractStatement.setDate(2, Date.valueOf(contract.getStartDate()));
                contractStatement.setDate(3, Date.valueOf(contract.getEndDate()));
                contractStatement.setNull(4, NULL);
                contractStatement.setNull(5, NULL);
                contractStatement.setNull(6, NULL);
                contractStatement.setInt(7, contract.getOwner().getId());
                if ( contract instanceof InternetContract) {
                    contractStatement.setDouble(4, ((InternetContract) contract).getInternetSpeed());
                } else if (contract instanceof MobileContract) {
                    rate = ((MobileContract) contract).getRate();
                    rateStatement.setInt(1, rate.getId());
                    rateStatement.setInt(2, rate.getNumberOfMinutes());
                    rateStatement.setInt(3, rate.getNumberOfSms());
                    rateStatement.setInt(4, rate.getNumberOfGb());
                    rateStatement.executeUpdate();

                    contractStatement.setInt(6, ((MobileContract) contract).getRate().getId());
                } else if (contract instanceof TVContract) {
                    contractStatement.setString(5, ((TVContract) contract).getChannelPackage().name());
                }
                contractStatement.executeUpdate();
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public IRepository dump() {
        IRepository repository = new MyRepository();
        try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement statement = connection.prepareStatement(SELECT_CONTRACT);
             ResultSet set = statement.executeQuery()) {
            Person person;
            Contract contract = null;
            Rate rate;
            while (set.next()) {
                person = new Person(
                        set.getInt("person_id"),
                        set.getString("first_name"),
                        set.getString("last_name"),
                        set.getString("middle_name"),
                        set.getString("birthday"),
                        Person.Gender.valueOf(set.getString("gender")),
                        set.getInt("passport_series"),
                        set.getInt("passport_number")
                );
                int contract_id = set.getInt("id_contract");
                String start_date = set.getString("start_date");
                String end_date = set.getString("end_date");
                if (set.getDouble("max_speed") != NULL) {
                    contract = new InternetContract(contract_id,contract_id, LocalDate.parse(start_date, formatter), LocalDate.parse(end_date, formatter), person, set.getInt("max_speed"));
                } else if (set.getString("package_channel") != null) {
                    contract = new TVContract(contract_id,contract_id, LocalDate.parse(start_date, formatter), LocalDate.parse(end_date, formatter), person, TVContract.ChannelPackage.valueOf(set.getString("package_channel")) );
                } else if (set.getString("rate") != null) {
                    rate = new Rate(set.getInt("id_rate"), set.getInt("numberOfMinutes"), set.getInt("numberOfSms"), set.getInt("numberOfGb"));
                    contract = new MobileContract(contract_id,contract_id, LocalDate.parse(start_date, formatter), LocalDate.parse(end_date, formatter), person, rate);
                }
                repository.add(contract);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return repository;
    }
}
