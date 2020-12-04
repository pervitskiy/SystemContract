package validators.validator;

import typeOfContracts.Contract;
import validators.Message;
import validators.Validate;
import validators.ValidationStatus;

public class AgeOwnerValidate implements Validate<Contract> {
    public static final int MIN_AGE = 16;
    public static final int MAX_AGE = 100;
    public static final int MIN_RECOMMENDED_AGE = 18;

    @Override
    public Message doValidate(Contract contract) {
        int age = contract.getOwner().getAge();
        if (checkRecommendedOwner(age))
            return new Message("age " + age + "is less than the recommended values", ValidationStatus.WARNING);
        if (checkAgeOwner(age))
            return new Message("age " + age + "is not in the range", ValidationStatus.ERROR);
        return new Message(ValidationStatus.ОК);
    }

    private boolean checkAgeOwner(int age){
        return age > MAX_AGE || age < MIN_AGE;
    }

    private boolean checkRecommendedOwner(int age){
        return  age < MIN_RECOMMENDED_AGE && age > MIN_AGE;
    }

}
