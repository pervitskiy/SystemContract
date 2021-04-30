package typeOfContracts.Rate;

public class Rate{
    static int seq_id=0;
    private int id ;
    private int numberOfMinutes;
    private int numberOfSms;
    private int numberOfGb;

    public Rate(int numberOfMinutes, int numberOfSms, int numberOfGb) {
        this.id = seq_id++;
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSms = numberOfSms;
        this.numberOfGb = numberOfGb;
    }

    public Rate(int id, int numberOfMinutes, int numberOfSms, int numberOfGb) {
        this.id = id;
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSms = numberOfSms;
        this.numberOfGb = numberOfGb;
    }

    @Override
    public String toString() {
        return
                numberOfMinutes +
                "," + numberOfSms +
                "," + numberOfGb;
    }

    public int getId() {
        return id;
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
