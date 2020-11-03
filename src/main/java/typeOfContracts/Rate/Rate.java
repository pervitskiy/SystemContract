package typeOfContracts.Rate;

public class Rate{
    private int numberOfMinutes;
    private int numberOfSms;
    private int nubmerOfGb;

    public Rate(int numberOfMinutes, int numberOfSms, int nubmerOfGb) {
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSms = numberOfSms;
        this.nubmerOfGb = nubmerOfGb;
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public int getNumberOfSms() {
        return numberOfSms;
    }

    public int getNubmerOfGb() {
        return nubmerOfGb;
    }
}