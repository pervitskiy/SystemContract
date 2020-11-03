package typeOfContracts;

import customers.Person;

import java.time.LocalDate;

public abstract class Contract {
    private int id;
    private int numberContract;
    private LocalDate startDate;
    private LocalDate endDate;
    private Person owner;

    public Contract(int id, int numberContract, LocalDate startDate, LocalDate endDate, Person owner) {
        this.id = id;
        this.numberContract = numberContract;
        this.startDate = startDate;
        this.endDate = endDate;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public int getNumberContract() {
        return numberContract;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Person getOwner() {
        return owner;
    }
}
