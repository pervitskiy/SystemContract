package typeOfContracts;

import customers.Person;
import typeOfContracts.Rate.Rate;

import java.time.LocalDate;

public class MobileContract extends Contract {

    /**
     * Mobile tariff
     */
    private Rate rate;

    public MobileContract(int id, int numberContract, LocalDate startDate, LocalDate endDate, Person owner, Rate rate) {
        super(id, numberContract, startDate, endDate, owner);
        this.rate = rate;
    }

    public Rate getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "MobileContract{" +
                "id=" + super.getId() +
                ", numberContract=" + super.getNumberContract()+
                ", startDate=" + super.getStartDate() +
                ", endDate=" + super.getEndDate() +
                ", owner=" + super.getOwner() +
                ", rate=" + rate +
                '}';
    }
}
