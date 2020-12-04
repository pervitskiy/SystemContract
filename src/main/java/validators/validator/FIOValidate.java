package validators.validator;

import typeOfContracts.Contract;
import validators.Message;
import validators.Validate;
import validators.ValidationStatus;

import java.util.regex.Pattern;

public class FIOValidate implements Validate<Contract> {

    private static final int MIN_LENGTH_FIRST_NAME = 3;
    private static final int MAX_LENGTH_FIRST_NAME = 100;
    private static final int MIN_LENGTH_LAST_NAME = 4;
    private static final int MAX_LENGTH_LAST_NAME = 50;
    private static final int MIN_LENGTH_MIDDLE_NAME = 4;
    private static final int MAX_LENGTH_MIDDLE_NAME = 60;


    @Override
    public Message doValidate(Contract contract) {

        String firstName = contract.getOwner().getFirst_name();
        String lastName = contract.getOwner().getLast_name();
        String middleName = contract.getOwner().getMiddle_name();

        String fullName = firstName + lastName + middleName;

        if (checkFirstNameIsLongLength(firstName))
            return new Message("first Name" + firstName + "is too long", ValidationStatus.ERROR);
        if (checkFirstNameIsSmallLength(firstName))
            return new Message("first Name" + firstName + "is too small", ValidationStatus.ERROR);
        if (checkLastNameIsLongLength(lastName))
            return new Message("Last Name" + lastName + "is too long", ValidationStatus.ERROR);
        if (checkLastNameIsSmallLength(lastName))
            return new Message("Last Name " + lastName + "is too small", ValidationStatus.ERROR);
        if (checkMiddleNameIsLongLength(middleName))
            return new Message("Middle Name" + middleName + "is too long", ValidationStatus.ERROR);
        if (checkMiddleNameIsSmallLength(middleName))
            return new Message("Middle Name " + middleName + "is too small", ValidationStatus.ERROR);
        if (checkInvalidChar(fullName))
            return new Message("Full Name " + fullName + " is invalid character", ValidationStatus.ERROR);
        return new Message(ValidationStatus.ОК);

    }

    private boolean checkFirstNameIsLongLength(String first_name){
        return first_name.length() > MAX_LENGTH_FIRST_NAME;
    }

    private boolean checkLastNameIsLongLength(String last_name){
        return last_name.length() > MAX_LENGTH_LAST_NAME;
    }

    private boolean checkMiddleNameIsLongLength(String middle_name){
        return middle_name.length() > MAX_LENGTH_MIDDLE_NAME;
    }

    private boolean checkFirstNameIsSmallLength(String first_name){
        return first_name.length() < MIN_LENGTH_FIRST_NAME;
    }

    private boolean checkLastNameIsSmallLength(String last_name){
        return last_name.length() < MIN_LENGTH_FIRST_NAME;
    }

    private boolean checkMiddleNameIsSmallLength(String middle_name){
        return middle_name.length() < MIN_LENGTH_FIRST_NAME;
    }

    private boolean checkInvalidChar(String full_name){
        Pattern pattern = Pattern.compile("[^a-zA-Z ]");
        return pattern.matcher(full_name).find();
    }
}
