package validators.validator;

import customers.Person;
import junit.framework.TestCase;
import org.junit.Assert;
import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.MobileContract;
import typeOfContracts.Rate.Rate;
import typeOfContracts.TVContract;
import validators.ValidationStatus;

import java.time.LocalDate;

public class FIOValidateTest extends TestCase {
    Person person1 = new Person(1, "Dima", "Per", "EB", LocalDate.of(2000, 10, 26), Person.Gender.MALE, 2014, 800457);
    Person person2 = new Person(2, "Max", "Lisdf", "HJewrwer", LocalDate.of(2004, 5, 26), Person.Gender.MALE, 2014, 800123);
    Person person3 = new Person(3, "Danil", "Petrov", "putins", LocalDate.of(2000, 11, 13), Person.Gender.MALE, 2014, 803148);

    Contract contract1 = new InternetContract(1,1, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person1, 100);
    Contract contract2 = new InternetContract(2,2, LocalDate.of(2010, 2, 2),LocalDate.of(2011, 12, 01), person1, 111);
    Contract contract3 = new InternetContract(3,3, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person2, 110);
    Contract contract4 = new TVContract(4,4, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person2, TVContract.ChannelPackage.CINEMA);
    Contract contract5 = new MobileContract(6,5, LocalDate.of(2009, 10, 12),LocalDate.of(2012, 12, 01), person3, new Rate(120, 100, 8 ));

    FIOValidate fioValidate = new FIOValidate();

    public void testDoValidate() {
        Assert.assertEquals(fioValidate.doValidate(contract1).getStatus(), ValidationStatus.ERROR); // mistake in last name and middle_name;
        Assert.assertEquals(fioValidate.doValidate(contract3).getStatus(), ValidationStatus.ОК);
        Assert.assertEquals(fioValidate.doValidate(contract4).getStatus(), ValidationStatus.ОК);
        Assert.assertEquals(fioValidate.doValidate(contract5).getStatus(), ValidationStatus.ОК);
    }
}