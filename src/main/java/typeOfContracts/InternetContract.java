package typeOfContracts;

import customers.Person;

import java.time.LocalDate;

public class InternetContract extends Contract {
    private double internetSpeed;

    public InternetContract(int id, int numberContract, LocalDate startDate, LocalDate endDate, Person owner, double internetSpeed) {
        super(id, numberContract, startDate, endDate, owner);
        this.internetSpeed = internetSpeed;
    }

    public double getInternetSpeed() {
        return internetSpeed;
    }
}
