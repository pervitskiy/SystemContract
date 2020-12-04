package typeOfContracts;

import customers.Person;

import java.time.LocalDate;

public class InternetContract extends Contract {
    /**
     * internet speed in mb/s
     */
    private int internetSpeed;

    public InternetContract(int id, int numberContract, LocalDate startDate, LocalDate endDate, Person owner, int internetSpeed) {
        super(id, numberContract, startDate, endDate, owner);
        this.internetSpeed = internetSpeed;
    }

    public int getInternetSpeed() {
        return internetSpeed;
    }


    @Override
    public String toString() {
        return "InternetContract{" +
                "id=" + super.getId() +
                ", numberContract=" + super.getNumberContract()+
                ", startDate=" + super.getStartDate() +
                ", endDate=" + super.getEndDate() +
                ", owner=" + super.getOwner() +
                ", internetSpeed=" + internetSpeed +
                '}';
    }
}
