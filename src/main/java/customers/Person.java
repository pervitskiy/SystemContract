package customers;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private int id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private LocalDate birthday;
    private Gender gender;
    private int passportSeries;
    private int passportNumber;

    private enum Gender {
        MALE,
        WOMAN
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
       return Period.between(LocalDate.now(), getBirthday()).getYears();
    }
}
