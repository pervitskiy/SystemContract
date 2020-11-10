package typeOfContracts;

import customers.Person;

import java.time.LocalDate;

public abstract class Contract {
    /**
     * id - id Contract
     */
    private int id;
    private int numberContract;

    /**
     * Contract start date
     */
    private LocalDate startDate;

    /**
     * Contract end date
     */
    private LocalDate endDate;

    /**
     * Owner Contract
     */
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

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", numberContract=" + numberContract +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", owner=" + owner +
                '}';
    }
}
