package validators.validator;

import typeOfContracts.Contract;
import validators.Message;
import validators.Validate;
import validators.ValidationStatus;

/**
 * Class for validating an Age field for Contracts
 */
public class AgeOwnerValidate implements Validate<Contract> {
    public static final int MIN_AGE = 16;
    public static final int MAX_AGE = 100;
    public static final int MIN_RECOMMENDED_AGE = 18;

    /**
     * @param contract - the contract for validate
     * @return - Validation status message
     */
    @Override
    public Message doValidate(Contract contract) {
        int age = contract.getOwner().getAge();
        if (checkRecommendedOwner(age))
            return new Message("age " + age + "is less than the recommended values", ValidationStatus.WARNING);
        if (checkAgeOwner(age))
            return new Message("age " + age + "is not in the range", ValidationStatus.ERROR);
        return new Message(ValidationStatus.ОК);
    }

    /**
     * @param age - age Owner
     * @return true - if age is not in the acceptable range
     */
    private boolean checkAgeOwner(int age){
        return age > MAX_AGE || age < MIN_AGE;
    }


    /**
     * @param age - age Owner
     * @return true - if age less min recommended age
     */
    private boolean checkRecommendedOwner(int age){
        return  age < MIN_RECOMMENDED_AGE && age > MIN_AGE;
    }

}
