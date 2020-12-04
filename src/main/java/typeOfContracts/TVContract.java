package typeOfContracts;

import customers.Person;

import java.time.LocalDate;

public class TVContract extends Contract {

    /**
     * String array of channel names
     */
    private ChannelPackage channelPackage;

    public enum ChannelPackage {
        SPORT,
        STANDARD,
        CINEMA
    }

    @Override
    public String toString() {
        return "TVContract{" +
                "id=" + super.getId() +
                ", numberContract=" + super.getNumberContract()+
                ", startDate=" + super.getStartDate() +
                ", endDate=" + super.getEndDate() +
                ", owner=" + super.getOwner() +
                ", channelPackage=" + channelPackage +
                '}';
    }

    public TVContract(int id, int numberContract, LocalDate startDate, LocalDate endDate, Person owner, ChannelPackage channelPackage) {
        super(id, numberContract, startDate, endDate, owner);
        this.channelPackage = channelPackage;
    }

    public ChannelPackage getChannelPackage() {
        return channelPackage;
    }
}
