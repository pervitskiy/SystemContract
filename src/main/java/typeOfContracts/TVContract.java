package typeOfContracts;

import customers.Person;

import java.time.LocalDate;

public class TVContract extends Contract {
    private String[] channelPacage;

    public TVContract(int id, int numberContract, LocalDate startDate, LocalDate endDate, Person owner, String[] channelPacage) {
        super(id, numberContract, startDate, endDate, owner);
        this.channelPacage = channelPacage;
    }

    public String[] getChannelPacage() {
        return channelPacage;
    }
}
