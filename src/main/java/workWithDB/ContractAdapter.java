package workWithDB;

import customers.Person;
import org.checkerframework.checker.units.qual.C;
import typeOfContracts.*;
import typeOfContracts.Rate.Rate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

import static java.sql.Types.NULL;

/**
 * Class contains the logic for saving and restore contracts in xml format
 */
public class ContractAdapter extends XmlAdapter<ContractAdapter.ContractXML, Contract> {


    /**
     * Convert a value type
     *
     * @param v the value to be converted
     * @return Contract
     * @throws Exception -if error during the conversion
     */
    @Override
    public Contract unmarshal(ContractXML v) throws Exception {
        if (v == null)
            return null;
        if (v.internetSpeed != NULL){
            return new InternetContract(v.id, v.id, v.startDate, v.endDate, v.owner, v.internetSpeed);
        }else if (v.channelPackage!=null){
            return new TVContract(v.id, v.id, v.startDate, v.endDate, v.owner, v.channelPackage);
        }
        else return new MobileContract(v.id, v.id, v.startDate, v.endDate, v.owner, v.rate);
    }

    /** Convert a value type
     *
     * @param v  the value to be converted
     * @throws Exception -if error during the conversion
     */
    @Override
    public ContractXML marshal(Contract v) throws Exception {
        if (v == null)
            return null;
        ContractXML contractXML = new ContractXML();
        contractXML.id = v.getId();
        contractXML.startDate = v.getStartDate();
        contractXML.endDate = v.getEndDate();
        contractXML.owner = v.getOwner();
        if (v instanceof MobileContract) {
            MobileContract contract = (MobileContract) v;
            contractXML.rate = contract.getRate();
        } else if (v instanceof InternetContract) {
            InternetContract ic = (InternetContract) v;
            contractXML.internetSpeed = ic.getInternetSpeed();
        } else {
            TVContract tvc = (TVContract) v;
            contractXML.channelPackage = tvc.getChannelPackage();
        }
        return contractXML;
    }

    /**
     * Class that contains all fields from all contracts
     */
    static class ContractXML {
        @XmlElement(name = "id")
        private int id;
        @XmlJavaTypeAdapter(ConverterTypeDate.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(ConverterTypeDate.class)
        private LocalDate endDate;
        @XmlElement(name = "person")
        private Person owner;
        @XmlElement(name = "internetSpeed")
        private int internetSpeed;
        @XmlElement(name = "channelPackage")
        private TVContract.ChannelPackage channelPackage;
        @XmlElement(name = "rate")
        private Rate rate;
    }
}
