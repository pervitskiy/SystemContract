package typeOfContracts.Rate;

public class Rate{
    private int numberOfMinutes;
    private int numberOfSms;
    private int numberOfGb;

    public Rate(int numberOfMinutes, int numberOfSms, int numberOfGb) {
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSms = numberOfSms;
        this.numberOfGb = numberOfGb;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "numberOfMinutes=" + numberOfMinutes +
                ", numberOfSms=" + numberOfSms +
                ", numberOfGb=" + numberOfGb +
                '}';
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public int getNumberOfSms() {
        return numberOfSms;
    }

    public int getNumberOfGb() {
        return numberOfGb;
    }
}