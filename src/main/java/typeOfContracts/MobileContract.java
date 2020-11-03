package typeOfContracts;

import customers.Person;
import typeOfContracts.Rate.Rate;

import java.time.LocalDate;

public class MobileContract extends Contract {

    private Rate rate;

    public MobileContract(int id, int numberContract, LocalDate startDate, LocalDate endDate, Person owner, Rate rate) {
        super(id, numberContract, startDate, endDate, owner);
        this.rate = rate;
    }

    public Rate getRate() {
        return rate;
    }
}
