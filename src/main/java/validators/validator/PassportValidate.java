package validators.validator;

import typeOfContracts.Contract;
import validators.Message;
import validators.Validate;
import validators.ValidationStatus;

import java.util.regex.Pattern;

/**
 * Class for validating an Number and Series Passport field for Contracts
 */
public class PassportValidate implements Validate<Contract> {

    public static final int PASSPORT_LENGTH = 10;

    /**
     * @param contract - the contract for validate
     * @return - Validation status message
     */
    @Override
    public Message doValidate(Contract contract) {
        String passport = contract.getOwner().getPassportSeries() + String.valueOf(contract.getOwner().getPassportNumber());
        if (checkLengthPassport(passport))
            return new Message("the number of digits in the passport" + passport + " is not correct\"", ValidationStatus.ERROR);
        if (checkInvalidCharacter(passport))
            return new Message("passport" + passport + "is invalid character", ValidationStatus.ERROR);
        return new Message(ValidationStatus.ОК);
    }

    /**
     * @param passport - number + series passport
     * @return true - if wrong length passport
     */
    private boolean checkLengthPassport(String passport){
        return passport.length() != PASSPORT_LENGTH;
    }


    /**
     * @param passport - number + series passport
     * @return true - if the line contains invalid characters
     */
    private boolean checkInvalidCharacter(String passport){
        Pattern pattern = Pattern.compile("[^0-9]");
        return pattern.matcher(passport).find();
    }

}
