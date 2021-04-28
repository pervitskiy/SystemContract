package workWithDB;

import customers.Person;
import repository.IRepository;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.MobileContract;
import typeOfContracts.Rate.Rate;
import typeOfContracts.TVContract;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static java.sql.Types.NULL;

public class RepositoryJDBC implements WorkWithDatabase{

    private static final String INSERT_PERSON = "INSERT INTO PERSON (person_id,first_name,last_name,middle_name,birthday,gender,passport_series,passport_number) VALUES (?,?,?,?,?,?,?,?) ON CONFLICT(person_id) DO UPDATE SET first_name=excluded.first_name";
    private static final String INSERT_CONTRACT = "INSERT INTO CONTRACT (id_contract,start_date,end_date,type_contract,max_speed,package_channel,rate,owner_id) VALUES (?,?,?,?,?,?,?,?)";


    private static RepositoryJDBC repositoryJDBC;
    private String url;
    private String driver;
    private String user;
    private String password;


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
            PreparedStatement contractStatement = connection.prepareStatement(INSERT_CONTRACT);

            Person person;
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
                contractStatement.setString(4, contract.getClass().toString());
                contractStatement.setNull(5, NULL);
                contractStatement.setNull(6, NULL);
                contractStatement.setNull(7, NULL);
                contractStatement.setInt(8, contract.getOwner().getId());
                if ( contract instanceof InternetContract) {
                    contractStatement.setDouble(5, ((InternetContract) contract).getInternetSpeed());
                } else if (contract instanceof MobileContract) {
                    contractStatement.setString(7, ((MobileContract) contract).getRate().toString());
                } else if (contract instanceof TVContract) {
                    contractStatement.setString(6, ((TVContract) contract).getChannelPackage().name());
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
        return null;
    }
}
