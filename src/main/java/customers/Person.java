package customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Person {
    private int id;
    private String first_name;
    private String last_name;

    private String middle_name;
    private LocalDate birthday;
    private Gender gender;
    private int passportSeries;
    private int passportNumber;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", passportSeries=" + passportSeries +
                ", passportNumber=" + passportNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                passportSeries == person.passportSeries &&
                passportNumber == person.passportNumber &&
                first_name.equals(person.first_name) &&
                last_name.equals(person.last_name) &&
                middle_name.equals(person.middle_name) &&
                birthday.equals(person.birthday) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, middle_name, birthday, gender, passportSeries, passportNumber);
    }

    public enum Gender {
        MALE,
        WOMAN
    }

    public Person(int id, String first_name, String last_name, String middle_name, String birthday, Gender gender, int passportSeries, int passportNumber) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.birthday = LocalDate.parse(birthday, formatter);;
        this.gender = gender;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
    }

    public Person(int id, String first_name, String last_name, String middle_name, LocalDate birthday, Gender gender, int passportSeries, int passportNumber) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.birthday = birthday;
        this.gender = gender;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public int getPassportSeries() {
        return passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public int getAge(){
       return Period.between(getBirthday(), LocalDate.now()).getYears();
    }
}
