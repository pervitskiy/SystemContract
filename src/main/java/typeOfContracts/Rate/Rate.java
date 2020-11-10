package typeOfContracts.Rate;

public class Rate{
    private int numberOfMinutes;
    private int numberOfSms;
    private int numberOfGb;

    public Rate(int numberOfMinutes, int numberOfSms, int nubmerOfGb) {
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSms = numberOfSms;
        this.numberOfGb = nubmerOfGb;
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

    public int getNubmerOfGb() {
        return numberOfGb;
    }
}