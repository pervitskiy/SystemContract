package customers;

import junit.framework.TestCase;
import org.junit.Assert;

import java.time.LocalDate;

public class PersonTest extends TestCase {

    Person person1 = new Person(1, "Dima", "Per", "EB", LocalDate.of(2000, 10, 26), Person.Gender.MALE, 2014, 800457);
    Person person2 = new Person(2, "Max", "Lis", "HJ", LocalDate.of(2002, 11, 26), Person.Gender.MALE, 2014, 800123);
    Person person3 = new Person(3, "Danil", "Petrov", "EB", LocalDate.of(2000, 11, 13), Person.Gender.MALE, 2014, 803148);

    public void testGetAge() {

        int age1 = person1.getAge();
        int age2 = person2.getAge();
        int age3 = person3.getAge();

        int realAge1 = 20;
        int realAge2 = 18;
        int realAge3 = 20;
        Assert.assertEquals(age1, realAge1);
        Assert.assertEquals(age2, realAge2);
        Assert.assertEquals(age3, realAge3);
        System.out.println(person1.hashCode());
    }
}