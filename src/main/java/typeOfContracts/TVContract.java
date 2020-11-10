package typeOfContracts;

import customers.Person;

import java.time.LocalDate;

public class TVContract extends Contract {

    /**
     * String array of channel names
     */
    private ChannelPacage channelPacage;

    public enum ChannelPacage{
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
                ", channelPacage=" + channelPacage +
                '}';
    }

    public TVContract(int id, int numberContract, LocalDate startDate, LocalDate endDate, Person owner, ChannelPacage channelPacage) {
        super(id, numberContract, startDate, endDate, owner);
        this.channelPacage = channelPacage;
    }

    public ChannelPacage getChannelPacage() {
        return channelPacage;
    }
}
